/*
	A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
	Write a function:
	int solution(int A[], int N);
	that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
	For example, given array A such that:
	A[0] = 3  A[1] = 2  A[2] = -6
	A[3] = 4  A[4] = 0
	the function should return 5 because:
	(3, 4) is a slice of A that has sum 4,
	(2, 2) is a slice of A that has sum −6,
	(0, 1) is a slice of A that has sum 5,
	no other slice of A has sum greater than (0, 1).
	Assume that:
	N is an integer within the range [1..1,000,000];
	each element of array A is an integer within the range [−1,000,000..1,000,000];
	the result will be an integer within the range [−2,147,483,648..2,147,483,647].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

/*
* A non-empty array A consisting of N integers is given. A pair of 
* integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array 
* A. The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
*/
public class MaxSliceSum {
	


	// A = {5, -1, -8, 3, 4}
	/*
		A[0] = 3  A[1] = 2  A[2] = -6
		A[3] = 4  A[4] = 0
		the function should return 5 because:
		(3, 4) is a slice of A that has sum 4,
		(2, 2) is a slice of A that has sum −6,
		(0, 1) is a slice of A that has sum 5,
		no other slice of A has sum greater than (0, 1).
	*/
	/*
	* solution -a
	*/	
	public static int solution(int[] A) {
	
		int max=Integer.MIN_VALUE;

		// find the max value 
		for (int i = 0; i < A.length; i++) {
			max = A[i] > max?A[i]:max;
		}
	
		if(max<=0){
			return max;
		}
		
		int maxSliceSum = 0;
		int currentSum = 0;

		/*
			// Algorithms 
			// ----------
			
			i.   Keep incrementing the slice as long as its > 0
			ii.  Otherwise, put the current slice = 0
			iii. Compare the currSlice with maxSlice and return maxSlice
		*/

		for (int i = 0; i < A.length; i++) {			
			currentSum = (currentSum + A[i]) > 0 ? (currentSum + A[i]):0;
			maxSliceSum= currentSum > maxSliceSum ? currentSum : maxSliceSum;
		}

		return maxSliceSum;
	}




	/*
	* solution - b
	*/	
	public int solution(int[] A) {

        int length = A.length;
        int maxEnding = A[0];
        int maxSlice = A[0];
        
        for(int i=1;i<length;i++) {
            maxEnding = Math.max(A[i], maxEnding + A[i]);
            maxSlice = Math.max(maxEnding, maxSlice);
        }
        
        return maxSlice;
    }



    /*
	* solution - c
	*/	
    public int solution(int[] a) {
    	
        final long[] lms = new long[a.length];
        final long[] rms = new long[a.length];
        long minSum = 0;
        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            lms[i] = minSum;
            sum += (long) a[i];
            if (sum < minSum) {
                minSum = sum;
            }
        }
        final long total = sum;
        minSum = 0;
        sum = 0;
        for (int i = a. length - 1; i >= 0; i--) {
            rms[i] = minSum;
            sum += (long) a[i];
            if (sum < minSum) {
                minSum = sum;
            }
        }
        long result = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            sum = total - lms[i] - rms[i];
            if (sum > result) {
                result = sum;
            }
        }
        return (int) result;
    }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[]{3,2,-6,4,0};
		System.out.println(solution(A));
	}
}
