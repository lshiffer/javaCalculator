
public class Main {
	
	private static Calculator calc;

	public static void main(String[] args) {
		if (args.length < 1)
			calc = new Calculator();
		else
			calc = new Calculator(args[0]); 
	}
}
