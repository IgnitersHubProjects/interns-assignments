def evaluate_expression(expr):
    expr = expr.replace("^", "**")
    expr = expr.replace("{", "(").replace("}", ")")
    expr = expr.replace("[", "(").replace("]", ")")

    return eval(expr)

input_file = "input.txt"
output_file = "output.txt"

with open(input_file, "r") as infile, open(output_file, "w") as outfile:

    for line in infile:
        line = line.strip()
        if not line:
            continue
        expression = line.rstrip("=")
        try:
            result = evaluate_expression(expression)
            outfile.write(f"{expression}= {result}\n")
        except Exception:
            outfile.write(f"{expression}= ERROR\n")
print("output file generated ")