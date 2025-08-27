/* Problem Statement
 * ------------------
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
 * ----------------------------------------------------------------------------------------------------
 * 
 * Next is bracket (will use recursion)
 */

// package apps;
// import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    /**
     * Parses the given expression string. We'll also initialise a stack, an operator and an operand variable
     * with default values. For the stack0 management (what and when to push or pop), we'll call pushToStack() 
     * and pass the current operator, operand and the stack (Step 0, 1 and 1c logic here)
     * @param expression The given expression that needs to be evaluated.
     * @return the value after evaluation if the input expression, 0 otherwise.
     */
    private static void evaluate(String expression) {
        // Initialise a stack, an operator and an operand variable with default values.
        Stack<Integer> calculateStack = new Stack();
        int currentOperand = 0, finalResult = 0,  operatorCount = 0, operandCount = 0;
        char currentOperator = '+';

        // trim blanks, saves unnecessary iterations
        expression = expression.trim();

        // Error if expression is empty
        if (expression.isEmpty()) {
            System.out.println("The input expression is empty.");
            return;
        } else { // Parse otherwise
            for(int strIndex = 0; strIndex < expression.length(); strIndex++) {
                // If the expression starts with an operator, throw error
                if(strIndex == 0 && (expression.charAt(strIndex) == '*' || expression.charAt(strIndex) == '/')) {
                    System.out.println("The input expression cannot begin with multiplication or division.");
                    return;
                }

                if(expression.charAt(strIndex) != ' ') {
                    // System.out.println("Current char is: " + expression.charAt(strIndex) + " at Index: " + strIndex + " and it's numeric value: " + Character.getNumericValue(expression.charAt(strIndex)) + "\n");

                    // Check whether it's a number " 2 + 24 / 6 - 9 * "
                    if(Character.isDigit(expression.charAt(strIndex))) {
                        // If the number is more than 1 digit we need to make that number cuase our input is coming as a string
                        currentOperand = (currentOperand * 10) + Character.getNumericValue(expression.charAt(strIndex));

                        // Increase operand count
                        operandCount++;
                    } else if(isOperator(expression.charAt(strIndex))) { // If it's an operator, it's time to push into the stack
                        pushToStack(currentOperator, currentOperand, calculateStack);

                        // Increase operator count
                        operatorCount++;

                        // we're setting the current operator after the push because this operator is for the next operand
                        currentOperator = expression.charAt(strIndex);

                        // restore the currentOperand variable to their default values
                        currentOperand = 0;
                    }

                    // If we're at the end of the string, it's time to push into the stack
                    if(strIndex == expression.length() - 1) {
                        if(isOperator(expression.charAt(strIndex))) {
                            // Increase operator count
                            operatorCount++;

                            break;
                        }

                        pushToStack(currentOperator, currentOperand, calculateStack);
                        
                        // Increase operand count
                        operandCount++;
                    }
                }
            }

            // System.out.println(Arrays.toString(calculateStack.toArray()));
            // System.out.println("Total operators = " + operatorCount + " and Total Operands = " + operandCount);

            // Calculate and return final result
            if (operatorCount == 0) {
                System.out.println("Input expression cannot contain of only operands!");
                return;
            } else if (operandCount == 0) {
                System.out.println("Input expression cannot contain of only operators!");
                return;
            } else if (!calculateStack.empty()) {
                finalResult = sumAllElements(calculateStack);
            }

            System.out.println("The result is (drumroll sound): " + finalResult);
        }
    }
    
    /**
     * Checks if the given character is an operator.
     * @param element The value to check.
     * @return true if the element is an operator [+, -, *, /], false otherwise.
     */
    private static boolean isOperator(char element) {
        if(element != ' ' && (element == '+' || element == '-' || element == '*' || element == '/')) {
            return true;
        }

        return false;
    }

    /**
     * Here we're gonna decide what to insert into the stack (Step 1a - 1b full logic here)
     * @param currentOperator An operator that needs to be calculated.
     * @param currentOperand  An operand that needs to be calculated.
     * @param calculateStack  A stack which stores operators and operands that needs to be summed.
     */
    private static void pushToStack(char currentOperator, int currentOperand, Stack<Integer> calculateStack) {
        // System.out.println("currentOperator: " + currentOperator + " currentOperand: " + currentOperand + "\n");

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

        // System.out.println(Arrays.toString(calculateStack.toArray()));
    }

    /**
     * Calculate sumation of the stack values and return final result (step 1c logic here)
     * @param calculateStack Stack containing all values that needs to be summed up.
     * @return Summation of the values stored in the stack.
     */
    private static int sumAllElements(Stack<Integer> calculateStack) {
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
        String expression = " 2 + 24 / 6 - 9 * 2 ";

        // Call evaluate() method cause main() don't wanna do stuff on it's own in the name of modulation :P
        evaluate(expression);
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