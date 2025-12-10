def evaluate_expression(expr):
    expr = expr.replace("^", "**")
    return eval(expr)

def process_file(input_file, output_file):
    with open(input_file, 'r') as f:
        lines = f.readlines()

    with open(output_file, 'w') as f:
        for line in lines:
            expression = line.strip().rstrip('=').strip()
            result = evaluate_expression(expression)
            f.write(f"{expression} = {result}\n")

process_file("input.txt", "output.txt")