/*
	You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:
	with your first step you can stand on rung 1 or 2,
	if you are on rung K, you can move to rungs K + 1 or K + 2,
	finally you have to stand on rung N.
	Your task is to count the number of different ways of climbing to the top of the ladder.
	For example, given N = 4, you have five different ways of climbing, ascending by:
	1, 1, 1 and 1 rung,
	1, 1 and 2 rungs,
	1, 2 and 1 rung,
	2, 1 and 1 rungs, and
	2 and 2 rungs.
	Given N = 5, you have eight different ways of climbing, ascending by:
	1, 1, 1, 1 and 1 rung,
	1, 1, 1 and 2 rungs,
	1, 1, 2 and 1 rung,
	1, 2, 1 and 1 rung,
	1, 2 and 2 rungs,
	2, 1, 1 and 1 rungs,
	2, 1 and 2 rungs, and
	2, 2 and 1 rung.
	The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.
	Write a function:
	class Solution { public int[] solution(int[] A, int[] B); }
	that, given two non-empty zero-indexed arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].
	For example, given L = 5 and:
	    A[0] = 4   B[0] = 3
	    A[1] = 4   B[1] = 2
	    A[2] = 5   B[2] = 4
	    A[3] = 5   B[3] = 3
	    A[4] = 1   B[4] = 1
	the function should return the sequence [5, 1, 8, 0, 1], as explained above.
	Assume that:
	L is an integer within the range [1..30,000];
	each element of array A is an integer within the range [1..L];
	each element of array B is an integer within the range [1..30].
	Complexity:
	expected worst-case time complexity is O(L);
	expected worst-case space complexity is O(L), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/75
package fibonaccinumbers;

import java.math.BigInteger;
import java.util.Arrays;

 
public class Ladder {

	
	int number = 0;
	

	/*
	* Count the number of different ways of 
	* climbing to the top of the ladder
	* 
	* Your task is to count the number of different 
	* ways of climbing to the top of the ladder.
	*
	* The number of different ways can be very large, so it is sufficient 
	* to return the result modulo 2P, for a given integer P.

	* position I should contain the number of different ways of climbing 
	* the ladder with A[I] rungs modulo 2B[I]
	*/

	/*
	* solution - a
	*/
	public static int[] solution(int[] A, int[] B) {


		BigInteger[] fibs = new BigInteger[A.length + 2];
		
		fibs[0] = new BigInteger("0");
		fibs[1] = new BigInteger("1");
		

		// 0,1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
		// 0,1    // 1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597
		for (int i = 2; i < A.length + 2; i++) {
			fibs[i] = fibs[i-1].add(fibs[i-2]);
		}

		int[] res = new int[A.length];

		// position I should contain the number of different ways of 
		// climbing the ladder with A[I] rungs modulo 2B[I]

		// A = [4,4,5,5,1]
		for (int i = 0; i < B.length; i++) {

			BigInteger currPow = new BigInteger(String.valueOf((long)Math.pow(2, B[i])));
			res[i] = fibs[A[i]+1].mod(currPow).intValue();
		}
		
		return res;
	}




	/*
	* solution - b
	*/
	public  int[] solution(int[] A, int[] B) {

        int N = A.length;

        int[] fib = new int[N + 2];
        int[] result = new int[N];

        fib[1] = 1;

        for (int j = 0; j < N; j++) {

            // make sure the fibonacci number will not exceed the max integer in java 1<<n = 2^n
            int i = j + 2;
            fib[i] = (fib[i - 1] + fib[i - 2]) % (1 << 30);
        }

        for (int i = 0; i < N; i++) {
            result[i] = fib[A[i]+1] % (1 << B[i]);
        }

        return result;
    }







    /*
	* solution - c
	*/
    public int[] solution(int[] a, int[] b) {

        final int[] cn = new int[a.length < 2 ? 2 : a.length];

        cn[0] = 1;
        cn[1] = 2;

        // numbers of combinations are fibonacci numbers
        // if we take combinations for n - 2, we should add one 2 or two 1 to some places.
        // but any of these transformations are reduced to the following forms:

	        // 1. combination from (n - 1) and 1
	        // 2. combination from (n - 2) and 2

        // the same for n - 1. we can should add one 1 to some places.
        // it gives the same possible outcomes as in the previous case.
        // Hence the number of combination for n is number of combinations for n - 1 and n - 2
        // At the same time it cannot be less than this. Suppose it was.
        // Since all the combinations from n - 1 are appended only by 1 and for n - 2 by 2,
        // the parts from n - 1 and n - 2 are present only once maximum. In our case it means that some parts are missed.
        // but it also means, that we can add this part plus 1 or 2. Contraction.
        for (int i = 2; i < a.length; i++) {

            // we store values by mod 2**30.
            // we can do it because (x mod a + y mod a) mod a == (x + y) mod a
            //later they are easily converted to modulo by power of 2 by removing bits.
            // 0101 & 2**2 = 0101 & 0011
            cn[i] = (cn[i - 1] + cn[i - 2]) & ((1 << 30) - 1);
        }

        for (int i = 0; i < a.length; i++) {
            a[i] = cn[a[i] - 1] & ((1 << b[i]) - 1);
        }
        return a;
    }

	public static void main(String[] args) {
	
		int[] A = new int[]{4,4,5,5,1};
		int[] B = new int[]{3,2,4,3,1};
	
		System.out.println(Arrays.toString(solution(A,B)));
	}
}
