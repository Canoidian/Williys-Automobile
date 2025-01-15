import java.util.Scanner;
import java.io.*;

public class CartHandler {
    // Precondition: Takes file and reads up until a certain line and grabs car model name
    // Postcondition: Returns that car model name for it to display for the user
    public static String getModelName(File inv, int lineNumber) throws IOException {
        // Objects and Variables
        Scanner sc = new Scanner(inv);
        sc.useDelimiter(",");

        int increment = 1;
        String modelName = "ERROR: CAR NOT FOUND"; // Placeholder

        // Finds name of car model
        while (sc.hasNextLine()) {
            if (increment == lineNumber) { // Loops over and over again until it reads the right line
                modelName = sc.next();
                break;
            }
            sc.nextLine();
            increment++;
        }

        return modelName;
    }

    // Precondition: Takes file and reads up until a certain line and grabs car model price
    // Postcondition: Returns car price for it to display for the user
    public static int getModelPrice(File inv, String modelName) throws IOException {
        // Objects and Variables
        Scanner sc = new Scanner(inv);
        sc.useDelimiter(",");

        int modelPrice = -1; // Placeholder

        // Finds price of car model
        while (sc.hasNext()) {
            if (sc.next().equals(modelName)) { // Loops over and over again until it reads the right line
                modelPrice = sc.nextInt();
                return modelPrice;
            }
            sc.nextLine();
        }

        return modelPrice;
    }

    public static void getCarInfo(File inv, int line, int dateMin) throws IOException {
        // Objects and Variables
        String modelName = "ERROR: NO CAR FOUND";
        int price = -1, date = -1;

        modelName = getModelName(inv, line);
        price = getModelPrice(inv, modelName);
        date = Other.generateRandomNumber(dateMin, 2025);

        // Print info
        System.out.println("Model: " + modelName + "\nPrice: $" + price + "\nDate: " + date + "\n");

    }

    public static void printToCart(File inv, String modelName) throws IOException {
        // Objects and Variables
        File cartFile = new File("cart.txt");

        PrintWriter outputFile;
        Scanner sc = new Scanner(cartFile);

        String[] currentCart = new String[100];
        int index = 0, price;
        String item;

        // Copys data of file into array
        while (sc.hasNext()) {
            item = sc.nextLine();
            currentCart[index] = item;
            index++;
        }

        // Rewrites to file again
        outputFile = new PrintWriter("cart.txt");
        for (int i = 0; i < currentCart.length; i++) {
            //System.out.println(currentCart[i]);
            if (currentCart[i] != null) {
                outputFile.println(currentCart[i]);
            }
        }

    // Adds modelName to cart
    price = getModelPrice(inv, modelName);
    outputFile.println(modelName + "," + price + ",");

    // Save file
    outputFile.close();
}

    public static void viewCart(File cart, Scanner sc) throws IOException{
        // Objects and Variables
        String item;
        int index = 1;

        // Reads file and outputs cart
        try {
            sc = new Scanner(cart);
            while (sc.hasNext()) {
                item = sc.nextLine();
                System.out.println(index + ". " + item);
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cart is empty.");
        }
    }

    public static void displayCarInfo(File inv, String modelTier, int car) throws IOException {
        // Objects and Variables
        boolean addToCart = false;
        String modelName = "Bruce Lee Car"; // placeholder

        // Find car model in respective tier and outputs information
        Other.clear();
        switch (modelTier) {
            case "High":
                switch (car) {
                    case 6:
                    // Output information about car
                    getCarInfo(inv, 8, 2008);

                    // Prompts user if they want to add to cart
                    addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                    break;
                    case 5:
                    // Output information about car
                    getCarInfo(inv, 2, 2008);

                    // Prompts user if they want to add to cart
                    addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                }
        }

        // Checks if user added to cart
        if (addToCart) {
            printToCart(inv, modelName);
        }
    }
}
