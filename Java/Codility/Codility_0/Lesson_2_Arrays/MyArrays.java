import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;


public class MyArrays {


    // CyclicRotation : Rotate an array to the right by a given number of steps

	// A zero-indexed array A consisting of N integers is given. Rotation of the array 
	// means that each element is shifted right by one index, and the last element of 
	// the array is also moved to the first place.

	// For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal 
	// is to rotate array A K times; that is, each element of A will be shifted to the right 
	// by K indexes.

	// Write a function:

	// class Solution { public int[] solution(int[] A, int K); }

	// that, given a zero-indexed array A consisting of N integers and an integer K, returns the 
	// array A rotated K times.

	// For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return 
	// [9, 7, 6, 3, 8].

	// Assume that:

	// N and K are integers within the range [0..100];
	// each element of array A is an integer within the range [−1,000..1,000].
	// In your solution, focus on correctness. The performance of your solution will not be the 
	// focus of the assessment.


	/*solution-a*/
	public static int swap(int itself , int dummy){
		return itself;
	}

	public static void reverse(int[] A, int start, int end){

		int i = start, j = end;
		while(i < j){

			A[i] = swap(A[j], A[j] = A[i]);

            // x = A[i], y = A[j]
            // x = x ^ y;  // x now becomes 15 (1111)
            // y = x ^ y;  // y becomes 10 (1010)
            // x = x ^ y;  // x becomes 5 (0101)

			i++;
			j--;
		}
	}

	public int[] solution(int[] A, int K) {

		if(A == null || K > A.length){
			return null;
		}

		reverse(A, 0, A.length -1);
		reverse(A, 0, K-1);
		reverse(A, K, A.length -1);

		return A;
	}
	/*ENd of solution-a*/





    /**
    *  K = 3
    *  A = [3, 8, 9, 7, 6] 
    *  A = [9, 7, 6, 3, 8]
    */

	/*End of solution-b*/
	public static int[] solution1(int[] arr, int n) {

		Map<Integer, Integer> map = new HashMap<>();

		for(int j = 0; j < arr.length; j++){

			if(j+n > arr.length -1){
				map.put(j+n - arr.length, arr[j]);
				continue;
			}
			map.put(j+n, arr[j]);
		}

		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			arr[entry.getKey()] = entry.getValue();
		}

		return arr;
	}
	/*ENd of solution-b*/






    /*OddOccurrencesInArray: Find value that occurs in odd number of elements.*/
    
	/* A non-empty zero-indexed array A consisting of N integers is given. The array contains 
	an odd number of elements, and each element of the array can be paired with another element 
	that has the same value, except for one element that is left unpaired.

	For example, in array A such that:

	  A[0] = 9  A[1] = 3  A[2] = 9
	  A[3] = 3  A[4] = 9  A[5] = 7
	  A[6] = 9
	the elements at indexes 0 and 2 have value 9,
	the elements at indexes 1 and 3 have value 3,
	the elements at indexes 4 and 6 have value 9,
	the element at index 5 has value 7 and is unpaired.
	Write a function:

	class Solution { public int solution(int[] A); }

	that, given an array A consisting of N integers fulfilling the above conditions, returns 
	the value of the unpaired element.

	For example, given array A such that:

    // {9, 3, 9, 3, 9, 7, 9}

	  A[0] = 9  A[1] = 3  A[2] = 9
	  A[3] = 3  A[4] = 9  A[5] = 7
	  A[6] = 9
	the function should return 7, as explained in the example above.

	Assume that:

	N is an odd integer within the range [1..1,000,000];
	each element of array A is an integer within the range [1..1,000,000,000];
	all but one of the values in A occur an even number of times.
	Complexity:

	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting 
	the storage required for input arguments).
	Elements of input arrays can be modified.*/

	public int solution(int[] arr) {
        
        int result = arr[0];

        for (int j =1; j < arr.length; j++) {
            result ^= arr[j];
        }

        return result;
    }
	/*ENd of solution */




	
	public static void main(String[] args) {

		System.out.println("Seattle");	
		int[] arr = {3, 8, 9, 7, 6};
	}
}