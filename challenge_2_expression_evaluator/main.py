import re

# Small calculator that understands + - * / ^, brackets, decimals
# and implicit multiplication.
# Examples to try: 2+3*4   (1+2)(3+4)   2^3^2   -2^2

# matches either a number (12, 3.5, .5) or a single operator/bracket
TOKEN_PATTERN = re.compile(r"\d+(?:\.\d*)?|\.\d+|[+\-*/^()\[\]{}]")

OPERATORS = ("+", "-", "*", "/", "^")
OPEN_BRACKETS = ("(", "[", "{")
CLOSE_BRACKETS = (")", "]", "}")


def is_number(token):
    # anything that isn't an operator or a bracket must be a number
    return token not in OPERATORS and token not in ("(", ")")


def normalize_brackets(tokens):
    # treat [ ] and { } exactly like ( )
    cleaned = []

    for token in tokens:
        if token in OPEN_BRACKETS:
            cleaned.append("(")
        elif token in CLOSE_BRACKETS:
            cleaned.append(")")
        else:
            cleaned.append(token)

    return cleaned


def add_implicit_multiplication(tokens):
    # turns 2(3) into 2*(3), and (2)(3) into (2)*(3)
    result = []

    for token in tokens:
        if result:
            previous = result[-1]

            # (2)(3) or (2)3
            after_bracket = previous == ")" and (token == "(" or is_number(token))

            # 2(3)
            before_bracket = is_number(previous) and token == "("

            if after_bracket or before_bracket:
                result.append("*")

        result.append(token)

    return result


def tokenize(expression):
    tokens = TOKEN_PATTERN.findall(expression)

    # if joining the tokens doesn't give back the original (minus spaces),
    # then there was a character we don't understand
    without_spaces = re.sub(r"\s+", "", expression)

    if "".join(tokens) != without_spaces:
        raise ValueError("Invalid character in expression")

    tokens = normalize_brackets(tokens)
    return add_implicit_multiplication(tokens)


class Parser:
    # Goes through the tokens left to right and calculates as it goes.
    # Each method handles one level of priority, lowest first:
    #   + -   ->   * /   ->   unary + -   ->   ^   ->   numbers and ( )

    def __init__(self, tokens):
        self.tokens = tokens
        self.position = 0

    def current(self):
        # look at the current token without moving forward
        if self.position < len(self.tokens):
            return self.tokens[self.position]
        return None

    def consume(self):
        # return the current token and move to the next one
        token = self.current()
        self.position += 1
        return token

    def parse_expression(self):
        # addition and subtraction
        result = self.parse_term()

        while self.current() in ("+", "-"):
            operator = self.consume()
            right = self.parse_term()

            if operator == "+":
                result += right
            else:
                result -= right

        return result

    def parse_term(self):
        # multiplication and division
        result = self.parse_unary()

        while self.current() in ("*", "/"):
            operator = self.consume()
            right = self.parse_unary()

            if operator == "*":
                result *= right
            else:
                if right == 0:
                    raise ValueError("Cannot divide by zero")
                result /= right

        return result

    def parse_unary(self):
        # a + or - sitting in front of something, like -5 or --5
        if self.current() == "+":
            self.consume()
            return self.parse_unary()

        if self.current() == "-":
            self.consume()
            return -self.parse_unary()

        return self.parse_power()

    def parse_power(self):
        # exponents, e.g. 2^3
        base = self.parse_primary()

        if self.current() == "^":
            self.consume()

            # parsing the exponent with parse_unary lets 2^3^2 work
            # right-to-left (2^(3^2)) and also allows 2^-1
            exponent = self.parse_unary()

            try:
                result = base ** exponent
            except ZeroDivisionError:
                raise ValueError("Cannot raise 0 to a negative power")
            except OverflowError:
                raise ValueError("Result is too big")

            # e.g. (-8)^0.5 gives a complex number in Python, which we don't support
            if isinstance(result, complex):
                raise ValueError("Result is not a real number")

            return result

        return base

    def parse_primary(self):
        # the smallest piece: a number, or something inside brackets
        token = self.current()

        if token is None:
            raise ValueError("Unexpected end of expression")

        if token == "(":
            self.consume()
            result = self.parse_expression()

            if self.current() != ")":
                raise ValueError("Missing closing bracket")

            self.consume()
            return result

        if token == ")":
            raise ValueError("Unexpected closing bracket")

        self.consume()
        return float(token)

    def calculate(self):
        result = self.parse_expression()

        # if there are tokens left over, the expression was malformed (like "2 3")
        if self.current() is not None:
            raise ValueError(f"Unexpected token: {self.current()}")

        return result


def evaluate(expression):
    tokens = tokenize(expression)

    if len(tokens) == 0:
        raise ValueError("Empty expression")

    parser = Parser(tokens)
    return parser.calculate()

from pathlib import Path


def format_result(result):
    if isinstance(result, float) and result.is_integer():
        return str(int(result))

    return str(result)


def process_file(input_file, output_file):
    try:
        with open(input_file, "r", encoding="utf-8") as file:
            lines = file.readlines()

    except FileNotFoundError:
        print(f"Input file not found: {input_file}")
        return

    output_lines = []

    for line in lines:
        line = line.strip()

        if not line:
            continue

        # Remove an existing answer, if present
        expression = line.split("=", 1)[0].strip()

        try:
            result = evaluate(expression)
            output_lines.append(
                f"{expression} = {format_result(result)}"
            )

        except Exception as error:
            output_lines.append(
                f"{expression} = ERROR ({error})"
            )

    with open(output_file, "w", encoding="utf-8") as file:
        file.write("\n".join(output_lines))

    print(f"Processed {len(output_lines)} expressions.")
    print(f"Results saved to: {output_file}")

def main():
    folder = Path(__file__).resolve().parent

    input_file = folder / "input.txt"
    output_file = folder / "output.txt"

    process_file(input_file, output_file)


if __name__ == "__main__":
    main()