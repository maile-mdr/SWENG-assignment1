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
        String lastOp = "";

        // Step 1. Deal with the multiplication first
        for (int i = 1; i < operators.length; i++) {
            operandStack.push(Integer.parseInt(operands[operandIndex]));
            System.out.println("Pushed " + operands[operandIndex]);

            String currOperator = operators[i];
            System.out.println("Dealing with " + currOperator);

            if (currOperator.equals("+") || currOperator.equals("-")) {
                operatorStack.push(currOperator);
                System.out.println("Pushed " + currOperator);
                operandIndex++;
                System.out.println("OperandIndex: " + operandIndex);
            } else if (currOperator.equals("*")) {
                int operand1 = operandStack.pop();
                System.out.println("Popped " + operand1);
                int operand2 = Integer.parseInt(operands[++operandIndex]);
                System.out.println("Processed " + operand2);
                int result = operand1 * operand2;
                operandStack.push(result);
                System.out.println("Pushed " + result);
            }

            lastOp = currOperator;
        }

        if (operandIndex == operands.length - 1) {
            if (!lastOp.equals("*")) {
                operandStack.push(Integer.parseInt(operands[operandIndex]));
                System.out.println("Pushed " + operands[operandIndex]);
            }
        }

        // Step 2. Computer the rest of the equations until no more operators left
        while (!operatorStack.isEmpty()) {
            int operand2 = operandStack.pop();
            int operand1 = operandStack.pop();
            System.out.println("Operands are: " + operand1 + " and " + operand2);

            String currOperator = operatorStack.pop();
            System.out.println("Dealing with " + currOperator);

            int result = 0;

            if (currOperator.equals("+")) {
                result = operand1 + operand2;
            } else if (currOperator.equals("-")) {
                result = operand1 - operand2;
            }

            operandStack.push(result);
        }

        return operandStack.pop();

    }

}
