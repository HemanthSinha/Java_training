package ex1;

import java.util.*;

class Employee {
    static Scanner s = new Scanner(System.in);
    String firstName, lastName, email, dept;
    char[] password = new char[8];
    
    Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void generateEmail() {
        this.email = firstName + lastName + "@" + dept + ".skit.ac.in";
    }

    public void SelectDept() {
        System.out.println("Select the department of employee \n1.Technical\n2.Admin\n3.Human Resource\n4.Legal");
        int ch = s.nextInt();
        switch (ch) {
            case 1 : dept = "Technical"; break;
            case 2 : dept = "Admin"; break;
            case 3 : dept = "Human Resource"; break;
            case 4 : dept = "Legal"; break;
            default : dept = "Unassigned"; break;
        }
    }

    public void ShowCredentials() {
        System.out.println("\nEmployee Credentials:");
        System.out.println("Name:"+firstName + " " + lastName);
        System.out.println("Email address: " + email);
        System.out.println("Department: " + dept);
        System.out.println("Password: " + "[" + new String(password) + "]");
    }

    public void generatePassword() {
    	String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    	String digits = "0123456789";
    	String specialChars = "!@#$%^&*-_+,./\"\\?';:`~";
    	String chars = upperCase + lowerCase + digits + specialChars;
        Random r = new Random();
        while (true) {
            for (int i = 0; i < 8; i++) {
                password[i] = chars.charAt(r.nextInt(chars.length()));
            }
            String randomPassword = new String(password);
            System.out.println("Randomly generated password: " + "["+ randomPassword + "]");
            System.out.println("Do you want to accept this Password(yes/no):");
            String choice = s.next();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Password accepted!");
                break;
            } else if (choice.equalsIgnoreCase("no")) {
            	System.out.println("Enter \"new\" to generate a new password or \"own\" to type in a new password");
            	choice = s.next();
            	if(choice.equalsIgnoreCase("new")) {
            		continue;
            	} else if (choice.equalsIgnoreCase("own")){
            		setOwnPassword();
            		break;
            	}
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void resetPassword() {
        System.out.println("Enter current password:");
        String cp = s.next();
        String currentPassword = new String(this.password);
        if (cp.equals(currentPassword)) {
        	generatePassword();
        } else {
            System.out.println("Incorrect password, Try Again.");
        }
    }
    
    private void setOwnPassword() {
        String ownPassword;
        while (true) {
            System.out.println("Enter password (Must have atleast 1 Uppercase, 1 Lowercase, 1 Number, 1 Special Character, and 8 or more length):");
            ownPassword = s.next();
            if (isPasswordStrong(ownPassword)) {
                this.password = ownPassword.toCharArray();
                System.out.println("Conditions fulfilled. Password set");
                break;
            } else {
                System.out.println("Conditions not fulfilled. Please try again.");
            }
        }
    }

    private boolean isPasswordStrong(String password) {
    	String specialChars = "!@#$%^&*-_+,./\"\\?';:`~";
        String pattern = "^(?=.*[a-z])" +"(?=.*[A-Z])" +"(?=.*\\d)" +"(?=.*[" + specialChars + "])"+".{8,}$";
        return password.matches(pattern);
    }
}
public class LoginCredentials {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter The first name:");
        String firstName = s.next();
        System.out.println("Enter The Last name:");
        String lastName = s.next();
        Employee e1 = new Employee(firstName, lastName);
        e1.SelectDept();
        e1.generateEmail();
        e1.generatePassword();
        e1.ShowCredentials();
        System.out.println("Do you want to reset Password:(Yes/No)");
        String ch = s.next();
        if (ch.equalsIgnoreCase("yes")) {
            e1.resetPassword();
        } else if (ch.equalsIgnoreCase("no")){
        	System.out.println("Password not reset");
        } else {
        	System.out.println("No action performed");
        }
        e1.ShowCredentials();
        s.close();
    }
}
