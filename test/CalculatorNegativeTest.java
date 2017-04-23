import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * <h2> Calculator Negative Tests</h2>
 * 
 * A series of tests to ensure negative results are properly handled. 
 * 
 * Tests are organized by arithmetic (Add, Sub, Mult, Div) including
 * a mix of Positive and Negative values. 
 * 
 * Following arithmetic tests are nested tests to ensure nested
 * expressions produce the correct outcome including the 'let' expression
 * when invalid expressions are entered. 
 * 
 * @author Luke
 *
 */

public class CalculatorNegativeTest {
	
	private final String inputError = "Input is not correct.  Please reformat.";
	
	Calculator calc; 

	@Before
	public void setUp() throws Exception {
		calc = new Calculator(); 
	}
	
	// GENERAL
	@Test
	public void testNoInput() {
		String input = " ";
				
		assertTrue("  equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testWrongInput() {
		String input = "Hi, this is a test.";
				
		assertTrue("\"Hi, this is a test.\"  equals [inputError]", calc.runCalculation(input).equals(inputError));
	}

	// ADDITION
	@Test
	public void testAddFirstInvalidParameter() {
		String input = "add(#,3)";
				
		assertTrue("add(#,3) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testAddSecondInvalidParameter() {
		String input = "add(-5,$)";
				
		assertTrue("add(-5,$) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testAddInvalidParameters() {
		String input = "add(@,nothing)";
				
		assertTrue("add(@,nothing) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	
	// SUBTRACTION
	@Test
	public void testSubFirstInvalidParameter() {
		String input = "sub(test,3)";
				
		assertTrue("sub(test,3) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testSubSecondInvalidParameter() {
		String input = "sub(3,+)";
				
		assertTrue("sub(3,+) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testSubInvalidParameters() {
		String input = "sub(sub,add)";
				
		assertTrue("sub(sub,add) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	
	// MULTIPLICATION
	@Test
	public void testMultFirstInvalidParameter() {
		String input = "mult(let,5)";
				
		assertTrue("mult(let,5) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testMultSecondInvalidParameter() {
		String input = "mult(-3,add())";
				
		assertTrue("mult(3,add()) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testMultInvalidParameters() {
		String input = "mult(mult, sub())";
				
		assertTrue("mult(mult, sub()) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	
	// DIVISION
	@Test
	public void testDivDivideByZero() {
		String input = "div(15,0)";
				
		assertTrue("div(15,0) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testDivFirstInvalidParameter() {
		String input = "div(,3)";
				
		assertTrue("div(,3) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testDivSecondInvalidParameter() {
		String input = "div(15,-)";
				
		assertTrue("div(15,-) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testDivInvalidParameters() {
		String input = "div(,)";
				
		assertTrue("div(,) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	
	// NESTED
	@Test
	public void testNestedMissingInput1() {
		String input = "add(, mult(2, 3))";
				
		assertTrue("add(, mult(2, 3)) equals [invalidInput]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testNestedMissingInput2() {
		String input = "mult(add(2, 2), (9, 3))";
				
		assertTrue("mult(add(2, 2), (9, 3)) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testLetNestedInvalidInput() {
		String input = "let(a, 5, add(b, a))";
				
		assertTrue("let(a, 5, add(b, a)) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testLetNestedMissingInput() {
		String input = "let(foo, , let(bar, mult(foo, 10), add(bar, foo)))";
				
		assertTrue("let(foo, , let(bar, mult(foo, 10), add(bar, foo))) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testLetNestedMissingInput1() {
		String input = "let(a, let(b, , let(b, 20, add(a, b)))";
				
		assertTrue("let(a, let(b, , let(b, 20, add(a, b))) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testNested5LetInvalidInput() {
		String input = "sub(let(boo, ~, sub(coo, 10)), add(let(foo, 15, sub(foo, 10)), foo)";
				
		assertTrue("sub(let(boo, ~, sub(coo, 10)), add(let(foo, 15, sub(foo, 10)), foo) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testNested6LetInvalidInput() {
		String input = "sub(let(boo, ~, sub(coo, 10)), add(let(foo, 15, sub(foo, 10)), div(foo,0)))";
				
		assertTrue("sub(let(boo, ~, sub(coo, 10)), add(let(foo, 15, sub(foo, 10)), foo) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}
	@Test
	public void testNested7LetDivideByZero() {
		String input = "sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), div(foo,0)))";
				
		assertTrue("sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), div(foo,0))) equals [inputError]", calc.runCalculation(input).equals(inputError));
	}	
}
