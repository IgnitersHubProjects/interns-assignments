import re

# Input and output file paths
input_file = r"C:\Users\Riya Joshi\OneDrive\Desktop\IgnitersHub\expressions.txt"
output_file = r"C:\Users\Riya Joshi\OneDrive\Desktop\IgnitersHub\solutions.txt"

# Function to normalize brackets
def normalize_brackets(expr):
    return expr.replace("[", "(").replace("]", ")").replace("{", "(").replace("}", ")")

# Read input file
with open(input_file, "r") as f:
    lines = f.readlines()

results = []

for line in lines:
    original_line = line.strip()
    if not original_line:  # Skip empty lines
        continue

    # Remove '=' sign and normalize brackets
    expr = normalize_brackets(original_line.replace("=", "").strip())
    # Replace '^' with '**' for exponentiation
    expr = expr.replace("^", "**")
    # Remove invalid characters
    expr = re.sub(r"[^0-9+\-*/(). ]", "", expr)
    
    try:
        answer = eval(expr)
        # Convert float ending with .0 to int for cleaner output
        if isinstance(answer, float) and answer.is_integer():
            answer = int(answer)
    except Exception:
        answer = "Error"
    
    # Add the answer to the original line
    results.append(f"{original_line} {answer}")

# Write results to output file
with open(output_file, "w") as f:
    f.write("\n".join(results))

print(f"Solutions written to {output_file}")
