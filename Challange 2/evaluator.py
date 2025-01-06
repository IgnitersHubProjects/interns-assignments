import re

def evaluate_expression(expression):
    """
    Evaluate the arithmetic expression safely.
    This function uses Python's eval function but restricts the available built-ins to prevent malicious code execution.
    """
    try:
        # Replacing '^' with '**' for exponentiation (since Python uses '**' for power)
        expression = expression.replace('^', '**')
        # Evaluate the expression
        result = eval(expression)
        return result
    except Exception as e:
        return f"Error: {str(e)}"

def process_file(input_filename, output_filename):
    """
    Reads an input file containing arithmetic expressions, solves each expression,
    and writes the results to an output file.
    """
    with open(input_filename, 'r') as infile:
        expressions = infile.readlines()

    with open(output_filename, 'w') as outfile:
        for expression in expressions:
            # Strip leading/trailing whitespace characters
            expression = expression.strip()
            if expression:
                # Match the expression pattern and split on the equality sign
                match = re.match(r"(.*)=$", expression)
                if match:
                    expr = match.group(1)
                    result = evaluate_expression(expr)
                    # Write the original expression and the result to the output file
                    outfile.write(f"{expr} = {result}\n")

if __name__ == "__main__":
    input_file = 'input.txt'
    output_file = 'output.txt'
    process_file(input_file, output_file)
    print(f"Results written to {output_file}")
