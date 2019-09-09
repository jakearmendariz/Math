package historyofmath;

import java.util.ArrayList;
import java.util.*;
/**
 * PerfectNumbers.java
 * @author jakearmendariz
 * Finds tbe perfect numbers up to a given bound.
 * Becuase of java's long limited size I was only able to find the first 8 perfect number however the run time is fairly fast (roughly 11.5 sec on my laptop)
 *
 */

public class PerfectNumbers {
	public static ArrayList<Long> getPerfect(long bound){
		ArrayList<Long> perfect = new ArrayList<Long>();
		Primes a = new Primes();
		ArrayList<Integer> primes;
		
		//This shortens run time by quite a lot by using smaller bounds
		if(bound < 20) {
			primes = a.getPrimes((int)bound);
		}
		else if(bound > (long) 2E11) {
			primes = a.getPrimes(31);
		}
		else {
			primes = a.getPrimes(20);
		}
		//a.printList(primes);
		//System.out.println();
		int range = 1;
		while(true) {
			//System.out.println(range + " is range ");
			if(range == primes.size()) {
				break;
			}
			//System.out.println((Math.pow(2, primes.get(range))-1) * (Math.pow(2,  primes.get(range)-1)));
			
			if(((long)((Math.pow(2, primes.get(range))-1) * Math.pow(2,  primes.get(range)-1)) > bound)) {
				
				break;
			}
			
			range++;
		}
		
		
		for(int i = 1; i < primes.size(); i++) {
		//	System.out.println("Checking if "+(int)(Math.pow(2, primes.get(i))-1)+ " is prime");
			if(isNumPrime((long)(Math.pow(2, primes.get(i))-1))) {
				if((long) ((Math.pow(2, primes.get(i))-1) * Math.pow(2,  primes.get(i)-1)) <= bound) {
					perfect.add((long) ((Math.pow(2, primes.get(i))-1) * Math.pow(2,  primes.get(i)-1)));
					//System.out.print(perfect.get(perfect.size()-1) + " ");
				}
			}
			
			
			
			
		}
		
		return perfect;
	}
	
	public static boolean isPerfect(ArrayList<Integer> factors,int n) {
		int sum = 0;
		for(int i = 0; i < factors.size(); i++) {
			sum += factors.get(i);
		}
		return sum == n;
	}
	
	public static boolean isNumPrime(long n) {
		ArrayList<Long> factors = getFactors(n);
		return factors.size() == 1;
	}
	
	public static ArrayList<Long> getFactors(long n){
		ArrayList<Long> factors = new ArrayList<Long>();
		long test = 1;
	
		while(test <= n/2) {

			if(n%test == 0) {
				factors.add(test);	
			}
			test++;
		}
		return factors;
	}
	
	public static void printLongList(ArrayList<Long> primes) {
		for(int i = 0; i < primes.size(); i++) {
			System.out.print(primes.get(i) + " ");
			if(i%20 == 0 && i != 0) {
				System.out.println();
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		Primes a = new Primes();
		System.out.println("Finding all perfect numbers up to what bound? (Enter -1 for max amount of perfect program can find)");
		Scanner scan = new Scanner(System.in);
		long bound = scan.nextLong();
		if(bound == -1) {
			bound = (long) 2.35E18;
		}
		long time = System.currentTimeMillis();
		ArrayList<Long> perfect = getPerfect(bound);
		System.out.print("Perfect Numbers: ");
		printLongList(perfect);
		time = System.currentTimeMillis() - time;
		System.out.println("\n\nProgram executed in " + time + " milliseconds");
	}
	
}
