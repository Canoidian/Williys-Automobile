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
        File f = new File("inv.txt");
        Scanner sc = new Scanner(f);
        sc.useDelimiter(",");

        String[] carModels = new String[100];
        int increment = 0;

        System.out.println("-----------------" + Other.Colour.CYAN + range + Other.Colour.RESET +  "-----------------");

        // Add car models to array
        while (sc.hasNext()); {
            carModels[increment] = sc.next();
            System.out.println("NINJA ATTACK: " + carModels[increment]);
            increment++;
        }

        for (int i = 0; i < carModels.length; i++) {
            System.out.println(carModels[i]);
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
        System.out.println("1. Low Range Cars\n2. Mid Range Cars\n3. High Range Cars\n4. Super Cars\n5. Back");
        option = Input.intValid("Enter an option: ", 1, 5);

        // Options
        Other.clear();
        switch (option) {
            case 5: // Exit
                break;
            case 4: // Super cars
                printModels(inv, " SUPER CARS ", 1, 6);

                // Prompts user
                option = Input.intValid("\nChoose a model: ", 1, 6);
                CartHandler.displayCarInfo(inv , "High", 5);
                break;
            case 3: // High range models
                printModels(inv, " HIGH RANGE CARS ", 8, 13);

                option = Input.intValid("\nChoose a model: ", 1, 6);
                CartHandler.displayCarInfo(inv , "High", 5);
                break;
            case 2: // Mid Range models
                printModels(inv, " MID RANGE CARS ", 15, 18);

                option = Input.intValid("\nChoose a model: ", 1, 4);
                CartHandler.displayCarInfo(inv , "High", 5);
                break;
            case 1: // Low range models
                printModels(inv, " LOW RANGE CARS ", 20, 23);

                option = Input.intValid("\nChoose a model: ", 1, 4);
                CartHandler.displayCarInfo(inv , "High", 5);
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
            System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles");

            System.out.println(Other.Colour.RESET + "\n1. View Models\n2. Checkout\n3. Quit");
            menuChoices = Input.intValid("\nPlease choose an option: ");
            switch (menuChoices) {
                case 1:
                    viewModels(inv);
                    break;
                case 2:
                    CheckoutProcess.checkOut();
                    break;
                case 3:
                    again = false;
                    break;
                default:
                    System.err.println(Other.Colour.RED + "Invalid choice. Please try again.");
                    break;
            }
        }
    }
}