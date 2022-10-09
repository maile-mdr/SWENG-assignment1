import java.util.*;

public class Calculator {

    // Changes made over time
    // Tues: set up the user interface
    // Thursday-Friday: worked on regex / input handling
    // Sunday: finalised stack calculator

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

        // Removing spaces for simplicity / saving space
        String formattedEquation = userInput.replace(" ", "");

        System.out.println("The result is: " + computeEquation(formattedEquation));

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

        // Assumptions - equation is valid and string is not empty

        // start placing operators and operands into their respective stacks
        // if you encounter a '*', compute the equation and push the result into the
        // operand stack

        Stack<String> operatorStack = new Stack<>();
        Stack<Integer> operandStack = new Stack<>();

        String equation = userInput;

        // Separating operators and operands
        String operators[] = equation.split("[0-9]+");
        String operands[] = equation.split("[+*\\-]");

        int operandIndex = 0;
        int operatorIndex = 1; // Starts at 1 because first element is an empty string "" for some strange
                               // reason
        String lastOp = ""; // helps us switch between stacking= +/- operands and * operands

        // Step 1. Deal with the multiplication first
        while (operatorIndex < operators.length) {
            String currOperator = operators[operatorIndex];

            if (currOperator.equals("+") || currOperator.equals("-")) {
                if (lastOp.equals("*")) {
                    operandIndex++; // if we last multiplied a digit then we have to deal with the next set of
                                    // characters
                }
                // Push operand and following operator onto stack
                operandStack.push(Integer.parseInt(operands[operandIndex]));
                operatorStack.push(currOperator);
                operandIndex++;
                operatorIndex++;
            } else if (currOperator.equals("*")) {
                // When we multiply, we figure out which correct operand1 to take (may or may
                // not be in stack)
                // We then get the next operand2 (not in stack yet)
                int operand1;
                if (lastOp.equals("*")) {
                    operand1 = operandStack.pop();
                } else {
                    operand1 = Integer.parseInt(operands[operandIndex]);
                }
                int operand2 = Integer.parseInt(operands[++operandIndex]);

                // Computing result
                int result = operand1 * operand2;

                // Push to stack
                operandStack.push(result);

                operatorIndex++;
            }

            lastOp = currOperator;
        }

        // Checking if there are any operands that haven't been visited yet (usually the
        // case when dealing with + or -)
        if (operandIndex == operands.length - 1) {
            if (!lastOp.equals("*")) {
                operandStack.push(Integer.parseInt(operands[operandIndex]));
            }
        }

        // Step 2. Computer the rest of the equations until no more operators left
        int result = 0;

        // We need to do the + and - operations in order, so we turn our stack into an
        // array
        Object[] operandArr = operandStack.toArray();
        Object[] operatorArr = operatorStack.toArray();

        result += Integer.parseInt(String.valueOf(operandArr[0]));
        System.out.println(result);

        for (int i = 0; i < operatorArr.length; i++) {
            String currOperator = String.valueOf(operatorArr[i]);
            if (currOperator.equals("+")) {
                result += Integer.parseInt(String.valueOf(operandArr[i + 1]));

            } else if (currOperator.equals("-")) {
                result -= Integer.parseInt(String.valueOf(operandArr[i + 1]));
            }
        }

        return result;

    }

}
