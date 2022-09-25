package emailapp;
import java.util.Scanner;
import java.util.Random;
import java.io.*;
public class Email {

    public Scanner s = new Scanner(System.in); // Scanner class in java.util package

    // Setting up the variables
    // Defined as 'private' so that these cannot be accessed directly
    private String fname;
    private String lname;
    private String dept;
    private String email;
    private String password;
    private int mailCapacity = 500;
    private String alter_email;

    // Constructor to receive the first name and the last name
    public Email(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        System.out.println("NEW EMPLOYEE: " + this.fname + " " + this.lname);

        // Call a method asking for the department - return the department
        this.dept = this.setDept();

        // Call a method that returns a random password
        this.password = this.generate_password(8);

        // Combine elements to generate an email
        this.email = this.generate_email();
    }

    // Generating the email according to the given syntax=>(fname.lname@department.company.com)
    private String generate_email() {
        return this.fname.toLowerCase() + "." + this.lname.toLowerCase() + "@" + this.dept.toLowerCase()
                + ".company.com";
    }

    // Ask for the department, returns department info.
    private String setDept() {
        System.out.println(
                "DEPARTMENT CODES\n1 for Sales\n2 for Development\n3 for Accounting\n0 for None");
        boolean flag = false;
        do {
            System.out.print("Enter Department Code: ");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    return "Sales"; // return breaks the loop and returns value for method call
                case 2:
                    return "Development";
                case 3:
                    return "Accounting";
                case 0:
                    return "None";
                default:
                    System.out.println("**INVALID CHOICE**");
            }
        } while (!flag); // keeps running until the valid input is given and return gets executed
        return null; // nothing to return as the required return value is already returned from do-while block;
    }

    // Generating a random password
    private String generate_password(int length) {
        Random r = new Random(); // Random class in java.util package

        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%&?";
        String values = Capital_chars + Small_chars + numbers + symbols;

        String password = "";
        for (int i = 0; i < length; i++) {
            password = password + values.charAt(r.nextInt(values.length()));
            // values.length()=>size of values string; r.nextInt(size) gives random integer in [0,size);
            // values.charAt(index) => char at that index in string values.
        }
        return password;
    }

    // Change the password
    public void set_password() {
        boolean flag = false;
        do {
            System.out.print("ARE YOU SURE YOU WANT TO CHANGE YOUR PASSWORD? (Y/N) : ");
            char choice = s.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                System.out.print("Enter current password: ");
                String temp = s.next();
                if (temp.equals(this.password)) {
                    flag = true;
                    this.password = this.generate_password(8);
                    System.out.println("Your new password: "+ password);
                   // this.password = s.next();  // for taking input from user
                    System.out.println("PASSWORD CHANGED SUCCESSFULLY!");
                } else {
                    System.out.println("Incorrect Password!");
                }
            } else if (choice == 'N' || choice == 'n') {
                flag = true;
                System.out.println("PASSWORD CHANGE CANCELED!");
            } else {
                System.out.println("**ENTER A VALID CHOICE**");
            }
        } while (!flag);
    }

    // Set the mailbox capacity
    public void set_mailCap() {
        System.out.println("Current capacity = " + this.mailCapacity + "mb");
        System.out.print("Enter new capacity: ");
        this.mailCapacity = s.nextInt();
        System.out.println("MAILBOX CAPACITY CHANGED SUCCESSFULLY!");

    }

    // Set the alternate email
    public void alternate_email() {
        System.out.print("Enter new alternate email: ");
        // Add checks so that this email must confide with email syntax.
        this.alter_email = s.next();
        System.out.println("ALTERNATE EMAIL SET SUCCESSFULLY!");
    }

    // Displaying the employee's information
    public void getInfo() {
        System.out.println("NAME: " + this.fname + " " + this.lname);
        System.out.println("DEPARTMENT: " + this.dept);
        System.out.println("EMAIL: " + this.email);
        System.out.println("PASSWORD: " + this.password);
        System.out.println("MAILBOX CAPACITY: " + this.mailCapacity + "mb");
        System.out.println("ALTER EMAIL: " + this.alter_email); // returns null if not set
    }

    public void storefile() {
        try {
            FileWriter in = new FileWriter("C:\\Users\\Sulav PC\\Desktop\\Info.txt");
            in.write("First Name: "+this.fname);
            in.append("Last Name: "+this.lname);
            in.append("Email: "+this.email);
            in.append("Password: "+this.password);
            in.append("Capacity: "+this.mailCapacity);
            in.append("Alternate mail: "+this.alter_email);
            in.close();
            System.out.println("Stored..");
        }catch (Exception e){System.out.println(e);}
    }

    public void read_file() {
        try {
            FileReader f1 = new
                    FileReader("C:\\Users\\Sulav PC\\Desktop\\Info.txt");
            int i;
            while ((i = f1.read()) != -1) {
                System.out.print((char) i);
            }
            f1.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println();

    }
}