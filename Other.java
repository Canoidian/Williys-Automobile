public class Other {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Reverses a given string and returns it
    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // Generates a random number within a specified range (inclusive)
    public static int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void type(String input, int speed){

        int len = input.length();

        System.out.println(" ");

        try {
            for (int i = 0; i < len; i++) {

                Thread.sleep(speed);

                System.out.print(input.charAt(i));
            }
        }
            catch (Exception sleep) {
                System.out.println(sleep);
            }
    }

    public static class Colour {
        public static final String RESET = "\u001B[0m";
        public static final String BOLD = "\u001B[1m";
        public static final String UNDERLINE = "\u001B[4m";

        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";

        public static final String BOLD_BLACK = "\u001B[1;30m";
        public static final String BOLD_RED = "\u001B[1;31m";
        public static final String BOLD_GREEN = "\u001B[1;32m";
        public static final String BOLD_YELLOW = "\u001B[1;33m";
        public static final String BOLD_BLUE = "\u001B[1;34m";
        public static final String BOLD_PURPLE = "\u001B[1;35m";
        public static final String BOLD_CYAN = "\u001B[1;36m";
        public static final String BOLD_WHITE = "\u001B[1;37m";

        public static final String UNDERLINE_BLACK = "\u001B[4;30m";
        public static final String UNDERLINE_RED = "\u001B[4;31m";
        public static final String UNDERLINE_GREEN = "\u001B[4;32m";
        public static final String UNDERLINE_YELLOW = "\u001B[4;33m";
        public static final String UNDERLINE_BLUE = "\u001B[4;34m";
        public static final String UNDERLINE_PURPLE = "\u001B[4;35m";
        public static final String UNDERLINE_CYAN = "\u001B[4;36m";
        public static final String UNDERLINE_WHITE = "\u001B[4;37m";

        public static final String BACKGROUND_BLACK = "\u001B[40m";
        public static final String BACKGROUND_RED = "\u001B[41m";
        public static final String BACKGROUND_GREEN = "\u001B[42m";
        public static final String BACKGROUND_YELLOW = "\u001B[43m";
        public static final String BACKGROUND_BLUE = "\u001B[44m";
        public static final String BACKGROUND_PURPLE = "\u001B[45m";
        public static final String BACKGROUND_CYAN = "\u001B[46m";
        public static final String BACKGROUND_WHITE = "\u001B[47m";

        public static final String DIM_BLACK = "\u001B[2;30m";
        public static final String DIM_RED = "\u001B[2;31m";
        public static final String DIM_GREEN = "\u001B[2;32m";
        public static final String DIM_YELLOW = "\u001B[2;33m";
        public static final String DIM_BLUE = "\u001B[2;34m";
        public static final String DIM_PURPLE = "\u001B[2;35m";
        public static final String DIM_CYAN = "\u001B[2;36m";
        public static final String DIM_WHITE = "\u001B[2;37m";

        public static final String BLINK_BLACK = "\u001B[5;30m";
        public static final String BLINK_RED = "\u001B[5;31m";
        public static final String BLINK_GREEN = "\u001B[5;32m";
        public static final String BLINK_YELLOW = "\u001B[5;33m";
        public static final String BLINK_BLUE = "\u001B[5;34m";
        public static final String BLINK_PURPLE = "\u001B[5;35m";
        public static final String BLINK_CYAN = "\u001B[5;36m";
        public static final String BLINK_WHITE = "\u001B[5;37m";

        public static final String REVERSED_BLACK = "\u001B[7;30m";
        public static final String REVERSED_RED = "\u001B[7;31m";
        public static final String REVERSED_GREEN = "\u001B[7;32m";
        public static final String REVERSED_YELLOW = "\u001B[7;33m";
        public static final String REVERSED_BLUE = "\u001B[7;34m";
        public static final String REVERSED_PURPLE = "\u001B[7;35m";
        public static final String REVERSED_CYAN = "\u001B[7;36m";
        public static final String REVERSED_WHITE = "\u001B[7;37m";
    }
}