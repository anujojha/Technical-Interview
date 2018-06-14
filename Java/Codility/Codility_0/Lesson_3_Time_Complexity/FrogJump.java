/*
	A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
	Count the minimal number of jumps that the small frog must perform to reach its target.
	Write a function:
	class Solution { public int solution(int X, int Y, int D); }
	that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
	For example, given:
	  X = 10
	  Y = 85
	  D = 30
	the function should return 3, because the frog will be positioned as follows:
	after the first jump, at position 10 + 30 = 40
	after the second jump, at position 10 + 30 + 30 = 70
	after the third jump, at position 10 + 30 + 30 + 30 = 100
	Assume that:
	X, Y and D are integers within the range [1..1,000,000,000];
	X ≤ Y.
	Complexity:
	expected worst-case time complexity is O(1);
	expected worst-case space complexity is O(1).
 */

//SCORE: 100/100
package timecomplexity;

public class FrogJump {

	/*
	* solution -a 
	*/
	public static int solution(int x, int y, int d) {
		return (int)Math.ceil((double)(y-x)/d);
	}

	/*
	* solution -b
	*/
	public int solution(int x, int y, int d) {
        final int diff = y - x;
        int result = diff / d;
        if (result * d != diff) {
            result++;
        }
        return result;
    }    

    /*
	* solution -c
    */	
    public int solution(int X, int Y, int D) {

        // write your code in Java SE 8
        if (X>=Y){
            return 0;
        }

        int remainder = (Y-X)%D;//Find the remainder 
        int step = (Y-remainder-X)/D;//Find the quotient
        
        if (remainder>0){
          return step+1;    
        }
        else {
          return step;    
        }        
    }

	public static void main(String[] args) {

		int A = 10;
		int B = 85;
		int D = 30;
		
		System.out.println(solution(A,B, D));
	}
}
