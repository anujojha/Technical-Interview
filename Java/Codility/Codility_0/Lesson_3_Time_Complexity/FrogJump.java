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
		double has higher precision, whereas floats take up less memory and 
		are faster. In general you should use float unless you have a case 
		where it isn't accurate enough. 

		float is represented in 32 bits, with 1 sign bit, 8 bits of exponent, 
		and 23 bits of the significand (or what follows from a scientific-notation 
		number: 2.33728*1012; 33728 is the significand).

		double is represented in 64 bits, with 1 sign bit, 11 bits of exponent, and 
		52 bits of significand.

		By default, Java uses double to represent its floating-point numerals (so a 
		literal 3.14 is typed double). It's also the data type that will give you a 
		much larger number range, so I would strongly encourage its use over float.
	*/
	public static int solution(int x, int y, int d) {
		return (int)Math.ceil((double)(y-x)/d);
	}

	public static void main(String[] args) {

		int A = 10;
		int B = 85;
		int D = 30;
		System.out.println(solution(A,B, D));
	}
}
