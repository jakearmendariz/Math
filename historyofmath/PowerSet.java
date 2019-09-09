package historyofmath;

public class PowerSet {
	// str : Stores input string  
    // curr : Stores current subset  
    // index : Index in current subset, curr  
    static void powerSet(String str, int index, 
                            String curr)  
    { 
        int n = str.length(); 
  
        // base case  
        if (index == n) 
        { 
            return; 
        } 
  
        // First print current subset  
        if(curr.contains("2") &&  curr.contains("3") && curr.contains("5")) {
        	System.out.println(curr); 
        }
        
  
        // Try appending remaining characters  
        // to current subset  
        for (int i = index + 1; i < n; i++)  
        { 
            curr += str.charAt(i); 
            powerSet(str, i, curr); 
  
            // Once all subsets beginning with  
            // initial "curr" are printed, remove  
            // last character to consider a different  
            // prefix of subsets.  
            curr = curr.substring(0, curr.length() - 1); 
        } 
    } 
  
    // Driver code  
    public static void main(String[] args)  
    { 
        String str = "123456"; 
        int index = -1; 
        String curr = ""; 
        powerSet(str, index, curr); 
    } 
}/*
	public static int[][] powerSet(int[] a) {
		//A power set has 2^n as many sets with up to 1+elements per set (including empty)
		int[][] pow = new int[(int) Math.pow(2, a.length)][a.length];
		for(int i = 1; i <= a.length; i++) {
			pow[i][0]= a[i-1];
		}
		int row= a.length+1;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a.length-1;j++) {
				pow[row][j] = 
			}
			row++;
		}
		
		return pow;
	}
	
	public static void main(String[] jake) {
		int[] a = {1, 2, 3};
		int[][] pow= powerSet(a);
		for(int i = 0; i < pow.length; i++) {
			System.out.print("{");
			for(int j = 0; j < pow[0].length; j++) {
				System.out.print(pow[i][j]+ ",");
			}
			System.out.println("}");
		}
		
	}
	8?
}
*/
