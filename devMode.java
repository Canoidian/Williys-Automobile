import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

/**
 * Developer mode class for managing car inventory
 * Allows editing and adding/deleting cars to the inventory system
 */
public class devMode {
    /**
     * Fills arrays with car data from inventory file
     * Preconditions:
     * - Arrays must be initialized with sufficient size
     * - inv.txt must exist and be readable
     * - File must have data in format: "model,price"
     * 
     * Postconditions:
     * - Arrays will be filled with data from file
     * - Returns total number of cars loaded
     */
    public static int arrayFill(String[] carModels, int[] carPrices, int count, Scanner sc, File inv) {
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");

            if (parts.length >= 2) {
                carModels[count] = parts[0].trim();
                carPrices[count] = Integer.parseInt(parts[1].trim());
                count++;
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.printf(i + 1 + ". " + "Model: %-20s Price: $%,d%n", carModels[i], carPrices[i]);
        }

        return count;
    }

    /**
     * Edits existing car details in inventory
     * Preconditions:
     * - itemToEdit must be valid index
     * - Arrays must contain valid data
     * - count must accurately reflect number of cars
     * 
     * Postconditions:
     * - Selected car details will be updated
     * - Changes will be saved to inv.txt
     */
    public static void edit(File inv, int itemToEdit, String[] carModels, int[] carPrices, int count) throws FileNotFoundException {
        Scanner sc = new Scanner(inv);
        boolean confirm = false;

        Other.clear();
        if (itemToEdit > 0) {
            itemToEdit -= 1;
        }
        System.out.printf("\nCurrent Model: %-20s Current Price: $%,d%n", carModels[itemToEdit], carPrices[itemToEdit]);

        System.out.println("\n1. Edit car model name");
        System.out.println("2. Edit car price");
        System.out.println("3. Edit both");
        System.out.println("4. Delete Car");
        int choice = Input.intValid("Enter your choice (1-3): ", 1, 3);

        switch (choice) {
            case 1:
                System.out.print("Enter new model name: ");
                Scanner input = new Scanner(System.in);
                carModels[itemToEdit] = input.nextLine();
                break;
            case 2:
                carPrices[itemToEdit] = Input.intValid("Enter new price: ");
                break;
            case 3:
                System.out.print("Enter new model name: ");
                Scanner input2 = new Scanner(System.in);
                carModels[itemToEdit] = input2.nextLine();
                carPrices[itemToEdit] = Input.intValid("Enter new price: ");
                break;
            case 4:
                confirm = Input.yesNoValid("Are you sure you want to delete this car? (y/n): ");
                if (confirm) {
                    carModels[itemToEdit] = null;
                    carPrices[itemToEdit] = 0;
                    count--;
                }
                break;
        }

        writeToFile(carModels, carPrices, count);
        System.out.println("Changes saved successfully!");
    }

    /**
     * Adds a new car to the inventory
     * Preconditions:
     * - Arrays must have space for new entry
     * - count must be less than array length
     * 
     * Postconditions:
     * - New car will be added to arrays
     * - Changes will be saved to inv.txt
     * - Returns updated count
     */
    public static int addNewCar(int[] carPrices, String[] carModels, int count) {
        if (count >= carModels.length) {
            System.out.println("Error: Inventory is full. Cannot add more cars.");
            return count;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the new car model: ");
        String newModel = input.nextLine();
        
        int newPrice = Input.intValid("Enter the price for " + newModel + ": ");
        
        carModels[count] = newModel;
        carPrices[count] = newPrice;
        count++;
        
        writeToFile(carModels, carPrices, count);
        System.out.println("New car added successfully!");
        
        return count;
    }

    /**
     * Writes current inventory to file
     * Preconditions:
     * - Arrays must contain valid data
     * - count must accurately reflect number of cars
     * 
     * Postconditions:
     * - inv.txt will be updated with current inventory
     */
    public static void writeToFile(String[] carModels, int[] carPrices, int count) {
        try {
            FileWriter writer = new FileWriter("inv.txt");
            PrintWriter printWriter = new PrintWriter(writer);
            
            for (int i = 0; i < count; i++) {
                if (carModels[i] != null) {
                    printWriter.println(carModels[i] + "," + carPrices[i] + ",");
                }
            }
            
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Main method to run the developer mode interface
     * 
     * Preconditions:
     * - inv.txt must exist and be accessible
     * - Input class must be available for validation
     * 
     * Postconditions:
     * - Allows user to edit and manage inventory
     * - All changes are saved to inv.txt
     */
    public static void main(String[] args) throws IOException {
        //Other.clear();

        File inv = new File("inv.txt");
        Scanner sc = new Scanner(inv);

        Input.stringValid("Please enter your password: ");

        String[] carModels = new String[100];
        int[] carPrices = new int[100];
        int count = 0, itemToEdit, max;
        boolean again = true;

        count = arrayFill(carModels, carPrices, count, sc, inv);
        max = count;

        while (again) {
            //Other.clear();

            System.out.println("\n1. Edit existing car price/model");
            System.out.println("2. Add new car");
            System.out.println("3. Exit");
            int choice = Input.intValid("Enter your choice (1-3): ", 1, 3);

            switch (choice) {
                case 1:
                    itemToEdit = Input.intValid("\nWhat item would you like to edit (eg. 1, 2, 3): ", 1, max);
                    edit(inv, itemToEdit, carModels, carPrices, count);
                    break;
                case 2:
                    count = addNewCar(carPrices, carModels, count);
                    max = count;
                    break;
                case 3:
                    again = false;
                    break;
            }

            // Refresh the scanner for next iteration
            sc = new Scanner(inv);
        }
        sc.close();
    }
}