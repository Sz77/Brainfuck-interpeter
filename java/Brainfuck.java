import java.util.Scanner;

/**
 * The Brainfuck class provides an interpreter for the Brainfuck programming language.
 * Brainfuck is an esoteric programming language with a minimalistic set of commands.
 * This class allows you to execute Brainfuck code and provides methods to manipulate
 * memory cells, input, and output.
 */
public class Brainfuck {
    // Scanner for input
    static Scanner scanner = new Scanner(System.in);

    // Pointer to the current memory cell
    static int ptr = 0;

    // Memory array with 30000 cells
    static int[] arr = new int[30000];

    /**
     * Main method to execute Brainfuck code.
     *
     * @param args The command-line arguments (unused).
     */
    public static void main(String[] args) {
        // hello world!
        String code = "e++++++++++[>+++++++>++++++seteyr++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        execute(code);
    }

    /**
     * Get the character at the specified index in the code.
     *
     * @param code The Brainfuck code.
     * @param i    The index in the code.
     * @return The character at the specified index.
     */
    public static char getCharAtIndex(String code, int i) {
        return code.charAt(i);
    }

    /**
     * Execute Brainfuck code.
     *
     * @param code The Brainfuck code to execute.
     */
    public static void execute(String code) {
        int i = 0;
        while (i < code.length()) {
            char ip = getCharAtIndex(code, i);
            switch (ip) {
                case '>' -> moveRight();
                case '<' -> moveLeft();
                case '+' -> inc();
                case '-' -> dec();
                case ',' -> input();
                case '.' -> output();
                case '[' -> {
                    if (startLoop()) {
                        while (ip != ']') {
                            i++;
                            ip = getCharAtIndex(code, i);
                        }
                    }
                }
                case ']' -> {
                    if (endLoop()) {
                        while (ip != '[') {
                            i--;
                            ip = getCharAtIndex(code, i);
                        }
                    }
                }
                default -> {

                }
            }
            i++;
        }
    }

    /**
     * Move the pointer to the left (decrement).
     * Throws an exception on buffer underflow.
     */
    public static void moveLeft() {
        if (ptr > 0) {
            ptr--;
        } else {
            throw new RuntimeException("Buffer underflow");
        }
    }

    /**
     * Move the pointer to the right (increment).
     * Throws an exception on buffer overflow.
     */
    public static void moveRight() {
        if (ptr < arr.length) {
            ptr++;
        } else {
            throw new RuntimeException("Buffer overflow");
        }
    }

    /**
     * Increment the current memory cell value.
     */
    public static void inc() {
        arr[ptr]++;
    }

    /**
     * Decrement the current memory cell value.
     */
    public static void dec() {
        arr[ptr]--;
    }

    /**
     * Read a character from the user and store its ASCII value in the current memory cell.
     */
    public static void input() {
        arr[ptr] = scanner.next().charAt(0);
    }

    /**
     * Output the ASCII character of the current memory cell.
     */
    public static void output() {
        int i = arr[ptr];
        System.out.print((char) i);
    }

    /**
     * Check if the current memory cell value is zero.
     *
     * @return True if the current memory cell value is zero, otherwise false.
     */
    public static boolean startLoop() {
        return arr[ptr] == 0;
    }

    /**
     * Check if the current memory cell value is not zero.
     *
     * @return True if the current memory cell value is not zero, otherwise false.
     */
    public static boolean endLoop() {
        return arr[ptr] != 0;
    }
}
