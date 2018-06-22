/*
	A non-empty zero-indexed array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).

	For example, array A such that:

	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8

	Contains the following example slices:
		
		slice (1, 2), whose average is (2 + 2) / 2 = 2;
		slice (3, 4), whose average is (5 + 1) / 2 = 3;
		slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.

	The goal is to find the starting position of a slice whose average is minimal.

	Write a function:
		class Solution { public int solution(int[] A); }
		that, given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
	
	For example, given array A such that:
	
	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8

	The function should return 1, as explained above.
	
	Assume that:

		N is an integer within the range [2..100,000];
		each element of array A is an integer within the range [−10,000..10,000].
		Complexity:
		expected worst-case time complexity is O(N);
		expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
		Elements of input arrays can be modified.
 */

//SCORE: 100/100 (both solutions)
package prefixsums;




// REQUIRES PRACTICE


/*
* The goal is to find the starting position 
* of a slice whose average is minimal.
*/
public class MinAvgTwoSlice {
	

	/*
		The goal is to find the starting position 
		of a slice whose average is minimal.

		The goal is to find the starting position 
		of a slice whose average is minimal.
	*/


	/*
	* Using caterpillar method (min value 
	* will be inside 2 or 3 element slices)
	*/
	public static int solution(int[] A) {

        // Slice (1, 2), whose average is (2 + 2) / 2 = 2;
        // Slice (3, 4), whose average is (5 + 1) / 2 = 3;
        // Slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.

        // for the slice, we will need endIndex and startIndex. Lets define that.
        int endIndex = 1;
        int startIndex = 0;

        int resultIndex = 0;
        int currentSum = A[0] + A[1];

        double min = (double)currentSum/2;
        double tmpMin = min;

        /*
		Algorithms
		----------
		Premise: The slice provies the min average will formed of 2 to 3 elements

			0.   Define the startIndex =0 and endIndex = 1 
			i.   Get the avg for the initial 2 elments (with these indexs)
			ii.  Get the avg for the initial 3 elments by moving the start 1 index
			iii. update the result comparing them
			iv.  move forward the endIndex 1 step and repeat the process 
		
        */
        while (true) {

            if (endIndex - startIndex == 1) {

                endIndex++;

                // return the min average of last 2 elments 
                if (endIndex == A.length){
                    return resultIndex;
                }

                currentSum += A[endIndex];
            }

            else {
                currentSum -= A[startIndex];
                startIndex++;
            }

            tmpMin=(double)currentSum/(endIndex-startIndex+1);

            if (tmpMin < min) {
                resultIndex = startIndex;
                min = tmpMin;
            }
        }
    }




	/*
	* Solution -b
	*/
	public static int solution1(int[] A) {

	
		int res = 0;
		double min = (double)(A[0]+A[1])/2;
		
		for (int j = 0; j < A.length-2; j++) {

			if ((double)(A[j] + A[j+1]) / 2 < min){
				min = (double)(A[j] + A[j+1]) / 2;
				res=j;
			}

			if ((double)(A[j] + A[j+1] + A[j+2]) / 3 < min){				
				min = (double)(A[j] + A[j+1] + A[j+2]) / 3;
				res=j;
			}
		}
		
		if ((double)(A[A.length-1] + A[A.length-2])/2 < min){
			return A.length - 2;
		}
		
		return res;
	}
	// END of Solution - b


	/*
	* solution - c
	*/
	public int solution(int[] A) {
    
        double minAvg = 100000;
        int index=0;
    
        if(A.length<=2) {
            return 0;
        }
    
        for(int i=0; i<A.length-2; i++) {
    
            if((A[i]+A[i+1])/2.0 < minAvg) {
                minAvg=(A[i]+A[i+1])/2.0;
                index=i;
            }
    
            if((A[i]+A[i+1]+A[i+2])/3.0<minAvg)  {
    
                minAvg=(A[i]+A[i+1]+A[i+2])/3.0;
                index=i;
            }
        }
    
        int aMax = A.length-2;
    
        if((A[aMax]+A[aMax+1])/2.0<minAvg) {
    
            minAvg=(A[aMax]+A[aMax+1])/2.0;
            index=aMax;
        }
    
        return index;
    }
    // END of Solution -c


	public static void main(String[] args) {
	
		int[] A = new int[]{4,2,2,5,1,5,8}; 
		System.out.println(solution1(A));
	}
}
