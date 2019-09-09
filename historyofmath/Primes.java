package historyofmath;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Primes.java
 * @author jakearmendariz
 * Can find all prime numbers simply by checking every other number to be divisible by other primes
 */
public class Primes {
	
	public ArrayList<Integer> getPrimes(int bound){
		ArrayList<Integer> primes = new ArrayList<Integer>();
		//The extra one is just there to make the output pretty :)
		primes.add(1);
		primes.add(1);
		primes.add(2);
		int test = 3;
		while(test <= bound) {
			if(isPrime(test, primes) == true) {
				primes.add(test);
				//System.out.print(test+ " ");
				if(primes.size()%20 == 0) {
					//System.out.println();
				}
			}
			test+=2;
		}
		
		
		return primes;
	}
	
	
	public boolean isPrime(int n, ArrayList<Integer> list) {
		
		for(int i = 2; i < list.size(); i++) {
			//If it is divisible by any primes
			if(n%list.get(i) == 0) {
				return false;
			}
			//Stops it from checking primes over half the size
			if(list.get(i)*2 > n) {
				break;
			}
		}
		return true;
	}
	
	public void printList(ArrayList<Integer> primes) {
		for(int i = 1; i < primes.size(); i++) {
			System.out.print(primes.get(i) + "\t");
			if(i%20 == 0 && i != 0) {
				System.out.println();
			}
		}
	}
	
	
	/**
	 * Calls getPrimes method and prints out all primes from 1 - whichever number set by the bound
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Finding all primes up to what bound? (inclusive)");
		Scanner scan = new Scanner(System.in);
		int bound = scan.nextInt();
		
		Primes a = new Primes();
		long time = System.currentTimeMillis();
		ArrayList<Integer> primes = a.getPrimes(bound);
		time = System.currentTimeMillis() - time;
		System.out.println("\nThere exists " + (primes.size()-1) + " primes between 1 and " + bound);
		a.printList(primes);
		double percent = primes.size()/(double)bound;
		double r = percent*1000;
		r = Math.round(r);
		r = r/10;
		System.out.println("\n\nThere are " + r + "% primes from 0 to " + bound);
		System.out.println("\n\nProgram executed in " + time + " milliseconds\n");
		
	}
}
