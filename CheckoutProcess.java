import java.util.Scanner;
import java.text.NumberFormat;
import java.io.*;

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
        int choice, newChoice,pick;
        double newTotal = 0.0, total = 0.0,  price = 0.0,  newTax = 0.0,  tax = 0.0,  shipping = 0.0;
        boolean loop = false;
        String cars = "";
        int k = 0;
        String receipt = "";
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
    invoice.print("Company ne: Williys Automobile\t\t\t\t\t\t\tDate:1/20/2025\nCompany Address: -5555\nEmail Address: wiilly@gmail.com"); //Write into the invoicealign to the left 15        
    invoice.println("\nItems:\t\t\t  Price:"); //Write to the file        
    invoice.printf("%-15s","\n************************************");  //write the stars        
    invoice.println("\n\nItems:\t\t\t  Price:"); //Write to the file

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
        while(s1.hasNext()){
            province[k] = s1.next();
            structure[k] = s1.next();
            hst[k] = Double.parseDouble(s1.next()); //Convert the string into a double s.nextDouble() doesn't work
            gst[k] = Double.parseDouble(s1.next());
            pst[k] = Double.parseDouble(s1.next());
            ship[k] = s1.nextInt();
            k++;
            s1.nextLine();
        }
        s1.close(); //Closes the file

        
        //Ask the user what province they are in
        System.out.println("1. Ontario\n2. Prince Edward Island\n3. Nova Scotia\n4. New Brunswick\n5. Newfoundland & Labrador\n6. British Columbia\n7. Manitoba\n8. Alberta\n9. Quebec\n10. Saskatchewan");
        pick = Input.intValid("Please pick the province your in (number) will affect the tax and shipping:", 1, 10);
        
        //code for the tax and the shipping depending on where they live
        switch(pick){
             
            case 1:
                tax = hst[0];
                shipping = ship[0];
                break;
            case 2:
                tax = hst[1];
                shipping = ship[1];
                break;
            case 3:
                tax = hst[2];
                shipping = ship[2];
                break;
            case 4:
                tax = hst[3];
                shipping = ship[3];
                break;
            case 5:
                tax = hst[4];
                shipping = ship[4];
                break;
            case 6:
                tax = gst[5] + pst[5];
                shipping = ship[5];
                break;
            case 7:
                tax = gst[6] + pst[6];
                shipping = ship[6];
                break;
            case 8:
                tax = gst[7];
                shipping = ship[7];
                break;
            case 9:
                tax = gst[8] + pst[8];
                shipping = ship[8];
                break;
            case 10:
                tax = gst[9] + pst[9];
                shipping = ship[9];
                break;
        }

        
        
        while(!loop) { //Loops back to the options
            
            choice = 0;
            //Get input from the user
            System.out.println("1. Discount option\n2. Checkout");
            choice = Input.intValid("Please enter a number (1 or 2): ");

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
                        newTotal = total + newTax + shipping; //calculates the total with tax
                        
                        invoice.printf("%-15s%16s\n", "\nShipping:", currency.format(shipping)); //print the shipping
                        invoice.printf("%-15s%19s\n", "Subtotal:", currency.format(total)); //prints the subtotal
                        invoice.printf("%-15s%19s\n", "Tax:", currency.format(newTax)); //print out to spread the total from the tax,subtotal,shipping prints the tax
                        invoice.printf("%-15s","\n---------------------------------------")
                        invoice.printf("%-15s%19s\n", "Total:", currency.format(newTotal)); //prints the total
                        invoice.printf("%-15s","\n\n************************************");  //prints the stars to customize                      
                        
                        invoice.flush();
                        invoice.close(); //saves and closes the file

                        try {
                            File r = new File("invoice.txt");
                            Scanner s2 = new Scanner(r);
                            while (s2.hasNextLine()) {
                                //saves the file 
                                receipt = s2.nextLine();
                                System.out.println(receipt);   
                            }
                            s2.close();
                        } catch (FileNotFoundException e) {
                            System.err.println("File not found: " + e.getMessage());
                        }
                        loop = true; // Set the loop to true to end the options

                        System.out.println("Thank you for checking out");
                        break;
                default:
                        System.out.println("Invaild option");
            }
            
        }
       
    } 
}