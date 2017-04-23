import java.util.Scanner;

public class CalculatorGui {

	private final String header = "\n[ Calculator ]";

	private final String directions = " Enter an Expression or 'q' to quit:";
	
	private final String response = "\nAnswer:  ";

	private final String goodbye = "\n Thank you for using Calculator!";
	
	Scanner consoleScanner;
	
	public CalculatorGui() {
		consoleScanner = new Scanner(System.in);
	}
	
	public String displayMenu() {
		String userInput = null;
		System.out.println(header);
		System.out.println(directions);
		userInput = consoleScanner.nextLine();
		
		return userInput;
	}
	
	public void displayResult(String result) {
		System.out.println(response + result);
	}
	
	public void exitProgram() {
		System.out.println(goodbye);
	}
}
