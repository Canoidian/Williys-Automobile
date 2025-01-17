/*
 * Authors: Kevin, Tony, William
 * Date: January 6th, 2025
 * Description: BLAH BLAH BLAh
*/

import java.util.Scanner;
import java.io.*;

public class AutoMain {
    public static void printModels(File inv, String range, int start, int end) throws IOException {
        // Objects and Variables
        Scanner sc = new Scanner(inv);
        sc.useDelimiter(",");

        String[] carModels = new String[100];
        int increment = 0;

        System.out.println("-----------------" + Other.Colour.CYAN + range + Other.Colour.RESET +  "-----------------");

        // Add car models to array
        while (sc.hasNext()) {
            carModels[increment] = sc.next();
            sc.nextLine();
            increment++;
        }

        // Output all car models
        increment = 1;
        for (int i = start; i < end; i++) {
            System.out.println(increment + ". " + carModels[i]);
            increment++;
        }
    }
    public static void viewModels(File inv) throws IOException {
        // Objects and Variables
        Scanner sc = new Scanner(inv);
        sc.useDelimiter(",");

        int option = 0, increment;
        String model = "Bruce Lee Car"; // Placeholder name

        // Prints options
        Other.clear();
        System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles!" + Other.Colour.RESET);
        System.out.println("\n1. Low Range Cars\n2. Mid Range Cars\n3. High Range Cars\n4. Super Cars\n5. Back");
        option = Input.intValid("\nEnter an option (1-5): ", 1, 5);

        // Options
        Other.clear();
        System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles!" + Other.Colour.RESET);
        switch (option) {
            case 5: // Exit
                break;
            case 4: // Super cars
                printModels(inv, " SUPER CARS ", 0, 6);

                // Prompts user
                option = Input.intValid("\nChoose a model: ", 1, 6);
                CartHandler.displayCarInfo(inv , "Super", option);
                break;
            case 3: // High range models
                printModels(inv, " HIGH RANGE CARS ", 6, 12);

                option = Input.intValid("\nChoose a model: ", 1, 6);
                CartHandler.displayCarInfo(inv , "High", option);
                break;
            case 2: // Mid Range models
                printModels(inv, " MID RANGE CARS ", 12, 16);

                option = Input.intValid("\nChoose a model: ", 1, 4);
                CartHandler.displayCarInfo(inv , "Mid", option);
                break;
            case 1: // Low range models
                printModels(inv, " LOW RANGE CARS ", 16, 20);

                option = Input.intValid("\nChoose a model: ", 1, 4);
                CartHandler.displayCarInfo(inv , "Low", option);
                break;
            default: 
            break;
        }
        
    }
    public static void main (String[] args) throws IOException{
        Other.clear(); // To allow for clean start of terminal

        // Objects and Variables
        int menuChoices;
        boolean again = true;

        File inv = new File("inv.txt");

        //! Menu
        while (again) {
            //Other.clear();
            System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles" + Other.Colour.RESET);

            System.out.println(Other.Colour.RESET + "\n1. View Models\n2. View Cart\n3. Checkout\n4. Console\n5. Quit");
            menuChoices = Input.intValid("\nEnter an option (1-5): ", 1, 5);
            switch (menuChoices) {
                case 1:
                    viewModels(inv);
                    break;
                case 2:
                    CartHandler.viewCart();
                    break;
                case 3:
                    CheckoutProcess.checkOut();
                    break;
                case 4:
                    devMode.main(null);
                    break;
                case 5:
                    again = false;
                    break;
                default:
                    System.err.println(Other.Colour.RED + "Invalid choice. Please try again.");
                    break;
            }
        }
    }
}