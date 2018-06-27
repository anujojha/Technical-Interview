/*
	A non-empty zero-indexed array A consisting of N integers is given.
	A permutation is a sequence containing each element from 1 to N once, and only once.
	For example, array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	    A[3] = 2
	is a permutation, but array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	is not a permutation, because value 2 is missing.
	The goal is to check whether array A is a permutation.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	    A[3] = 2
	the function should return 1.
	Given array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	the function should return 0.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [1..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100


import java.util.Arrays;

public class PermCheck {


	/*
	solution-a
	*/
	public static int solution(int[] A) {
		
		Arrays.sort(A);

		for (int i = 0; i < A.length; i++) {
			if (A[i] != i+1)
				return 0;
		}
		
		return 1;
	}



	/*
	solution -b 
	*/
	public int solution(int[] A) {    

        int N = A.length;

        int [] P = new int[N+1];
        
        for (int i=0;i<N+1;i++) {
           P[i] = -1;    
        }
    
        for (int i=0;i<N;i++) {
            if (N>=1) { 
                if ((A[i]<=N) && (A[i]>=1)){
                   if ( P[A[i]] == 1){
                    return 0;
                   }else {
                    P[A[i]] = 1; 
                   }
                }else {
                    return 0;    
                }
            }else {
                return 0;
            }           
        }
        
        for (int i=1;i<N+1;i++) {    
            if (P[i] == -1){
                return 0;    
            }else if (P[0]==1){
                return 0;
            }else {
                return 1;
            }            
        }

        return 0;
    }

    


	public static void main(String[] args) {

		int[] A = new int[]{5,3,4,1,2,2,6};
		System.out.println(solution(A));
	}
}
