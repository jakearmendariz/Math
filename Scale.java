import java.util.ArrayList;
import java.util.Random;

public class Scale {
	private ArrayList<Ball> set;
	private int num;
	private int result;
	int steps;
	
	/**
	 * Creates 9 balls in the set, with a 
	 */
	public Scale(int n) {
		this.num = n;
		set = new ArrayList<Ball>();
		for(int i = 0; i < this.num; i++) {
			set.add(new Ball());
		}
		Random r = new Random();
		//set.get(r.nextInt(this.num)).specialBall();
		steps = 0;
		//set.get(5]).weight += .2;
	}
	
	/**
	 * Prints the original set to see where the special position is
	 */
	public void printSet() {
		int n = 0;
		for(int i = 0; i < this.num; i++) {
			System.out.print(set.get(i).getWeight() + ", ");
			if(set.get(i).getWeight() == 1.2) {
				n = i;
			}
		}
		n++;
		//System.out.println("\nHeaviest ball at pos: " + n + "\n");
	}
	
	
	//Returns 1 if a > b
	//Returns -1 if b > a
	//Returns 0 if equal
	public int weigh(double a, double b) {
		this.steps++;
		if(a > b) {
			return 1;
		}
		else if(b> a) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * Adds up the elements in array from a to b
	 * @param a included
	 * @param b excluded
	 * @return the total sum
	 */
	public double sumPos(ArrayList<Ball> s, int a, int b) {
		double sum = 0;
		for(int i = a; i < b; i++) {
			sum += s.get(i).weight;
		}
		return sum;
	}
	
	/**
	 * Creates new arraylist with only the possibile elemtns
	 * @param a included
	 * @param b excluded
	 * @return the total sum
	 */
	public ArrayList<Ball> newList(int a, int b){
		ArrayList<Ball> relavant = new ArrayList<Ball>();
		for(int i = a; i < b; i++) {
			relavant.add(this.set.get(i));
		}
		return relavant;
	}
	
	
	
	/**
	 * The recurssive method to find heaviest ball
	 * Uses the half the set as measurment
	 */
	public  void measureHalf(ArrayList<Ball> s) {
		int a = s.size();
		int b = (int)a/2;
		
		int side = weigh(sumPos(s, 0, b), sumPos(s, b, b*2));
		if(a > 3) {
			if(side == 0) {
				this.result += b*2+1;
				System.out.println("The heavier ball is #" + result);
			}else if(side == 1) {
				s = newList(this.result, this.result+b);
				measureHalf(s);
			}else {
				this.result += b;
				s = newList(this.result, this.result + b);
				measureHalf(s);
			}
		}else {
			if(side == 0) {
				this.result += 3;
				System.out.println("The heavier ball is #" + result);
			}else if(side == 1) {
				this.result += 1;
				System.out.println("The heavier ball is #" + result);
			}else {
				this.result += 2;
				System.out.println("The heavier ball is #" + result);
			}
		}
		
	}
	
	/*
	 * B is the variant of measurement
	 * We want to slowly change B and see how it affects the rate of change for the graph
	 */
	public  int measure(ArrayList<Ball> s, int b, int steps) {
		int a = s.size();
		int side = weigh(sumPos(s, 0, b), sumPos(s, b, b*2));
		if(a > 3) {
			if(side == 0) {
				this.result += b*2+1;
				s = newList(this.result, a);
				measure(s, b, steps++);
			}else if(side == 1) {
				s = newList(this.result, this.result+b);
				measure(s, b, steps++);
			}else {
				this.result += b;
				s = newList(this.result, this.result + b);
				measure(s, b, steps++);
			}
			
		}else {
			printSet();
			if(side == 0) {
				this.result += 3;
				System.out.println("The heavier ball is #" + result);
			}else if(side == 1) {
				this.result += 1;
				System.out.println("The heavier ball is #" + result);
			}else {
				this.result += 2;
				System.out.println("The heavier ball is #" + result);
			}
		}
		return steps;
		
	}
	
	
	
	
	
	/*
	 * n is the heaviest ball();
	 * initiates the array
	 */
	public void init(int n) {
		for(int i = 0; i < this.set.size(); i++) {
			this.set.get(i).weight = 1;
		}
		this.set.get(n).weight = 1.2;
	}
	
	public double averageSteps(int n) {
		double sum = 0;
		for(int i = 0; i < this.num; i++) {
			init(i);
			findHeaviest(n);
			this.result = 0;
			sum += this.steps;
			System.out.println("The # steps is " + steps);
		}
		sum /= this.num;
		System.out.println("The average steps is " + sum);
		return sum;
	}
	
	/**
	 * Returns the number of steps in the worse case
	 * @param n
	 * @return
	 */
	public double worstCase(int n) {
		double stps = 0;
		for(int i = 0; i < this.num; i++) {
			init(i);
			findHeaviest(n);
			this.result = 0;
			if( stps < this.steps) {
				stps = this.steps;
			}
		}
		
		System.out.println("The average steps is " + stps);
		return stps;
	}
	
	
	/**
	 * Calls recursive method
	 */
	public void findHeaviest(int n) {
		this.steps = 0;
		measure(this.set, n, 0);
	}
	
	public void bestRoute() {
		double leastSteps = 10000;
		int j = -1;
		for(int i = 1; i < this.num/2; i++) {
			if(worstCase(i) < leastSteps) {
				leastSteps = worstCase(i);
				j = i;
			} 
		}
		System.out.println("The best route for "+  this.num + " balls is " + j +  " size comparisons. Steps: " + leastSteps);
		
	}
	
	public static void main(String[] args) {
		Scale a = new Scale(9);
		a.init(2);
		a.printSet();
		a.findHeaviest(3);
		//System.out.println("It took " + a.steps + " steps.");
		//a.bestRoute();
	}

}
