/*
	A non-empty zero-indexed array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
	For example, consider array A such that:
	  A[0] = -5    
	  A[1] = -3    
	  A[2] = -1
	  A[3] =  0    
	  A[4] =  3    
	  A[5] =  6
	The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N numbers, returns absolute distinct count of array A.
	For example, given array A such that:
	  A[0] = -5    
	  A[1] = -3    
	  A[2] = -1
	  A[3] =  0    
	  A[4] =  3    
	  A[5] =  6
	the function should return 5, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
	array A is sorted in non-decreasing order.
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100 (both solutions)
package caterpillar;

import java.util.Arrays;
import java.util.stream.IntStream;


public class AbsDistinct {
	

	//using Java 8
	public static int solution(int[] A) {
		return (int)IntStream.of(A).map(i->Math.abs(i)).distinct().count();
	}

	
	public static int solution1(int[] A) {
	
		int dupls = 0;
	
		for (int i = 0; i < A.length; i++) {
			if(A[i]<0)
				A[i] = -A[i];
		}
		
		Arrays.sort(A);
		for (int i = 1; i < A.length; i++) {
			if(A[i] == A[i-1])
				dupls++;
		}
		return A.length-dupls;
	}




	public int solution(int[] A) {
    
        int N = A.length;
        int head = 0;
        int tail = N - 1;
        int result = 1;

        // the current maximal value
        int currMaxValue = Math.max(Math.abs(A[head]), Math.abs(A[tail]));
        
        // we should be careful of the minimal integer number in JAVA, because
        // the absolute value of it is still a negative number.
        if (A[head] == Integer.MIN_VALUE)
            currMaxValue = Math.abs(A[head]);
        
        while (head <= tail) {
            int currHead = Math.abs(A[head]);
            // the same value of the current maximal value should not be counted
            if (currHead == currMaxValue) {
                head++;
                continue;
            }
            int currTail = Math.abs(A[tail]);
            // the same value of the current maximal value should not be counted
            if (currTail == currMaxValue) {
                tail--;
                continue;
            }
            // get the new current maximal value
            if (currHead >= currTail) {
                currMaxValue = currHead;
                head++;
            } else {
                currMaxValue = currTail;
                tail--;
            }
            // meet a new distinct absolute value
            result++;
        }
        return result;
    }




    public int solution(int[] a) {
        final int[] negatives = new int[a.length];
        int i = 0;
        int j = 0;
        int prev = 1;
        int result = 0;
        for (; i < a.length && a[i] < 0; i++) {
            if (prev != a[i]) {
                negatives[j] = -a[i];
                prev = a[i];
                j++;
                result++;
            }
        }
        
        prev = -1;
        for (--j; i < a.length; i++) {
            while (j >= 0 && negatives[j] < a[i]) {
                j--;
            }
            if (j < 0 || negatives[j] != a[i]) {
                if (prev != a[i]) {
                    result++;
                    prev = a[i];
                }
            }
        }
        return result;
    }

	public static void main(String[] args) {
		int[] A = new int[]{-5,-3,-1,0,3,6};
		System.out.println(solution(A));
	}
}
