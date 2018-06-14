/*
	Two positive integers N and M are given. Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
	You start to eat the chocolates. After eating a chocolate you leave only a wrapper.
	You begin with eating chocolate number 0. Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
	More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
	You stop eating when you encounter an empty wrapper.
	For example, given integers N = 10 and M = 4. You will eat the following chocolates: 0, 4, 8, 2, 6.
	The goal is to count the number of chocolates that you will eat, following the above rules.
	Write a function:
	class Solution { public int solution(int N, int M); }
	that, given two positive integers N and M, returns the number of chocolates that you will eat.
	For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
	Assume that:
	N and M are integers within the range [1..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(log(N+M));
	expected worst-case space complexity is O(1).
 */

//SCORE: 100/100
package euclideanalgorithm;

public class ChocoladeByNumbers {


	/*
		If you ate chocolate number X, then you will next eat the chocolate with 
		number (X + M) modulo N (remainder of division).

		You stop eating when you encounter an empty wrapper.

		For example, given integers N = 10 and M = 4. You will eat the following 
		chocolates: 0, 4, 8, 2, 6.

		The goal is to count the number of chocolates that you will eat. 
	*/


	// ƒ0, 1, ƒ2, 3, ƒ4, 5, ƒ6, 7, ƒ8, 9	

	// Greatest common divisor (gcd) of two or more integers, which are not all 
	// zero, is the largest positive integer that divides each of the integers. 

	// N = Chocolate circle 
	// M = Gap 	
	private static int solution(int N, int M) {	
		
		int a = gcd(N,M,1);
		return N/a;
	}

	// a = Num. of chocolate 
	// b = Gap 	
	private static int gcd(int a, int b, int res) {

		if (a==b){
			return res*a;
		}

		else if (a%2==0 && b%2==0){
			return gcd (a/2, b/2, res*2);
		}

		else if (a%2==0){
			return gcd (a/2, b, res);
		}

		else if (b%2==0){
			return gcd (a, b/2, res);
		}

		else if (a>b){
			return gcd (a - b, b, res);
		}

		else {
			return gcd (a, b - a, res);
		} 			
	}

	public static void main(String[] args) {
		int N =12;
		int M =3;
		int res=1;
		System.out.println(solution(N,M,res));
	}
}
