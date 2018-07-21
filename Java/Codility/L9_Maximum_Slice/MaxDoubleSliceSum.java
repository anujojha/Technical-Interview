/*
	A non-empty zero-indexed array A consisting of N integers is given.
	A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
	The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
	For example, array A such that:
	    A[0] = 3
	    A[1] = 2
	    A[2] = 6
	    A[3] = -1
	    A[4] = 4
	    A[5] = 5
	    A[6] = -1
	    A[7] = 2
	contains the following example double slices:
	double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
	double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
	double slice (3, 4, 5), sum is 0.
	The goal is to find the maximal sum of any double slice.
	
	Write a function:

		class Solution { public int solution(int[] A); }
		that, given a non-empty zero-indexed array A consisting of 
		N integers, returns the maximal sum of any double slice.


	For example, given:
	    A[0] = 3
	    A[1] = 2
	    A[2] = 6
	    A[3] = -1
	    A[4] = 4
	    A[5] = 5
	    A[6] = -1
	    A[7] = 2
	the function should return 17, because no double slice of array A has a sum of greater than 17.
	Assume that:
	N is an integer within the range [3..100,000];
	each element of array A is an integer within the range [−10,000..10,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */



// The goal is to find the maximal sum of any double slice
public class MaxDoubleSliceSum {
	

	/*
	* A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
	* The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... 
	* + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
	*/
	/*
	* solution - a
	*/
	public static int solution(int[] A) {
		
		int max = 0;

		int[] A1 = new int[A.length];
		int[] A2 = new int[A.length];

		// A1
		// A
		// [1,.., .., N-2]		
		for (int i = 1; i < A.length-1; i++) {
			A1[i] = Math.max(A1[i-1] + A[i], 0);
		}

		for (int i = A.length -2; i >=1; i--) {
			A2[i] = Math.max(A2[i+1] + A[i], 0);
		}
		
		for (int i = 1; i < A.length-1; i++) {
			max = Math.max(max, A1[i-1] + A2[i+1]);
		}
		
		return max;
	}



	// public int solution(int[] A) {
		
	// 	int N = A.length;
	// 	int[] K1 = new int[N];
	// 	int[] K2 = new int[N];

	// 	for(int i = 1; i < N-1; i++){
	// 		K1[i] = Math.max(K1[i-1] + A[i], 0);
	// 	}
	// 	for(int i = N-2; i > 0; i--){
	// 		K2[i] = Math.max(K2[i+1]+A[i], 0);
	// 	}

	// 	int max = 0;

	// 	for(int i = 1; i < N-1; i++){
	// 		max = Math.max(max, K1[i-1]+K2[i+1]);
	// 	}

	// 	return max;
 //    }


	/*
	* solution - b
	*/
    public int solution(int[] a) {
        int sum = 0;
        final int[] lms = new int[a.length];
        int minSum = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum < minSum) {
                minSum = sum;
            }
            lms[i] = minSum;
        }
        final int total = sum;
        sum = 0;
        final int[] rms = new int[a.length];
        minSum = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            sum += a[i];
            if (sum < minSum) {
                minSum = sum;
            }
            rms[i] = minSum;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < a.length - 1; i++) {
            sum = total - a[i] - lms[i - 1] - rms[i + 1];
            if (result < sum) {
                result = sum;
            }
        }
        return result;
    }

    // 14, 14, 17, 17 

	public static void main(String[] args) {
		
		int[] A = new int[]{3,2,6,-1,4,5,-1,2};
		System.out.println(solution(A));
	}
}

