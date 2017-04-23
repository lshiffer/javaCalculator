import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * <h2> Calculator Positive Tests</h2>
 * 
 * A series of tests to ensure positive results on user provided expressions.  
 * 
 * Tests are organized by arithmetic (Add, Sub, Mult, Div) including
 * a mix of Positive and Negative values. 
 * 
 * Following arithmetic tests are nested tests to ensure nested
 * expressions produce the correct outcome including the 'let' expression.
 * 
 * @author Luke
 *
 */

public class CalculatorPositiveTest {
	
	Calculator calc; 

	@Before
	public void setUp() throws Exception {
		calc = new Calculator(); 
	}

	// ADDITION
	@Test
	public void testAdd() {
		String input = "add(5,3)";
				
		assertTrue("add(5,3) equals 8", calc.runCalculation(input).equals("8"));
	}
	@Test
	public void testAddFirstNeg() {
		String input = "add(-5,3)";
				
		assertTrue("add(-5,3) equals -2", calc.runCalculation(input).equals("-2"));
	}
	@Test
	public void testAddSecondNeg() {
		String input = "add(5,-3)";
				
		assertTrue("add(5,-3) equals 2", calc.runCalculation(input).equals("2"));
	}
	@Test
	public void testAddDoubleNeg() {
		String input = "add(-5,-3)";
				
		assertTrue("add(-5,-3) equals -8", calc.runCalculation(input).equals("-8"));
	}
	
	// SUBTRACTION
	@Test
	public void testSub() {
		String input = "sub(5,3)";
				
		assertTrue("sub(5,3) equals 2", calc.runCalculation(input).equals("2"));
	}
	@Test
	public void testSubNegativeResult() {
		String input = "sub(3,5)";
				
		assertTrue("sub(3,5) equals -2", calc.runCalculation(input).equals("-2"));
	}
	@Test
	public void testSubSecondNegative() {
		String input = "sub(3,-5)";
				
		assertTrue("sub(3,-5) equals 8", calc.runCalculation(input).equals("8"));
	}
	@Test
	public void testSubFirstNegative() {
		String input = "sub(-3,5)";
				
		assertTrue("sub(-3,5) equals -8", calc.runCalculation(input).equals("-8"));
	}
	@Test
	public void testSubDoubleNegative() {
		String input = "sub(-3,-5)";
				
		assertTrue("sub(-3,-5) equals 2", calc.runCalculation(input).equals("2"));
	}
	
	// MULTIPLICATION
	@Test
	public void testMult() {
		String input = "mult(3,5)";
				
		assertTrue("mult(3,5) equals 15", calc.runCalculation(input).equals("15"));
	}
	@Test
	public void testMultFirstOneNeg() {
		String input = "mult(-3,5)";
				
		assertTrue("mult(3,5) equals -15", calc.runCalculation(input).equals("-15"));
	}
	@Test
	public void testMultSecondOneNeg() {
		String input = "mult(3,-5)";
				
		assertTrue("mult(3,-5) equals -15", calc.runCalculation(input).equals("-15"));
	}
	@Test
	public void testMultBothNeg() {
		String input = "mult(-3,-5)";
				
		assertTrue("mult(-3,-5) equals 15", calc.runCalculation(input).equals("15"));
	}
	
	// DIVISION
	@Test
	public void testDiv() {
		String input = "div(15,3)";
				
		assertTrue("div(15,3) equals 5", calc.runCalculation(input).equals("5"));
	}
	@Test
	public void testDivFirstOneNeg() {
		String input = "div(-15,3)";
				
		assertTrue("div(-15,3) equals -5", calc.runCalculation(input).equals("-5"));
	}
	@Test
	public void testDivSecondOneNeg() {
		String input = "div(15,-3)";
				
		assertTrue("div(15,-3) equals -5", calc.runCalculation(input).equals("-5"));
	}
	@Test
	public void testDivBothNeg() {
		String input = "div(-15,-3)";
				
		assertTrue("div(-15,-3) equals 5", calc.runCalculation(input).equals("5"));
	}
	
	// NESTED
	@Test
	public void testNested1() {
		String input = "add(1, mult(2, 3))";
				
		assertTrue("add(1, mult(2, 3)) equals 7", calc.runCalculation(input).equals("7"));
	}
	@Test
	public void testNested2() {
		String input = "mult(add(2, 2), div(9, 3))";
				
		assertTrue("mult(add(2, 2), div(9, 3)) equals 12", calc.runCalculation(input).equals("12"));
	}
	@Test
	public void testLetNested1() {
		String input = "let(a, 5, add(a, a))";
				
		assertTrue("let(a, 5, add(a, a)) equals 10", calc.runCalculation(input).equals("10"));
	}
	@Test
	public void testLetNested3() {
		String input = "let(foo, 5, let(bar, mult(foo, 10), add(bar, foo)))";
				
		assertTrue("let(foo, 5, let(bar, mult(foo, 10), add(bar, foo))) equals 55", calc.runCalculation(input).equals("55"));
	}
	@Test
	public void testLetNested4() {
		String input = "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))";
				
		assertTrue("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))) equals 40", calc.runCalculation(input).equals("40"));
	}
	@Test
	public void testNested5Let() {
		String input = "sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), foo)";
				
		assertTrue("sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), foo) equals -5", calc.runCalculation(input).equals("-5"));
	}
	@Test
	public void testNested6Let() {
		String input = "sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), div(foo,5)))";
				
		assertTrue("sub(let(boo, 25, sub(boo, 10)), add(let(foo, 15, sub(foo, 10)), div(foo,5))) equals 7", calc.runCalculation(input).equals("7"));
	}
	
}
