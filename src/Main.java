
public class Main {
	
	public static void main(String[] args) {
		if (args.length < 1)
			new Calculator();
		else
			new Calculator(args[0]); 
	}
}
