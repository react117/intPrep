/* Problem Statement
 * ------------------
 * 
 * Write a small calculator where the simpler equation can be supplied as a string
 * It will support +, -, *, / and understand precedence of each (precedence is a stretch goal)
 * the code should be modular and extendable
 * Expected i/o: "2 + 2 - 1" -> 3 || "2 + 4 / 2" -> 4
 * ----------------------------------------------------------------------------------------------------
 * 
 * Step 0: Intialise a stack to push elements before computatation (as some operators has higher 
 * precedance we don't know which operation has to be perfoirmed first), also intialise two variables
 * to keep track of the current operator and operand, default operator value is + and operand is 0.
 * 
 * Step 1: Parse the input string left to right.
 *      1a: If encountering an operator (+, -, *, /), update operator variable accordingly, go back
 *          to Step 1 and keep parsing.
 *      1b: If encountering an operand, update the operand variable acordingly. 
 *          1b-i: If the current operator value is + or -, multiply the operand variable accordingly by
 *                +1 or -1, push it to the stack and restore the operator and operand variable to their 
 *                default values (+ and 0)
 *          1b-ii: If the current operator value is * or /, we have to give it precedance which means
 *                 we have to perform this operation NOW. So pop 1 elemennt from the stack, do the
 *                 operation with the current operand variable accordingly (* or / based on the current 
 *                 value of operator variable), push the result in to the stack and restore the operator 
 *                 and operand variable to their default values (+ and 0)
 *      1c: If we hit end of string, we perform a summation on all the values stored in the stack, and 
 *          return the result. That's our output.
 */

package apps;
// import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    // First we're gonna parse the input string. We'll also initialise a stack, an operator and an operand variable 
    // with default values. For the stack0 management (what and when to push or pop), we'll call pushToStack() 
    // and pass the current operator, operand and the stack (Step 0, 1 and 1c logic here)
    private static int calculate(String inputString) {
        // Initialise a stack, an operator and an operand variable with default values.
        Stack<Integer> calculateStack = new Stack();
        int currentOperand = 0;
        char currentOperator = '+';

        // We need an index to parse the string, cause it's string and not an array
        int strIndex = 0;

        // Parse inputString
        while(strIndex < inputString.length()) {
            // System.out.println("Current char: " + inputString.charAt(strIndex) + "\n");

            // Check whether it's a number
            if(Character.isDigit(inputString.charAt(strIndex))) {
                // If the number is more than 1 digit we need to make that number cuase our input is coming as a string
                currentOperand = (currentOperand * 10) + (inputString.charAt(strIndex) - '0');
            }

            // Check whether it's an operator or blank, if it's an operator, it's time to push into the stack
            if(!Character.isDigit(inputString.charAt(strIndex)) && inputString.charAt(strIndex) != ' ') {
                pushToStack(currentOperator, currentOperand, calculateStack);

                // we're setting the current operator after the push because this operator is for the next operand
                currentOperator = inputString.charAt(strIndex);

                // restore the currentOperand variable to their default values
                currentOperand = 0;
            }

            // If we're at the end of the string, it's time to push into the stack
            if(strIndex == inputString.length() - 1) {
                pushToStack(currentOperator, currentOperand, calculateStack);
            }

            strIndex++;
        }

        // System.out.println(Arrays.toString(calculateStack.toArray()));

        // Calculate and return final result
        if (!calculateStack.empty()) {
            return finalResult(calculateStack);
        } else {
            return 0;
        }
    }

    // Here we're gonna decide what to insert into the stack (Step 1a - 1b full logic here)
    private static void pushToStack(char currentOperator, int currentOperand, Stack<Integer> calculateStack) {
        // Check for current operator, it it's + or -, we simply multiply currentOperand by +1 or -1 accordingly
        if(currentOperator == '+') {
            currentOperand = currentOperand * 1;
        }

        if(currentOperator == '-') {
            currentOperand = currentOperand * -1;
        }

        // If the current operator is * or /, wee need to pop last element from the stack, perform the * or / operation
        // accordingly with current operand and push the current value into the stack
        if(currentOperator == '*') {
            currentOperand = calculateStack.pop() * currentOperand;
        }

        if(currentOperator == '/') {
            currentOperand = calculateStack.pop() / currentOperand;
        }

        calculateStack.push(currentOperand);

        // System.out.println("currentOperator: " + currentOperator + " currentOperand: " + currentOperand + "\n");
        // System.out.println(Arrays.toString(calculateStack.toArray()));


    }

    // Calculate sumation of the stack values and return final result (step 1c logic here)
    private static int finalResult(Stack<Integer> calculateStack) {
        // System.out.println("Final Stack: " + Arrays.toString(calculateStack.toArray()));
        
        int stackSum = 0;

        // Pop elements and add to stackSum
        while (!calculateStack.isEmpty()) {
            stackSum += calculateStack.pop();
        }

        return stackSum;
    }

    // Here we're gonna take the input string from user and send it to calculate method for the result.
    public static void main(String[] args) {
        // Input string (Static for time being)
        String inputStr = "2 + 24 / 6 - 9 * 2";

        // Call calculate() method cause main() don't wanna do stuff on it's own in the name of modulation :P
        int result = calculate(inputStr);

        System.out.println("The result is (drumroll sound): " + result);
    }
}


/* Note to Self
 * ------------
 * 
 * Things I've learned coding this
 * 
 * 0. Relearned Java Array 
 * 1. Relearned ArrayList
 * 2. Map
 * 3. HashMap
 * 4. TreeMap (Cause HashMap is not ordered and I needer order to calculate the string)
 * 5. Expression Tree (A binary tree where internal nodes are operators and leaves are operands.
 *    however my brain went #rip trying to think of a way to implement this in here and thought 
 *    ET will be too complicated for this problem)
 * 6. Relearned how to code stack and it's basic operations.
 */