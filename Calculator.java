import java.util.*;

public class Calculator {

    // Simple stuff to start - created main class to handle inputs and outputs

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the Calculator App.");
        System.out.println("The calculator can add '+', subtract '-', or multiply '*' integers only.");
        System.out.println("e.g. 12435 + 34569 - 12345 * 10 + 50");

        String userInput = "";
        do {
            System.out.print("Please enter your equation here: ");
            userInput = scan.nextLine();
        } while (!checkValidInput(userInput));

        System.out.println("Calculating " + userInput);

        System.out.println("The result is: " + computeEquation(userInput));

        scan.close();

    }

    public static boolean checkValidInput(String userInput) {

        // to check if valid - if the string contains characters other than numbers 0-9
        // or operands +, - and *
        // then it is invalid
        if (userInput.matches("\\s*\\d+(?:\\s*[-+*]\\s*\\d+)*\\s*")) {
            return true;
        } else {
            System.out.println("Please ensure your string does not contain any letters or duplicate operators.");
            return false;
        }
    }

    public static int computeEquation(String userInput) {

        // Assumptions - equation is valid

        String formattedEquation = userInput.replace(" ", "");
        System.out.println(formattedEquation);

        return 0;
    }

}
