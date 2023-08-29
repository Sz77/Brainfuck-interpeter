cells = [0] * 30000
ptr = 0


def move_left() -> None:
    """
    Move the pointer to the left by one cell (ptr--)
    :return: None
    """
    global ptr
    if ptr > 0:
        ptr -= 1
    else:
        raise BufferError("ptr smaller than 0")


def move_right() -> None:
    """
    Move the pointer to the right by one cell (ptr++)
    :return: None
    """
    global ptr
    if ptr < len(cells) - 1:
        ptr += 1
    else:
        raise BufferError("ptr bigger than the amount of cells")


def inc() -> None:
    """
    Increment the value of the cell at the pointer by one (cells[ptr]++)
    :return: None
    """
    cells[ptr] += 1


def dec() -> None:
    """
    Decrement the value of the cell at the pointer by one (cells[ptr]--)
    :return: None
    """
    cells[ptr] -= 1


def output() -> None:
    """
    Output the value of the cell at the pointer as an ASCII character
    :return: None
    """
    print(chr(cells[ptr]), end="")


def char_in() -> None:
    """
    Accept one byte of input, storing its value in the cell at the pointer
    :return: None
    """
    char = input()
    char = char[0]  # only the first char
    cells[ptr] = ord(char)


def start_loop() -> bool:
    """
    If the value of the cell at the pointer is zero, jump to the command after the matching ] command
    :return: True if the value of the cell at the pointer is zero, False otherwise
    """
    return cells[ptr] == 0


def stop_loop() -> bool:
    """
    If the value of the cell at the pointer is nonzero, jump to the command after the matching [ command
    :return: True if the value of the cell at the pointer is nonzero, False otherwise
    """
    return cells[ptr] != 0


def get_char_at_index(code: str, i: int) -> str:
    """
    Get the character at the index i in the code
    :param code:  to get the character from
    :param i: Index of the character
    :return:  at the index i in the code
    """
    return code[i]


def execute(code: str) -> None:
    """
    Execute the Brainfuck code
    :param code: The code to execute
    :return: None
    """
    i = 0  # index in the code
    while i < len(code):
        ip = get_char_at_index(code, i)
        match ip:
            case ">":
                move_left()
            case "<":
                move_right()
            case "+":
                inc()
            case "-":
                dec()
            case ".":
                output()
            case ",":
                char_in()
            case "[":
                if start_loop():
                    while ip != "]":
                        i += 1
                        ip = get_char_at_index(code, i)
            case "]":
                if stop_loop():
                    i -= 1
                    while ip != "[":
                        i -= 1
                        ip = get_char_at_index(code, i)
            case _:
                pass
        i += 1


def main():
    # Hello World!
    code = (
        "e++++++++++["
        ">+++++++>++++++seteyr++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>."
    )
    execute(code)


if __name__ == "__main__":
    main()
