/*
	A non-empty zero-indexed array A consisting of N integers is given. The consecutive elements of array A represent consecutive cars on a road.
	Array A contains only 0s and/or 1s:
	0 represents a car traveling east,
	1 represents a car traveling west.
	The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.
	For example, consider array A such that:
	  A[0] = 0
	  A[1] = 1
	  A[2] = 0
	  A[3] = 1
	  A[4] = 1
	We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the number of passing cars.
	The function should return −1 if the number of passing cars exceeds 1,000,000,000.
	For example, given:
	  A[0] = 0
	  A[1] = 1
	  A[2] = 0
	  A[3] = 1
	  A[4] = 1
	the function should return 5, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer that can have one of the following values: 0, 1.
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
*/
public class PassingCars {

	/*
	* silution -a
	*/
	// The goal is to count passing cars.
	// Array A contains only 0s and/or 1s:

	// 	0 represents a car traveling east,
	// 	1 represents a car traveling west.

	public static int solution(int[] A) {

		int res = 0;
		int ones = 0;

		for (int i = A.length-1; i >= 0; i--) {

			if (A[i] == 1){
				ones++;
			} 

			else {		
				res += ones;

				// The function should return −1 if the number of 
				// pairs of passing cars exceeds 1,000,000,000
				if (res > 1000000000){
					return -1;
				}
			}
		}
		return res;
	}




	/*
	* silution - b
	*/
	public int solution(int[] A) {


        int countOne = 0;
        int countZero= 0;
        int pair = 0;
        int counter = 0;

        for (int i = A.length-1; i>=0;i--){
            counter++;
            if (A[i]==1){
                countOne++;
            } else if (A[i]==0){
                countZero++;
                pair += counter-countZero;
                if (pair > 1000000000){
                    return -1;    
                }
            }    
        }
        
        return pair;
        
    }


    /*
	* solution -c
    */
    // public int solution(int[] a) {
    //     int ones = 0;
    //     int result = 0;
    //     for (int i = a.length - 1; i >= 0; i--) {
    //         if (a[i] == 1) {
    //             ones++;
    //         } else {
    //             result += ones;
    //         }
    //         if (result > 1000000000) {
    //             return -1;
    //         }
    //     }
    //     return result;
    // }

	public static void main(String[] args) {

		int[] A = new int[]{0,1,0,1,1};
		System.out.println(solution(A));
	}
}




