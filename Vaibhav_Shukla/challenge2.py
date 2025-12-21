# simple arithmetic expression solver using full file paths

input_file = r"D:\git-work\interns-assignments\Vaibhav_Shukla\input.txt"
output_file = r"D:\git-work\interns-assignments\Vaibhav_Shukla\output.txt"

with open(input_file, "r") as inp, open(output_file, "w") as out:
    
    for line in inp:
        expression = line.replace("=", "").strip()         # cleaning the expression and replacing ^ with ** and removing =
        expression = expression.replace("^", "**")

        try:
            answer = eval(expression)            # evaluating the expression 
            out.write(f"{expression} = {answer}\n")
        except:
            out.write(f"{expression} = Error\n")

print("Done! Check output file in the repo output.txt")
