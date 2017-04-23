import java.util.HashMap;

/**
 * <h2> Calculator </h2>
 * 
 * A recursive class that parses a String of User input to calculate. 
 * 
 * @author Luke
 *
 */

public class Calculator {
	
	/*
	 * Dev Controls
	 */
	
		// If enabled, program will loop with a menu.
	private boolean guiEnabled = false;
		// If enabled, exceptions are displayed.
	private boolean devEnabled = false; 

	/*
	 * Constants
	 */
	
		// Default value for result
	private final Integer defaultValue = null;
		// Input and Output Messages
	private final String inputError = "Input is not correct.  Please reformat.";
	private final String exitCondition = "q";

	/*
	 * String Patterns
	 */
	
		// Where to break the input String
	private final String Delimiters = "[(), ]+";
		// Pattern for alphabetic characters
	private final String Alpha = "[a-z]+";

	/*
	 * Control Elements
	 */
	
		// Final result
	private Integer result = null;
		// Index to bound recursion
	private int controlIndex = 0;
		// Flag for errors to terminate loop
	private boolean errorFlag = false; 

	/*
	 * Data Structures
	 */
	
		// Storage for 'let' variables
	private HashMap<String, Integer> variables = new HashMap<String, Integer>();
	
	/*
	 * GUI reference
	 */
	private CalculatorGui gui = new CalculatorGui();

	
	public Calculator() {
		if (guiEnabled)
			runCalc(); 
	}
	
	/**
	 * Constructor with input
	 * 
	 * @param input Calculation String of arithmetic expressions. 
	 */
	public Calculator(String input) {		
		String[] inputTokens = prepareInput(input);

		gui.displayResult(calculate(inputTokens));
		
		if (guiEnabled)
			runCalc(); 
	}
	
	/**
	 * Runs a calculation.
	 * Primary use for when Calculator instantiated with default constructor. 
	 * 
	 * @param input String expression to be calculated
	 * @return Returns the outcome of expression 'input' 
	 */
	public String runCalculation(String input) {
		return calculate(prepareInput(input));
	}
	
	/**
	 * Keeps the Calculator running when guiEnabled is true.
	 */
	private void runCalc() {
		String input; 
		
		do {
			resetCalculator(); 
			
			input = gui.displayMenu();
		
			if (input.length() > 2)
				gui.displayResult(calculate(prepareInput(input)));
		} while ((!input.equals(exitCondition)));
		
		gui.exitProgram();
	}
	
	/**
	 * Resets the Calculator.
	 * result:  Represents the final outcome (error message or value).
	 * controlIndex:  Monitors the recursion calls to prevent out of bounds and infinite loop.
	 * errorFlag:  For invalid input.
	 */
	private void resetCalculator() {
		result = null;
		controlIndex = 0;
		errorFlag = false;
	}
	
	/**
	 * Ensures entire expression is evaluated before returning an outcome. 
	 * Calls the recursive method 'performAction' until either:
	 * 		a)  All input tokens have been evaluated.
	 * 		b)  And error is flagged.
	 * 
	 * @param inputTokens Tokenized expression input. 
	 * @return The result of the calculation.
	 */
	private String calculate(String[] inputTokens) {
		
		do {
			performAction(inputTokens, controlIndex, defaultValue);
		} while ((controlIndex < inputTokens.length-1) && !errorFlag);
		
		return (result != null ? result.toString() : inputError);
	}
	
	/**
	 * Prepares and parses the input for calculation.
	 * 
	 * @param input Expression string
	 * @return Lower-cased, tokenized expression string. 
	 */
	private String[] prepareInput(String input) {
		return input.toLowerCase().split(Delimiters);
	}

	/**
	 * Selects the next action to be performed and calls the corresponding method. 
	 * Three base cases are checked to terminate recursion. 
	 * 
	 * Is a recursive parent method to 5 children methods. 
	 * 
	 * @param inputTokens User input
	 * @param index Current index of inputTokens
	 * @param value Current value.  Is null until a child method returns a value. 
	 * @return Returns a resolved value. 
	 */
	private Integer performAction(String[] inputTokens, int index, Integer value) {
		
			// Base Case 1:  A value has resolved
		if (value != defaultValue) 
			return value;
		
			// Base Case 2:  Index out of bounds
		if (index > inputTokens.length-1) 
			return null;

			// Base Case 3:  Incorrect input flagged
		if (errorFlag)
			return null;
		
		switch(inputTokens[index]) {
			case "let":
				if (!let(inputTokens, controlIndexIncrement(1)))
					return null;
				break;
			case "add":
				value = add(inputTokens, controlIndexIncrement(1));
				result = value;
				break;
			case "sub":
				value = sub(inputTokens, controlIndexIncrement(1));
				result = value;
				break;
			case "mult":
				value = mult(inputTokens, controlIndexIncrement(1));
				result = value;
				break;
			case "div":
				value = div(inputTokens, controlIndexIncrement(1));
				result = value;
				break;
			default:
				if (controlIndex == inputTokens.length-1)
					return getValue(inputTokens[index]);
				else {
					controlIndexIncrement(1);
					return null;
				}
		}
		
		return performAction(inputTokens, controlIndex, value);
	}

	/**
	 * Adds two integers.
	 * Attempts to access the first value in tokens[index].
	 * If a valid int, will attempt to access tokens[index+1]
	 * 
	 * Will make a recursive call to parent method performAction to 
	 * resolve any unavailable ints. 
	 * 
	 * @param tokens Expression entered by User
	 * @param index Current position in tokens
	 * @return Sum of two values
	 */
	private Integer add(String[] tokens, int index) {
		Integer a = null, b = null; 
		
		try {		
				// Get the first value
			a = getValue(tokens[index]);
			
				// Get the second value 
			if (index < tokens.length-1)
				b = getValue(tokens[index+1]);
			
				// Return sum
			if (a != null && b != null) {
				controlIndexIncrement(2);
				return a + b;
			}
			
				// Wait for the value to resolve
			else if (a != null)
				return a + performAction(tokens, controlIndexIncrement(1), defaultValue);
			
			return performAction(tokens, controlIndex, defaultValue) + performAction(tokens, controlIndex, defaultValue);

		}
		catch (Exception e) {
			if (devEnabled)	
				System.out.println("add:  " + e);
			errorFlag = true;
		}
		
		return null;
	}

	/**
	 * Subtracts two integers.
	 * Attempts to access the first value in tokens[index].
	 * If a valid int, will attempt to access tokens[index+1]
	 * 
	 * Will make a recursive call to parent method performAction to 
	 * resolve any unavailable ints. 
	 * 
	 * @param tokens Expression entered by User
	 * @param index Current position in tokens
	 * @return Difference of two values
	 */
	private Integer sub(String[] tokens, int index) {
		Integer a = null, b = null; 
		
		try {		
			a = getValue(tokens[index]);

			b = getValue(tokens[index+1]);
			
			if (a != null && b != null) {
				controlIndexIncrement(2);
				return a - b;
			}
			
			else if (a != null)
				return a - performAction(tokens, controlIndexIncrement(1), defaultValue);
			
			return performAction(tokens, controlIndex, defaultValue) - performAction(tokens, controlIndex, defaultValue);
		}
		catch (Exception e) {
			if (devEnabled)	
				System.out.println("sub:  " + e);
			errorFlag = true;
		}
		
		return null;
	}
	
	/**
	 * Multiplies two integers.
	 * Attempts to access the first value in tokens[index].
	 * If a valid int, will attempt to access tokens[index+1]
	 * 
	 * Will make a recursive call to parent method performAction to 
	 * resolve any unavailable ints. 
	 * 
	 * @param tokens Expression entered by User
	 * @param index Current position in tokens
	 * @return Product of two values
	 */
	private Integer mult(String[] tokens, int index) {
		Integer a = null, b = null; 
		
		try {
			a = getValue(tokens[index]);
			
			b = getValue(tokens[index+1]);
			
			if (a != null && b != null) {
				controlIndexIncrement(2);
				return a * b;
			}
			
			else if (a != null)
				return a * performAction(tokens, controlIndexIncrement(1), defaultValue);
			
			return performAction(tokens, controlIndex, defaultValue) * performAction(tokens, controlIndex, defaultValue);
		}
		catch (Exception e) {
			if (devEnabled)	
				System.out.println("mult:  " + e);
			errorFlag = true;
		}
		
		return null;
	}
	
	/**
	 * Divides two integers.
	 * Attempts to access the first value in tokens[index].
	 * If a valid int, will attempt to access tokens[index+1].
	 * 
	 * If the second value, the divisor, is 0 will throw an exception.
	 * 
	 * Will make a recursive call to parent method performAction to 
	 * resolve any unavailable ints. 
	 * 
	 * @param tokens Expression entered by User
	 * @param index Current position in tokens
	 * @return Quotient of two values
	 */
	private Integer div(String[] tokens, int index) {
		Integer a = null, b = null; 
		
		try {
			a = getValue(tokens[index]);
			
			b = getValue(tokens[index+1]);
			
				// Check if dividing by zero
			if (b == 0)
				throw new IllegalArgumentException("Cannot divide by 0!");
			
			if (a != null && b != null) {
				controlIndexIncrement(2);
				return a / b;
			}
			
			else if (a != null)
				return a / performAction(tokens, controlIndexIncrement(1), defaultValue);
			
			return performAction(tokens, controlIndex, defaultValue) / performAction(tokens, controlIndex, defaultValue);
		}
		catch (Exception e) {
			if (devEnabled)	
				System.out.println("div:  " + e);
			errorFlag = true;
		}

		return null;
	}

	/**
	 * Assigns a value to a User provided variable name. 
	 * 
	 * Will check if inputTokens[index+1] contains an int value.
	 * If not, will make a recursive call to parent method for the value to resolve.
	 * 
	 * Should the variable name be invalid, will return false.
	 * 
	 * @param inputTokens User-input values
	 * @param index Current place within inputTokens
	 */
	private boolean let(String[] inputTokens, int index) {
		if (inputTokens[index].matches(Alpha)) {
			if (getValue(inputTokens[index+1]) != null ) {
				variables.put(inputTokens[index], getInt(inputTokens[index+1]));
				controlIndexIncrement(2);
			}
			else 
				variables.put(inputTokens[index], performAction(inputTokens, controlIndexIncrement(1), defaultValue));
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * Updates 'controlIndex'
	 * 
	 * controlIndex allows the origin loop to 'performAction' to maintain control of flow. 
	 * 
	 * Prevents the origin loop from overlapping with the recursive calls and that each
	 * token provided by the User is evaluated.
	 *
	 * @param index Current index of inputTokens String array of User input.
	 * @return Active index being used
	 */
	private int controlIndexIncrement(int increment) {
		controlIndex+=increment;

		return controlIndex;
	}
	
	/**
	 * Wrapper for converting a String to an int (getInt)
	 * and verifying the value is valid (isValidInt).
	 * 
	 * @param num String version of an int
	 * @return Integer version of the String
	 */
	private Integer getValue(String num) {
		if (getInt(num) != null) {
			if (isValidInt(getInt(num)))
				return getInt(num);
		}
		else if (variables.get(num) != null) {
			if (isValidInt(variables.get(num))) 
				return variables.get(num);
		}
		
		return null;
	}
	
	/**
	 * Attempts to convert a String representation of an int to an Integer.
	 * 
	 * @param num String version of an int
	 * @return Integer version of the String
	 */
	private Integer getInt(String num) {
		try {
			return Integer.parseInt(num);
		}
		catch(Exception e) {
			if (devEnabled)	
				System.out.println("getInt:  String is not an int!");
		}

		return null;
	}

	/**
	 * Validates an int is within the supported boundary. 
	 * 
	 * @param num And int
	 * @return If the int is within a support boundary
	 */
	private boolean isValidInt(int num) {
		return (num > Integer.MIN_VALUE && num < Integer.MAX_VALUE);
	}

}
