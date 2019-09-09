package historyofmath;
import java.util.*;

/**
 * @author jakeaarmendariz
 * Math 181
 * 
 * AnyBase
 * This class can take any number up to 9223372036854775807 and any base up to  2147483647
 * and find it's value. If you enter 2 as a base the answer is in simple binary.
 * However one can find any use any base they'd like
 * This is a spin on the different number system's cultures used such as a the babylonians with a base 60 system
 */
public class AnyBase {

	// Handles larger numbers
	public static long[] getDigits(long N, long b) {
		long power = 0;
		while (Math.pow(b, power) <= N) {
			power++;
		}

		long[] result = new long[(int)power];
		while (N > 0 && power > 0) {
			power--;
			long c = 0;
			while (c * Math.pow(b, power) <= N) {
				c++;
			}
			c--;
			N = (long) (N - (c * Math.pow(b, power)));
			result[(int)power] = c;
		}
		return result;
	}
	
	
	//This is a basic UI to allow running of the program
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long b;
		System.out.println("Enter any large number under 9,223,372,036,854,775,807");
		long a = in.nextLong();
		System.out.println("Enter any number smaller than the first and 2,147,483,647");
		b = in.nextLong();
		long arr[] = getDigits(a, b);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n");
		
		long[] ar = new long[arr.length];
		for (int i = 0; i < arr.length; i++) {
			long x = (long) (arr[i] * Math.pow(b, i));
			ar[i] = x;
			System.out.println(arr[i] + " * " + b + " ^ " + i + " = " + x);
			//System.out.prlongln(Math.pow(b, i));
		}
		System.out.println("");
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			System.out.print(ar[i] + " + ");
			sum += ar[i];
		}
		System.out.println("= " + a);
	}

}
