import java.util.Scanner;
import java.util.List;

public class Input {
    public static Scanner input = new Scanner(System.in);
    public static final String C_RED = "\u001B[31m";
    public static final String C_RESET = "\u001B[0m";

    //! Integer Input

    // Checks for valid integer input from the usr, with the prompt entered by the usr
    public static int intValid(String prompt) {
        int num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                input.nextLine();
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }

        return num;
    }

    // Checks for valid integer input from the usr between two values, with the prompt, min and max entered by the usr
    public static int intValid(String prompt, int min, int max) {
        int num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                input.nextLine();
                
                if (num >= min && num <= max) {
                    break;
                } else {
                    System.err.println(C_RED + "Invalid Input!" + C_RESET);
                }

            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }

        return num;
    }

    // Checks for valid int input from the usr with a min value, with the prompt and min entered by the usr
    public static double intValid(String prompt, double min) {
        int num = 0;
        
        while (true) {
            System.out.print(prompt);
        if (input.hasNextInt()) {
            num = input.nextInt();
            input.nextLine();

            if (num >= min) {
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
            }
        } else {
            System.err.println(C_RED + "Invalid Input!" + C_RESET);
            input.next();
        }
        }

        return num;
    }

    //! Double Input

    // Checks for valid double input from the usr, with the prompt entered by the usr
    public static double doubleValid(String prompt) {
        double num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                num = input.nextDouble();
                input.nextLine();
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
            
        }

        return num;
    }

    // Checks for valid double input from the usr between two values, with the prompt, min and max entered by the usr
    public static double doubleValid(String prompt, double min, double max) {
        double num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                num = input.nextDouble();
                input.nextLine();

                if (num >= min && num <= max) {
                    break;
                } else {
                    System.err.println(C_RED + "Invalid Input!" + C_RESET);
                }

            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }

        return num;
    }

    // Checks for valid double input from the usr with a min value, with the prompt and min entered by the usr
    public static double doubleValid(String prompt, double min) {
        double num = 0;

        while (true) {
            System.out.print(prompt);
        if (input.hasNextDouble()) {
            num = input.nextDouble();
            input.nextLine();

            if (num >= min) {
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
            }
        } else {
            System.err.println(C_RED + "Invalid Input!" + C_RESET);
            input.next();
        }
        }

        return num;
    }

    //! String Input

    // Checks for valid string input from the usr, with the prompt entered by the usr
    public static String stringValid(String prompt) {
        String str = "";

        while (true) {
            System.out.print(prompt);
            if (input.hasNextLine()) {
                str = input.nextLine();
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }

        return str;
    }

    // Checks for valid string input from the usr with a prompt, ensuring the input is not empty
    public static String nonEmptyStringValid(String prompt) {
        String str = "";

        do {
            System.out.print(prompt);
            str = input.nextLine();
            if (str.trim().isEmpty()) {
                System.out.println(C_RED + "Error: Input cannot be empty. Please enter a valid string." + C_RESET);
            }
        } while (str.trim().isEmpty());

        return str;
    }

    //! Other Input
    // Checks for valid boolean input from the usr, with the prompt entered by the usr
    public static boolean booleanValid(String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim().toLowerCase();
            if (inputStr.equals("true") || inputStr.equals("false")) {
                return Boolean.parseBoolean(inputStr);
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter 'true' or 'false'." + C_RESET);
            }
        }
    }

    // Checks for valid character input from the usr, with the prompt entered by the usr
    public static char charValid(String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputStr = input.nextLine();
            if (inputStr.length() == 1) {
                return inputStr.charAt(0);
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter a single character." + C_RESET);
            }
        }
    }

    // Checks for valid string input from the usr with a specific length, with the prompt and length entered by the usr
    public static String stringValid(String prompt, int length) {
        while (true) {
            System.out.print(prompt);
            String str = input.nextLine();
            if (str.length() == length) {
                return str;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter a string of length " + length + "." + C_RESET);
            }
        }
    }

    // Checks for valid email input from the usr, with the prompt entered by the usr
    public static String emailValid(String prompt) {
        while (true) {
            System.out.print(prompt);
            String email = input.nextLine();
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter a valid email address." + C_RESET);
            }
        }
    }

    // Checks for valid date input from the usr in the format "yyyy-MM-dd", with the prompt entered by the usr
    public static String dateValid(String prompt) {
        while (true) {
            System.out.print(prompt);
            String date = input.nextLine();
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return date;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter a date in the format yyyy-MM-dd." + C_RESET);
            }
        }
    }

    // Checks for valid integer input from the usr and adds it to the list
    public static void intValid(String prompt, List<Integer> list) {
        int num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                input.nextLine();
                list.add(num);
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }
    }

    // Checks for valid double input from the usr and adds it to the list
    public static void doubleValid(String prompt, List<Double> list) {
        double num = 0;

        while (true) {
            System.out.print(prompt);
            if (input.hasNextDouble()) {
                num = input.nextDouble();
                input.nextLine();
                list.add(num);
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }
    }

    // Checks for valid string input from the usr and adds it to the list
    public static void stringValid(String prompt, List<String> list) {
        String str = "";

        while (true) {
            System.out.print(prompt);
            if (input.hasNextLine()) {
                str = input.nextLine();
                list.add(str);
                break;
            } else {
                System.err.println(C_RED + "Invalid Input!" + C_RESET);
                input.next();
            }
        }
    }

    // Checks for valid boolean input from the usr and adds it to the list
    public static void booleanValid(String prompt, List<Boolean> list) {
        while (true) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim().toLowerCase();
            if (inputStr.equals("true") || inputStr.equals("false")) {
                list.add(Boolean.parseBoolean(inputStr));
                return;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter 'true' or 'false'." + C_RESET);
            }
        }
    }

    // Checks for valid character input from the usr and adds it to the list
    public static void charValid(String prompt, List<Character> list) {
        while (true) {
            System.out.print(prompt);
            String inputStr = input.nextLine();
            if (inputStr.length() == 1) {
                list.add(inputStr.charAt(0));
                return;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter a single character." + C_RESET);
            }
        }
    }

    // Checks for valid yes/no input from the usr, with the prompt entered by the usr 
    public static boolean yesNoValid(String prompt) {
        while (true) {
            System.out.print(prompt);
            String inputStr = input.nextLine().trim().toLowerCase();
            if (inputStr.equals("y")) {
                return true;
            } else if (inputStr.equals("n")) {
                return false;
            } else {
                System.err.println(C_RED + "Invalid Input! Please enter 'y' for yes or 'n' for no." + C_RESET);
            }
        }
    }
}