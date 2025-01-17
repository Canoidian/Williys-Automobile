import java.util.Scanner;
import java.util.NoSuchElementException;
import java.io.*;

public class CartHandler {
    // Precondition: Takes a file and reads all lines in it
    // Postcondition: Returns the number of lines
    public static int getFileSize(File f) throws IOException {
        // Objects and Variables
        Scanner sc = new Scanner(f);
        sc.useDelimiter(",");

        int lineNum = 0;
        while (sc.hasNext()) {
            lineNum++;
            sc.nextLine();
        }

        return lineNum;
    }

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
            if (currentCart[i] != null) {
                outputFile.println(currentCart[i]);
            }
        }

    // Adds modelName to cart
    price = getModelPrice(inv, modelName);
    outputFile.println(modelName + "," + price + ",");

    // Save file
    outputFile.close();
    System.out.println("Successfully added to cart!");
}

    public static void viewCart() throws IOException{
        // Objects and Variables
        File cart = new File("cart.txt");
        Scanner sc;

        String[] items = new String[100];

        String item;
        int index = 1, option, price, fileSize = getFileSize(cart);

        // Checks if cart is empty
        if (fileSize <= 0) {
            System.out.println(Other.Colour.RED + "Your Cart is empty! Please add models to your cart." + Other.Colour.RESET);
        }
        else {
            // Reads file and outputs cart
            Other.clear();
            System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles" + Other.Colour.RESET);
            try {
                sc = new Scanner(cart);
                sc.useDelimiter(",");
                while (sc.hasNext()) {
                    item = sc.next();
                    price = sc.nextInt();

                    items[index] = item;

                    System.out.printf(index + ". %-30s Price:  $%,d%n", item, price);
                    index++;

                    sc.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Cart is empty.");
            }

            // Options
            System.out.println("\n1. Remove item\n2. Checkout\n3. Back");
            option = Input.intValid("Enter an option (1-3): ", 1, 3);

            switch (option) {
                case 1: // Remove item
                    option = Input.intValid("Select item number that you would like to remove: ", 1, index);
                    removeItem(cart, items[option]);
                    break;
            
                case 2: // Checkout
                    CheckoutProcess.checkOut();
                    break;
                case 3:
                    AutoMain.main(null);
                    break;
                default:
                    break;
            }
        }
    }

    public static void removeItem(File cart, String model) throws IOException {
        // objects and vars
        Scanner sc = new Scanner(cart);
        sc.useDelimiter(",");

        PrintWriter outputFile;

        String[] cartItems = new String[getFileSize(cart)], newCartItems = new String[cartItems.length-1];
        int[] cartPrices = new int[getFileSize(cart)], newCartPrices = new int[cartPrices.length-1];

        int count = 0, priceRemove = 0;

        // Copys items in cart to two arrays
        while(sc.hasNext()) {
            cartItems[count] = sc.next();
            cartPrices[count] = sc.nextInt();
            count++;
            sc.nextLine();
        }

        // Removes items
        for (int i = 0; i < cartItems.length; i ++) {
            System.out.println(cartItems[i] + " || " + cartPrices[i]);
        }

        for (int i = 0, v = 0; i < cartItems.length; i++) {
            if (!cartItems[i].equals(model)) {
                newCartItems[v] = cartItems[i];
                v++;
            }
            else {
                priceRemove = cartPrices[i];
            }
        }

        // Rewrites cart file without the removed item
        outputFile = new PrintWriter(cart);
        for (int i = 0; i < count-1; i++) {
            outputFile.println(newCartItems[i] + "," + newCartPrices[i] + ",");
        }

        // Saves new cart
        outputFile.close();
        System.out.println("Cart saved successfully!");
    }

    public static void displayCarInfo(File inv, String modelTier, int car) throws IOException {
        // Objects and Variables
        boolean addToCart = false;
        String modelName = "Bruce Lee Car"; // placeholder

        // Find car model in respective tier and outputs information
        Other.clear();
        System.out.println(Other.Colour.BACKGROUND_CYAN + "Willy's Automobiles" + Other.Colour.RESET);
        switch (modelTier) {
            case "Super":
                // Output information about car
                getCarInfo(inv, car, 2018);
                modelName = getModelName(inv, car);

                // Prompts user if they want to add to cart
                addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                break;
            case "High":
                getCarInfo(inv, car + 6, 2018);
                modelName = getModelName(inv, car + 6);
                addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                break;
            case "Mid":
                getCarInfo(inv, car + 12, 2018);
                modelName = getModelName(inv, car + 12);
                addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                break;
            case "Low":
                getCarInfo(inv, car + 16, 2018);
                modelName = getModelName(inv, car + 16);
                addToCart = Input.yesNoValid("Add to cart? (y/n): ");
                break;
            default:
                System.out.println("ERROR");
        }

        // Checks if user added to cart
        if (addToCart) {
            printToCart(inv, modelName);
        }
    }
}
