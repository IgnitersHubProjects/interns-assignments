import re


def solve_expressions(input_file, output_file):
    def evaluate_expression(expression):
        # Replace ^ with ** for Python's exponentiation operator
        expression = expression.replace('^', '**')
        try:
            # Evaluate the expression safely using eval
            result = eval(expression)
            return result
        except Exception as e:
            return f"Error: {e}"

    try:
        # Read lines from the input file
        with open(input_file, 'r') as infile:
            lines = infile.readlines()

        output_lines = []

        for line in lines:
            # Match the expression before the '=' symbol
            match = re.match(r'(.*)=', line.strip())
            if match:
                expression = match.group(1).strip()
                result = evaluate_expression(expression)
                output_lines.append(f"{expression} = {result}\n")
            else:
                # If no '=' is found, write the line as-is with an error message
                output_lines.append(f"{line.strip()} = Error: Invalid format\n")

        # Write the results to the output file
        with open(output_file, 'w') as outfile:
            outfile.writelines(output_lines)

        print(f"Results written to {output_file}")

    except FileNotFoundError:
        print(f"Error: File {input_file} not found.")
    except Exception as e:
        print(f"An error occurred: {e}")


# Example usage
input_file = 'input.txt'  # Replace with your input file name
output_file = 'output.txt'  # Replace with your desired output file name
solve_expressions(input_file, output_file)
