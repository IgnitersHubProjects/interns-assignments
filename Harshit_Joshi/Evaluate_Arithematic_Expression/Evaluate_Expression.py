import ast
import operator as op
import re


# Allowed operators mapping
ALLOWED_OPERATORS = {
    ast.Add: op.add,
    ast.Sub: op.sub,
    ast.Mult: op.mul,
    ast.Div: op.truediv,
    ast.Pow: op.pow,
    ast.USub: op.neg,
    ast.UAdd: op.pos,
}


def safe_eval(expr: str) -> float:
    """
    Safely evaluate a math expression using AST.
    Supports: +, -, *, /, **, unary +/-.
    """
    node = ast.parse(expr, mode="eval")

    def _eval(n):
        if isinstance(n, ast.Expression):
            return _eval(n.body)

        if isinstance(n, ast.Constant):  # Python 3.8+
            if isinstance(n.value, (int, float)):
                return n.value
            raise ValueError("Only numeric constants allowed")

        if isinstance(n, ast.BinOp):
            left = _eval(n.left)
            right = _eval(n.right)
            op_type = type(n.op)
            if op_type in ALLOWED_OPERATORS:
                return ALLOWED_OPERATORS[op_type](left, right)
            raise ValueError("Operator not allowed")

        if isinstance(n, ast.UnaryOp):
            operand = _eval(n.operand)
            op_type = type(n.op)
            if op_type in ALLOWED_OPERATORS:
                return ALLOWED_OPERATORS[op_type](operand)
            raise ValueError("Unary operator not allowed")

        raise ValueError("Invalid expression")

    return _eval(node)


def normalize_expression(expression: str) -> str:
    """
    - Replace ^ with ** for power
    - Replace [] and {} with ()
    - Remove extra spaces
    """
    expression = expression.replace("^", "**")
    expression = expression.replace("[", "(").replace("]", ")")
    expression = expression.replace("{", "(").replace("}", ")")
    expression = re.sub(r"\s+", " ", expression).strip()
    return expression


def solve_file(input_file: str, output_file: str):
    """
    Reads input file line-by-line, evaluates expressions before '=' and writes results.
    Output format: original_expression = value
    """
    with open(input_file, "r", encoding="utf-8") as f:
        lines = f.readlines()

    output_lines = []

    for line in lines:
        original_line = line.rstrip("\n").strip()
        if not original_line:
            continue

        if "=" not in original_line:
            output_lines.append(original_line)
            continue

        left_side = original_line.split("=")[0].strip()
        normalized = normalize_expression(left_side)

        try:
            result = safe_eval(normalized)

            # If result is like 8.0 -> show 8
            if abs(result - int(result)) < 1e-9:
                result = int(result)

            output_lines.append(f"{left_side} = {result}")
        except Exception as e:
            output_lines.append(f"{left_side} = ERROR ({e})")

    with open(output_file, "w", encoding="utf-8") as f:
        f.write("\n".join(output_lines))

    print(f"✅ Done! Output written to: {output_file}")


def main():
    print("===== Challenge 2: Evaluate Arithmetic Expressions =====")
    input_file = input("Enter input filename (example: input_expressions.txt): ").strip()
    output_file = input("Enter output filename (example: output_expressions.txt): ").strip()

    try:
        solve_file(input_file, output_file)
    except FileNotFoundError:
        print("❌ Input file not found. Please check file name/path.")


if __name__ == "__main__":
    main()
