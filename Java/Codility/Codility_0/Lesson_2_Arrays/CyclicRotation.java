/*
An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).

The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

Write a function:

class Solution { public int[] solution(int[] A, int K); }

that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

For example, given

    A = [3, 8, 9, 7, 6]
    K = 3
the function should return [9, 7, 6, 3, 8]. Three rotations were made:

    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
For another example, given

    A = [0, 0, 0]
    K = 1
the function should return [0, 0, 0]

Given

    A = [1, 2, 3, 4]
    K = 4
the function should return [1, 2, 3, 4]

Assume that:

N and K are integers within the range [0..100];
each element of array A is an integer within the range [−1,000..1,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
*/

public  class CyclicRotation {
	
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


	public static void main(String[] args) {
		System.out.println();
	}
}