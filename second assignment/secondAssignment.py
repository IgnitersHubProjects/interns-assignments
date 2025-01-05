
def solve_arithmetic_expressions(input_filename, output_filename):
   
    with open(input_filename, 'r') as input_file, open(output_filename, 'w') as output_file:
        
        for line in input_file:
            expression = line.strip()
            if expression.endswith('='):
               
                expression = expression[:-1].strip()
                
                try:
                    
                    result = eval(expression)
                    output_line = f"{line.strip()} {result}\n"
                    output_file.write(output_line)
                except Exception as e:
                    
                    output_file.write(f"Error evaluating: {expression}\n")
            else:
               
                output_file.write(f"Invalid expression format: {line.strip()}\n")


def main():
    
    input_filename = 'inputt.txt'
    output_filename = 'outputt.txt'  
    
  
    solve_arithmetic_expressions(input_filename, output_filename)
    print("The expressions have been solved and saved in the output file.")

if __name__ == "__main__":
    main()
