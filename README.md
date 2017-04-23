## CLI Calculator written in Java ##

Calculator will evaluate a User provided expression String and display the result.  Expressions are composed of four possible elements:

* Numbers: (..., -1, 0, 1, ...)
* Variables: String of characters ([a-z], [A-Z])
* Arithmetic Functions:
    * add(*expression*, *expression*)
    * sub(*expression*, *expression*)
    * mult(*expression*, *expression*)
    * div(*expression*, *expression*)
* Let:  Assign a value to a variable
    * let(*variable name*, *value*, *expression where variable is used*)

**Examples**

add(1, 2) -> 3

add(1, mult(2, 3)) -> 7

mult(add(2, 2), div(9, 3)) -> 12

let(a, 5, add(a, a)) -> 10

let(foo, 5, let(bar, mult(foo, 10), add(bar, foo))) -> 55

let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))) -> 40


### About ###
**/src**

**Main**.java

Entry into the program. Instantiates Calculator.

**Calculator**.java

Evaluates User provided expression strings.  Upon User input, will prepare the input String for evaluation.  'performAction' is the core method of Calculator which recursively calls itself and one of 5 children methods ('add', 'sub', 'mult', 'div', 'let').  An origin loop in 'calculate' makes the initial call to 'performAction' and ensures each token in the User provided expression String is evaluated (unless an error flag is raised). 

The default constructor for Calculator allows the program to start without initial input.  'runCalculation' is provided for use in such scenarios when the Calculator is instantiated for input that will be provided later.  A secondary constructor takes a String argument which will immediately evaluate.

Exceptions are suppressed unless 'devEnabled' is set to true.  

To have the program loop on the CLI, prompting for an expression after each evaluation, 'guiEnabled' can be set to true. 

**CalculatorGui**.java

Responsible for outputting responses and also displaying a prompt should 'guiEnabled' be set to true. 

**/test**

A Test Suite is provided.  A total of 53 junit tests exist within CalculatorNegativeTest and CalculatorPositiveTest.  Both can be run by using the suite CalculatorTestSuite

### To Run ###
From the /src folder in a CLI:

1. javac *.java 
2. java Main *expression*

*Should 'guiEnabled' be set to true, can be run without any arguments:  java Main
