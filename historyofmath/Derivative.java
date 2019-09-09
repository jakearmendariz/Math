package historyofmath;

import java.util.ArrayList;
/**
 * Derivative.java 
 * @author jakearmendariz
 * Does a lot more than derivatives but I named the file this
 * It splits an expressiojn into muliple terms and then can find it's derivatives, roots and value at any point at any integer
 *
 */
public class Derivative {
	private String expression;
	private ArrayList<ArrayList> functions;
	private ArrayList<String> operators;
	private int highestOrder;
	private ArrayList<Double> roots;
	/**
	 * Derivative
	 * @param s is the expression to be split up into terms
	 */
	public Derivative(String s) {
		ArrayList<String> terms = new ArrayList<String>();
		operators = new ArrayList<String>();
		functions = new ArrayList<ArrayList>();
		roots = new ArrayList<Double>();
		s = s.replaceAll(" ", "");
		s += "+";
		expression = s;
		expression = cleanUp(expression);
		s = expression;
		int index = 0;
		int n = 0;
		int gap = 0;
		//Single term node
		if(s.indexOf("+") == -1 && s.indexOf("-") == -1 && s.indexOf("-") != 0) {
			terms.add(s);
			//System.out.println(terms.get(index));
			return;
		}
		while(n < expression.length()) {
			if(n == expression.length()-1) {
				terms.add(s.substring(0, s.length() -1));
				//System.out.println(terms.get(index));
				break;
				
			}
			else if(expression.charAt(n) == '+' || expression.charAt(n) == '-') {
				if(n > 0 && expression.charAt(n-1) == '^') {}
				else {
					terms.add(s.substring(0, gap));
					s = s.substring(gap+1);
					gap = -1;
					operators.add("" + expression.charAt(n));
					
					//System.out.println(terms.get(index));
				}
			}
			
			n++;
			gap++;
			if(gap == 0) {
				index++;
			}
			
		}
		functions.add(terms);
		this.highestOrder = highestOrderPoly(terms);
		
		
		
	}
	
	
	public String cleanUp(String exp) {
		int i = 0;
		while(i < exp.length()) {
			if(i < exp.length()-1) {
				if(exp.charAt(i) == '+' && exp.charAt(i+1) == '+') {
					exp = exp.substring(0, i) + exp.substring(i+1);
					i--;
				}
				else if(exp.charAt(i) == '+' && exp.charAt(i+1) == '-') {
					exp = exp.substring(0, i) + exp.substring(i+1);
					i--;
				}
				else if(exp.charAt(i) == '-' && exp.charAt(i+1) == '+') {
					exp = exp.substring(0, i+1) + exp.substring(i+2);
					i--;
				}
				else if(exp.charAt(i) == '-' && exp.charAt(i+1) == '-') {
					exp = exp.substring(0, i) + "+" + exp.substring(i+2);
					i--;
				}
				
			}
			i++;
		}
		
		
		
		return exp;
	}
	
	/**
	 * Finds the highest order term in the sequence
	 * @param exp is the exression
	 * @return
	 */
	public int highestOrderPoly(ArrayList<String> exp) {
		
		int max = -106;
		for(int i = 0; i < exp.size(); i++) {
			if(exp.get(i).contains("^")) {
				int next = getExp(exp.get(i));
				if(next>max) {
					max = next;
				}
			}
		}
		if(max == -106) {
			return 1;
		}
		if(max < 0) {
			return 3;
		}
		
		return max;
	}
	
	
	/**
	 * Finds value of the expression at point x
	 * @param x the point to evaluate at'\
	 * @param dev is the derivative to calculate it at
	 * @return the value
	 */
	public int value(int x, int derv) {
		ArrayList<String> terms = functions.get(derv);
		int sum = 0;
		//System.out.println("1 ");
		for(int i = 0; i < terms.size(); i++) {
			String s = terms.get(i);
			int n = s.indexOf("x");
			boolean negative = false;
			if(i > 0) {
			if(operators.get(i-1).equals("-")) {
				negative = true;
			}
			}
			int a = 0;
			for(int j = 0; j < n; j++) {
				if(s.charAt(j) == '-') {
					negative = true;
				}
				a *=10;
				a += (int)s.charAt(j) - 48;
			}
			if(s.length() > n+1 && n != -1) {
				if(a == 0) {
					a =1;
				}
				if(s.charAt(n+1) == '^') {
					n = getExp(s);
					n = (int) Math.pow(x,  n);
				}
			}
			else if(n == -1){//Constant value
				for(int j = 0; j < terms.get(i).length(); j++) {
					a *=10;
					a += (int)s.charAt(j) - 48;
				}
				n =1;
				
			}
			else {
				n = x;
			}
			if(negative) {
				n *=-1;
			}
			if(a == 0) {
				a = 1;
			}
			//System.out.println(terms.get(i) + "\tevaluates to: " + a*n);
			sum += (int)a*n;
		}
		
		
		return sum;
	}
	
	//Finds the value given a double  value
	public double dValue(double x, int derv) {
		ArrayList<String> terms = functions.get(derv);
		double sum = 0;
		for(int i = 0; i < terms.size(); i++) {
			boolean negative = false;
			String s = terms.get(i);
			if(s.charAt(0) == '-') {
				negative = true;
				s = s.substring(1);
			}
			double n = s.indexOf("x");
			if(i > 0) {
			if(operators.get(i-1).equals("-")) {
				negative = !negative;
			}
			}
			double a = 0;
			for(int j = 0; j < n; j++) {
				a *=10;
				a += (double)s.charAt(j) - 48;
			}
			if(s.length() > n+1 && n != -1) {
				if(a == 0) {
					a =1;
				}
				if(s.charAt((int)n+1) == '^') {
					n = getExp(s);
					n = (double) Math.pow(x,  n);
				}
			}
			else if(n == -1){//Constant value
				for(int j = 0; j < terms.get(i).length(); j++) {
					a *=10;
					a += (int)s.charAt(j) - 48;
				}
				n =1;
				
			}
			else {
				n = x;
			}
			if(negative) {
				n *=-1;
			}
			if(a == 0) {
				a = 1;
			}
			//System.out.println(terms.get(i) + "\tevaluates to: " + a*n);
			sum += (int)a*n;
		}
		
		
		return sum;
		
	}
	
	//Finds all derivatives, if negative exponents it will only find up to 3
	public void allDerivatives() {
		//System.out.println(highestOrder + " is highest order");
		for(int i = 0; i < highestOrder+1; i++) {
			functions.add(DerivativeOf(functions.get(i)));
		}
	}
	
	
	/**
	 * How many derivatives to calculate at
	 * @param n the number of derivatives
	 */
	public void calculateDerivative(int n) {
		for(int i = 0; i < n; i++) {
			if(functions.size() == i+1) {
				functions.add(DerivativeOf(functions.get(i)));
			}
		}
	}
	
	//Finds derivative of the next term
	public ArrayList<String> DerivativeOf(ArrayList<String> a){
		ArrayList<String> d = new ArrayList<String>();
		for(int i = 0; i < a.size(); i++) {
			String s = getDer(a.get(i));
			if(s != "") {
				d.add(s);
			}
		}
		return d;
	}
	/**
	 * getExp gets the value of exponent in strings
	 * @param s to check
	 * @return the value of exponent
	 */
	public int getExp(String s) {
		int index = s.indexOf('^');
		int a = 0;
		boolean negative = false;
		if(s.charAt(index+1) == '-') {
			index++;
			negative = true;
		}
		for(int j = index+1; j < s.length(); j++) {
			a *=10;
			a += (int)s.charAt(j) - 48;
		}
		if(negative) {
			a*=-1;
		}
		//System.out.println(s);
		//System.out.println("a " + a);
		return a;
	}
	
	//Gets the derivative using chain rule
	public String getDer(String s) {
		boolean negative = false;
		int n = s.indexOf("x");
		if(n >= s.length()-1) {
			if(n == 0) {
				return 1 + "";
			}
			return s.substring(0,n);
		}
		if(s.charAt(n+1) == '^') {
				int a = 0;
				
				if(s.charAt(0) == '-') {
					negative = true;
					s = s.substring(1);
					n--;
				}
				for(int j = 0; j < n; j++) {
					a *=10;
					a += (int)s.charAt(j) - 48;
				}
				if(a==0) {
					a=1;
				}
				int exp = getExp(s);
				if(negative) {
					a *= -1;
				}
				if(exp == 0) {
					return "";
				}
				else if(exp == 1) {
					return a + "";
				}
				else if(exp < 0) {
					return (a * exp) + "x^" + (exp-1);
				}
				else if(exp == 2) {
					return 2*a + "x";
				}
				else {
					return exp*a + "x^" + (exp-1);
				}
		
		}
		else if(n == -1){//Constant value
			//System.out.println("COnstant\n");
			return "";
		}
		
		
		return "";
	}
	
	/**
	 * printExp()
	 * 
	 * Prints all of the derivatives of the function
	 */
	public void printExp() {
		boolean plus = true;
		for(int i = 0; i < functions.size()-1; i++) {
			if(i != functions.size())
				System.out.print(i+": ");
			for(int j = 0; j < functions.get(i).size(); j++) {
					
				System.out.print(functions.get(i).get(j));
				if(j < functions.get(i).size()-1) {
					System.out.print(" " + operators.get(j) + " ");
				}
					
				
			}
			System.out.println("");
			
		}
		
		
	}
	
	//Prints the roots
	public void printRoots() {
		System.out.print("Roots: ");
		for(int i = 0; i < roots.size(); i++) {
			System.out.print(roots.get(i));
			if(i < roots.size()-1) {
				System.out.print(", ");
			}
		}
	}
	
	/**
	 * nextOperator finds the soonest operator
	 * @param s is the string to search
	 * @return the index in the string
	 */
	public int nextOperator(String s) {
		for(int i = 0; i< s.length(); i++) {
			char c = s.charAt(i);
			if(c == '+' || c == '-' || c == '/') {
				return i;
			}
		}
		return -1;
	}
	
	
	public static void main(String[] args) {
		Derivative a = new Derivative("x^1");
		a.allDerivatives();
		a.printExp();
		System.out.println(a.dValue(2,  0));
		System.out.println(a.dValue(2,  1));
		//a.findRoots();
		//a.printRoots();
		
	}
	
	
	/**
	 * oppisite signs
	 * Defines the difference between the two values for what to do with linear apporximation
	 * @param a
	 * @param b
	 * @return
	 */
	public int oppisiteSigns(double a, double b) {
		if(a == 0) {
			return 10;
		}
		if(b == 0) {
			return -10;
		}
		if(a > 0 && b > 0) {
			return -1;
		}
		else if(a < 0 && b < 0) {
			return -1;
		}
		else if(a > 0 && b < 0) {
			return 1;
		}
		else if(a < 0 && b > 0) {
			return 1;
		}
		else if(a == b) {
			return 0;
		}
		return 100;
	}
	
	
	/**
	 * findRoots
	 * 
	 * @return all of the roots
	 */
	public ArrayList<Double> findRoots(){
		for(int i = -100; i <= 100; i++) {
			int n = oppisiteSigns(value(i, 0), value(i+1, 0));
			if(n == 1) {
				//System.out.println(value(i, 0) + " " + value(i+1, 0));
				double x;// = dValue(i, 0) +  dValue(i+1, 0);
				x = i+.5;
				double root = newtonsMethod(x);
				if(root != -123){
					if(roots.size() >= 1 && roots.get(roots.size()-1) != root) {
						roots.add(root);
					}
					if(roots.size() == 0) {
						roots.add(root);
					}
				}
			}
			else if(n == 10) {
				roots.add((double) i);
			}
			
		}
		
		
		return roots;
	}
	
	
	/**
	 * Esitmates the roots
	 * @param x
	 * @return
	 */
	double newtonsMethod(double x) {
		//System.out.println("x: " + x);
		for(int i = 0; i < 100; i++) {
			
			//System.out.println("value \t:" + dValue(x, 0));
			//System.out.println("value'\t:" + dValue(x, 1));
			if(dValue(x, 1) == 0) {
				if(dValue(x, 0) == 0) {
					return i;
				}
				else {
					return -123;
				}
			}
			x = x - (dValue(x, 0))/(dValue(x, 1));
			//x = ((double)(Math.round(x*10000)))/10000;
			
		}
		return x;
	}
	
}
