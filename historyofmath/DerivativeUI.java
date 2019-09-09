package historyofmath;
import java.util.*;
/**
 * DerivativeUI.java
 * @author jakearmendariz
 * A basic UI to use the derivative class. It's farily intuitive but a lot of incorrect inputs will cause an error
 * Far from perfect but works on normal polynomail expressions with integer constants and exponents
 * Handles negative exponents
 */
public class DerivativeUI {
	
	public static void printHelp() {
		System.out.println("Commands:\nall\t:All derivatives printed\nvalue\t:Value of function or one of its derivatives\n"
				+ "roots\tprints all roots of program\nquit\t:terminates program");
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Please enter a polynomial expression of any degree\tExample input: x^2 + 4x + 4\ny = ");
		String exp = scan.nextLine();
		exp = exp.toLowerCase();
		
		while(exp.contains("(")) {
			System.out.println("No parenthases in expression. Example input: x^2 + 4x + 4");
			exp = scan.nextLine();
		}
		while(exp.contains("y") || exp.contains("=")) {
			System.out.println("Need an expression not equation (in terms of x). Example input: x^2 + 4x + 4");
			exp = scan.nextLine();
		}
		Derivative a = new Derivative(exp);
		a.allDerivatives();
		a.findRoots();
		String input = "";
		printHelp();
		while(!input.equals("quit")) {
			input = scan.next();
			if(input.equals("all")) {
				a.printExp();
			}
			else if(input.equals("value")) {
				System.out.println("Which derivative of the function would you like (Enter 0 for real function)");
				int n = scan.nextInt();
				System.out.println("What value would like to plug in?");
				int x = scan.nextInt();
				System.out.println("Value is " + a.dValue(x,  n));
			}
			else if(input.equals("roots")) {
				a.printRoots();
			}
			else if(!input.equals("quit")){
				printHelp();
			}
			
			
		}
		
	}
}
