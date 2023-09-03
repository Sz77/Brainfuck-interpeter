import java.util.Scanner;

public class Brainfuck {
    static Scanner scanner = new Scanner(System.in);
    static int ptr = 0;
    static int[] arr = new int[30000];

    public static void main(String[] args) {
        String code = "e++++++++++[>+++++++>++++++seteyr++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
        execute(code);
    }

    public static char getCharAtIndex(String code, int i) {
        return code.charAt(i);
    }

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

    public static void moveLeft() {
        if (ptr > 0) {
            ptr--;
        } else {
            throw new RuntimeException("buffer underflow");
        }
    }

    public static void moveRight() {
        if (ptr < arr.length) {
            ptr++;
        } else {
            throw new RuntimeException("buffer overflow");
        }
    }

    public static void inc() {
        arr[ptr]++;
    }

    public static void dec() {
        arr[ptr]--;
    }

    public static void input() {
        arr[ptr] = scanner.next().charAt(0);
    }

    public static void output() {
        int i = arr[ptr];
        System.out.print((char) i);
    }

    public static boolean startLoop() {
        return arr[ptr] == 0;
    }

    public static boolean endLoop() {
        return arr[ptr] != 0;
    }
}
