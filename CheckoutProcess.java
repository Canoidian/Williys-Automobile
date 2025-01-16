import java.util.Scanner;
import java.text.NumberFormat;
import java.io.*;
import java.util.InputMismatchException;

public class CheckoutProcess {
    
    // PreCondition: receive price and discount and minNum to check the requirements and computes the price
    // PostCondition: returns the new price of the total
    public static double dollarOff(double price, double discount, int minNum){
        if (price >= minNum){
            price = price - discount;
        }
        else{
            System.out.println("Your not qualify for this discount!");
        }
        

        return price;
    }
    // PreCondition: Calcautes for the new price using the discount
    // PostCondition: returns the new price of the total 
    public static double percentOff(double price, double discount){
        double newPrice = price - (price * discount);
        
        return newPrice;
    }

    // PreCondition: Display options to the user
    // PostCondition: Returns the choice given from the user
    public static int discountOption(int choice){
        choice = Input.intValid("1. 30% Discount\n2. 20% Discount\n3. 10% Discount\n4. 5% Discount\n5. $100000 off the total price over $500000 \n6. $25000 off the total price over 100000$ ", 1, 6);

        // return the choice
        return choice;
    }

    // Precondition: Ask the user if they want discount or invoice for the total
    // Postcondition: Nothing to return
    public static void checkOut() throws IOException{
        
        //Declares variable and objects and arrays
        int choice = 0, newChoice = 0;
        double newTotal = 0.0, total = 0.0, price = 0.0, newTax = 0.0, tax = 0.0, shipping = 0.0;
        boolean loop = false;
        String cars = "";
        String[] province = new String[50];
        String[] structure = new String[50];
        double[] hst = new double[50];
        double[] gst = new double[50];
        double[] pst = new double[50];
        int[] ship = new int[50];
        NumberFormat currency = NumberFormat.getCurrencyInstance();


        //Create files
        File f = new File("cart.txt");
        Scanner s = new Scanner(f);
        s.useDelimiter(",");
        PrintWriter invoice = new PrintWriter("invoice.txt");
                        
        //add to the file
        invoice.print("Company name: Williys Automobile\t\t\t\t\t\t\tDate:1/20/2025\nCompany Address: 51 AutoDrive St, Guelph, Ontario Canada\nPhone number: (666) 123-5555\nEmail Address: wiilly@gmail.com"); //Write into the invoice
        invoice.println("\n\n\nDescription:\t\t\t  Price:"); //Write to the file

        //Scan through the cart file
        while (s.hasNext()){
            cars = s.next();
            price = s.nextDouble();
            invoice.printf("%-15s%18s\n", cars,currency.format(price));
            total = total + price; //get the total
            s.nextLine();
        }
        s.close(); //closes the cart file

        File p = new File("province.txt");
        Scanner s1 = new Scanner(p);
        s1.useDelimiter(",");
        //Scan through the province file
        while(s.hasNext){}
            province[i] = s1.next();
            structure[i] = s1.next();
            hst[i] = s1.nextDouble();
            gst[i] = s1.nextDouble();
            pst[i] = s1.nextDouble();
            ship[i] = s1.nextInt();
        }
        //Ask the user what province they are in
        System.out.println("1. Ontario\n2.Prince Edward island\n3.Nova Scotia\n4.New Brunswick\n5.Newfoundland & Labrador\n6.British Columbia\n7.Manitoba\n8.Alberta\n9.Quebec\n10.Saskatchewan");
        choice = Input.intValid("Please pick the province your in (number):", 1, 10);
        
        

        while(!loop) { //Loops back to the options
            
            
            //Get input from the user
            System.out.println("1. Discount option\n2. Checkout");
            choice = Input.intValid("Please pick an option: ");

            switch (choice){
                case 1:
                    newChoice = discountOption(choice); //displays the discount menus

                        switch(newChoice){ //takes the total and discounts it
                            case 1:
                                    total = percentOff(total,0.30);
                                    break;
                            case 2:
                                    total = percentOff(total,0.20);
                                    break;
                            case 3:
                                    total = percentOff(total,0.10);
                                    break;
                            case 4:
                                    total = percentOff(total,0.05);
                                    break;
                            case 5:
                                    total = dollarOff(total,100000,500000);
                                    break;
                            case 6:
                                    total = dollarOff(total,25000,100000);
                                    break;
                        }
                        break;
                case 2:
                        
                        
                        newTax = total * tax; //calculates the tax
                        newTotal = total + newTax; //calculates the total with tax
                        
                        invoice.printf("%-15s%20s\n", "\nSubtotal:", currency.format(total)); //prints the subtotal
                        invoice.printf("%-15s%19s\n", "Tax:", currency.format(newTax)); //prints the tax
                        invoice.printf("%-15s%19s\n", "Total:", currency.format(newTotal)); //prints the total
                    
                        loop = true; // Set the loop to true to end the options
                        invoice.close(); //saves and closes the file
                        System.out.println("Thank you for checking out");
                        break;
                default:
                        System.out.println("Invaild option");
            }
        }
    } 

    public static double getValidDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
