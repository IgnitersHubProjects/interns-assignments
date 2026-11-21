'''Write a program that reads a text file, solves all the Arithmetic Expressions given in
the text file, and generates a new output file with answers.
Input
10 – 2 =
100 * ( 2 + 12 ) =
(10 + 5^2) ( (5*-2) + 9 - 3^3) / 2=

Output
10 – 2 = 8
100 * ( 2 + 12 ) = 1400
(10 + 5^2) ( (5*-2) + 9 - 3^3) / 2 = -490
'''

# Reading expressions from input file
file = open("input.txt", "r")
lines = file.readlines()
file.close()
# Creating output file
output = open("output.txt", "w")
print("***********************ARITHMETIC EXPRESSIONS****************************")
for line in lines:
    line = line.strip()
    if line == "":
        continue
    # Remove equal sign
    expression = line.replace("=", "").strip()
    # Change power symbol
    expression = expression.replace("^", "**")
    # Change brackets
    expression = expression.replace("[", "(")
    expression = expression.replace("]", ")")
    expression = expression.replace("{", "(")
    expression = expression.replace("}", ")")
    # Add multiplication between adjacent brackets
    expression = expression.replace(") (", ") * (")
    expression = expression.replace(")(", ")*(")
    try:
        answer = eval(expression)
        # Save expression and answer
        output.write(line + " " + str(answer) + "\n")
    except:
        output.write(line + " Error\n")
output.close()
print("Check output.txt for answers.")
print("-------------------Calculation completed!--------------------")
