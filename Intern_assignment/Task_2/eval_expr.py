#Input: text file 
#Output: output file with solved arithmetic expressions (line by line)
#Constraints: Digits 0 - 9 and + - * / ^ . ( ) [ ] { }

#TASK_2 Evaluate arithmetic expressions in Python

import re

def eval_exp(exp):
        exp = exp.replace('^', '**')
        exp = exp.replace('–', '-')
        answer = eval(exp)
        return answer


def files(input, output):
        with open(input, 'r', encoding='utf-8') as inp_file, open(output, 'w') as out_file:
            for line in inp_file:
                line = line.strip()
                if '=' in line:
                    exp = line.split('=')[0].strip()
                    answer = eval_exp(exp)
                    out_file.write(f"{line} {answer}\n")
                else: print('did not find =')
        print("Expressions evaluated. Results written to output file")

def main():
    print("Expression Solver")
    inp = input("Input file path without apostrophe: ")
    out = input("output file path without apostrophe: ")
    files(inp, out)

if __name__ == "__main__":
    main()