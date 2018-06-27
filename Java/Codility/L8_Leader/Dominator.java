/*
	A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.
	For example, consider array A such that
	A[0] = 3    A[1] = 4    A[2] =  3
	A[3] = 2    A[4] = 3    A[5] = -1
	A[6] = 3    A[7] = 3

	The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.

	Write a function
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	For example, given array A such that
	A[0] = 3    A[1] = 4    A[2] =  3
	A[3] = 2    A[4] = 3    A[5] = -1
	A[6] = 3    A[7] = 3
	the function may return 0, 2, 4, 6 or 7, as explained above.
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */



//SCORE: 100/100
package leader;

import java.util.Stack;



/*
The dominator of array A is the value that occurs in more 
than half of the elements of A.

Given an array A consisting of N integers, returns index 
of any element of array A in which the dominator of A occurs. 
The function should return −1 if array A does not have a 
dominator.
*/

public class Dominator {
	

	/*
		// algorithms 
		// ----------
		This ensures the element that occurs max in an array

		a. If the stack is empty, push inside
		b. If not empty and same element as previous,
		push again
		c. if not empty and not same elelemnt, pop the stack

		finally we will have the elelemt in the stack occurs max num.
	*/


	// The dominator of array A is the value that occurs 
	// in more than half of the elements of A.
	/*
	* solution -a 
	*/	
	public static int solution(int[] A) {

	
		Stack<Integer> stack = new Stack<Integer>();
		
		// the max times. Not the denominator 	
		for (int i = 0; i < A.length; i++) {
			
			if (stack.isEmpty()) {
				stack.push(A[i]);
				continue;
			} 			
			if (stack.peek() == A[i]){
				stack.push(A[i]);
			}
			else {
				stack.pop();
			}
		}

		if (stack.isEmpty()){
			return -1;
		}

		int domCandidate = stack.peek();
		int occurances = 0;
		int randomIndex=-1;

		for (int i = 0; i < A.length; i++) {

			if(A[i] == domCandidate) {
				occurances++;
				randomIndex = i;
			}
		}

		// int count = (int) IntStream.of(A).map(x -> x = result).count();
		// int index = Arrays.asList(A).indexOf(result);

		// index of the first occurance of "domCandidate"
		// int indexOfTwo = ArrayUtils.indexOf(arr, domCandidate);
		
		return occurances > A.length/2? randomIndex:-1;
	}



	/*
	* solution - b
	*/	
	public int solution(int[] A) {
    
        if(A.length == 0) {
            return -1;    
        }
        
        if(A.length==1) {
            return 0;    
        }
        
        int value = A[0];
        int size=0;

        // get the elemnt of max occurance and count 
        for(int i=0; i<A.length; i++) {

            if(size==0) {
                size++;    
                value = A[i];
            }

            else {
                if(A[i]==value) {
                    size++;    
                }else {
                    size--;
                }                
            }   
        }
        
        int candidate = -1;

        int count = 0;
        int index = -1;
        
        if(size>0) {
           candidate = value;     
        }
        
        for(int i=0;i<A.length;i++) {

            if(A[i]==candidate) {
                count++;
                index=i;
            }    
        }

        if(count>A.length/2) {  
            return index;
        }
        
        return -1;
    }

	public static void main (String[] args) {

		int[] A = new int[]{3, 4, 3, 2, 3, -1, 3, 3};
		System.out.println(solution(A));		
	}
}
