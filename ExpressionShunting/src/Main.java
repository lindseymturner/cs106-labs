import java.util.ArrayList;
/*
Name: Lindsey Turner
Date: 4/20/24
Program Description: This program reads an infix mathematical expression from the command line and converts it into postfix notation in order to calculate the result following all PEMDAS rules.
 */

public class Main {
    public static void main(String[] args) {
        // Your main code goes here.
        ArrayList<String> commandArgs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            commandArgs.add(args[i]);
        }

        System.out.println("First, some stack tests:");
        MainTestsOFInterfaces.main(args);
        System.out.println("");

        System.out.println("Now, Welcome to the Shunting Yard");
        // @ToDo: put your test calls to your shunting yard algorithm here

        String[] stringArray = new String[]{"1", "+", "2"};
        System.out.println(shuntingYard(stringArray));

        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("+");
        System.out.println(postFix(queue));

        LinkedQueue<String> result = shuntingYard(args);
        System.out.println(result);
        System.out.println(postFix(result));
    }

    /**
     * takes an infix expression and converts it to postfix
     * @param stringArray each element of the array is a string consisting of a number, operator, or parenthesis.
     * @return a linked queue in postfix order
     * @throws IllegalArgumentException
     */
    public static LinkedQueue<String> shuntingYard(String[] stringArray) throws IllegalArgumentException {
        LinkedStack<String> operatorStack = new LinkedStack<>();
        LinkedQueue<String> outBuffer = new LinkedQueue<>();

        for (String element : stringArray) {

            if (isOperator(element)) {
                // if there is nothing in the operator stack, or there is an open parenthesis at the top of the operator stack, or the current element's precedence is greater than or equal to that of the
                // operator at the top of the stack, then simply add the element to the top of the stack
                if (operatorStack.isEmpty() || operatorStack.peek().equals("(") || precedence(element) >= precedence(operatorStack.peek())) {
                    operatorStack.push(element);
                }
                // if the current element's precedence is less than that of the operator at the top of the stack, remove the top element from the top of the stack and add it to the outBuffer queue. Then add
                // the current element to the top of the operator stack.
                else {
                    outBuffer.enqueue(operatorStack.pop());
                    operatorStack.push(element);
                }


            }
            else if (isParenthesis(element)) {
                // Else if element is left parenthesis, then push it on the stack
                if (element.equals("(")) {
                    operatorStack.push(element);
                }
                // Else if element is a right parenthesis, until the top element (from the stack) is left parenthesis, pop from the stack to the output buffer
                else if (element.equals(")")) {
                    while (!operatorStack.peek().equals("(")) {
                        outBuffer.enqueue(operatorStack.pop());
                    }
                    // Also pop the left parenthesis but don’t include it in the output buffer
                    operatorStack.pop();
                }
            }

            // If element is a number, add it to the output buffer
            else if (isNumeric(element)){
                outBuffer.enqueue(element);
            }
            else {
                throw new IllegalArgumentException("Not a valid input");
            }
        }

        // Pop any remaining operators from the stack to the output
        while (!operatorStack.isEmpty()) {
            outBuffer.enqueue(operatorStack.pop());
        }

        return outBuffer;
    }

    /**
     * Takes a postfix expression, applies the operators, and outputs the result.
     * @param stringQueue A LinkedQueue with each element being a number or operator in postfix order
     * @return Returns the resulting value
     */
    public static Double postFix(LinkedQueue<String> stringQueue) {
        LinkedStack<String> myStack = new LinkedStack<>();
        while (!stringQueue.isEmpty()) {
            // If the token is an operand, push it onto the stack
            if (isNumeric(stringQueue.first())) {
                myStack.push(stringQueue.first());
            }
            // If the token is a binary operator: Pop the two topmost operands from the stack. Apply the binary operator to the two operands. Push the result back onto the stack
            else if (isOperator(stringQueue.first())) {
                double operand1 = Double.parseDouble(myStack.pop());
                double operand2 = Double.parseDouble(myStack.pop());
                double result = 0;
                if (stringQueue.first().equals("*")) {
                    result = operand1 * operand2;
                }
                else if (stringQueue.first().equals("/")) {
                    result = operand2 / operand1;
                }
                else if (stringQueue.first().equals("^")) {
                    result = Math.pow(operand2, operand1);
                }
                else if (stringQueue.first().equals("+")) {
                    result = operand1 + operand2;
                }
                else {
                    result = operand2 - operand1;
                }
                myStack.push(String.valueOf(result));
            }
            stringQueue.dequeue();
        }
        // Finally, the value of the whole postfix expression remains in the stack. It is the only element in the stack.
        return Double.parseDouble(myStack.peek());
    }

    /**
     * Determines if a string contains a number
     * @param strNum a string
     * @return returns true if it is a number
     * @throws NumberFormatException
     */
    public static Boolean isNumeric(String strNum) throws NumberFormatException {
        try
        {
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }

    }

    /**
     * Determines if a string contains an operator
     * @param strOp a string
     * @return returns true if it is an operator
     */
    public static Boolean isOperator(String strOp) {
        return strOp.equals("^") || strOp.equals("/") || strOp.equals("*") || strOp.equals("+") || strOp.equals("-");
    }

    /**
     * Determines if a string contains a parenthesis
     * @param str a string
     * @return Returns true if it is a parenthesis
     */
    public static Boolean isParenthesis(String str) {
        return str.equals("(") || str.equals(")");
    }

    /**
     * Determines the precedence value of an operator within a string
     * @param operator a string
     * @return returns the integer value corresponding to the operator's precedence
     */
    public static int precedence(String operator) {
        if (operator.equals("^")) {
            return 10;
        }
        else if (operator.equals("/") || operator.equals("*")) {
            return 5;
        }
        else {
            return 0;
        }
    }
}