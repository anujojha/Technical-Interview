/*
	A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
	For example, array A such that:
	  A[0] = -3
	  A[1] = 1
	  A[2] = 2
	  A[3] = -2
	  A[4] = 5
	  A[5] = 6
	contains the following example triplets:
	(0, 1, 2), product is −3 * 1 * 2 = −6
	(1, 2, 4), product is 1 * 2 * 5 = 10
	(2, 4, 5), product is 2 * 5 * 6 = 60
	Your goal is to find the maximal product of any triplet.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.
	For example, given array A such that:
	  A[0] = -3
	  A[1] = 1
	  A[2] = 2
	  A[3] = -2
	  A[4] = 5
	  A[5] = 6
	the function should return 60, as the product of triplet (2, 4, 5) is maximal.
	Assume that:
	N is an integer within the range [3..100,000];
	each element of array A is an integer within the range [−1,000..1,000].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */



/*
* Your goal is to find the maximal 
* product of any triplet.
*/
public class MaxProductOfThree {


	/*
	* solution -a
	*/
	public static int solution(int[] A) {

		Arrays.sort(A);

        // all the elements are positives 
		int max1 = A[A.length-1] *A[A.length-2] *A[A.length-3];

		// mix of positives and negatives 
		int max2 = A[A.length-1] * A[0] *A[1];
		
		return max1 > max2 ? max1:max2;
	}



	/*
	* solution -b
	*/
	public int solution(int[] A) {
        
        int[] maxes = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        // Invariant: maxes[0] <= maxes[1] <= maxes[2]
        
        int[] mins = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        // Invariant: mins[0] <= mins[1]

        // O(n)        
        for( int a : A ){
            updateMaxes( a, maxes );
            updateMins( a, mins );
        }
         
        int obvious = maxes[0] * maxes[1] * maxes[2];
        int twoBigNegs = mins[0] * mins[1] * maxes[2];
 
        return Math.max( obvious, twoBigNegs );
    }    

    private static void updateMaxes( int a, int[] maxes ){

        if( a >= maxes[2] ) {
            // Found new max, shift down
            maxes[0] = maxes[1];
            maxes[1] = maxes[2];
            maxes[2] = a;
        } else if( a >= maxes[1] ) {
            maxes[0] = maxes[1];
            maxes[1] = a;
        } else if( a > maxes[0] ) {
            maxes[0] = a;
        }
    }

    private static void updateMins( int a, int[] mins ){

        if( a <= mins[0] ) {
            // Found new min, shift down
            mins[1] = mins[0];
            mins[0] = a;
        } else if( a < mins[1] ) {
            mins[1] = a;
        }
    }




    /*
	* soilution -c
    */
    // O(n) solution. O(n * log(n)) involves sorting, than calculation two possible maximums.
    // first, second, last (in case first and second are negative and give product more than last - 2 and last - 1)
    // and last - 2, last - 1, last.
    public int solution(int[] a) {


        int minOne = a[0] > a[1] ? a[1] : a[0];
        int maxOne = a[0] > a[1] ? a[0] : a[1];

        int minTwo = a[0] * a[1];
        int maxTwo = a[0] * a[1];
        
        int result = Integer.MIN_VALUE;
        

        for (int i = 2; i < a.length; i++) {

            // find max triplet value
            int tmp = minTwo * a[i];

            if (tmp > result) {
                result = tmp;
            }
            tmp = maxTwo * a[i];
            if (tmp > result) {
                result = tmp;
            }
            // find max/min duplets values
            tmp = minOne * a[i];
            if (tmp < minTwo) {
                minTwo = tmp;
            }
            if (tmp > maxTwo) {
                maxTwo = tmp;
            }
            tmp = maxOne * a[i];
            if (tmp < minTwo) {
                minTwo = tmp;
            }
            if (tmp > maxTwo) {
                maxTwo = tmp;
            }
            // find max/min values;
            if (a[i] < minOne) {
                minOne = a[i];
            }
            if (a[i] > maxOne) {
                maxOne = a[i];
            }
        }
        return result;
    }


	public static void main (String[] args) {
		int[] A = new int[] {-3,1,2,-2,5,6};
		System.out.println(solution(A));
	}
}

