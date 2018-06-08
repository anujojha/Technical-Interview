/*
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
	
	For example, given:
	  A[0] = 1    
	  A[1] = 3    
	  A[2] = 6
	  A[3] = 4    
	  A[4] = 1    
	  A[5] = 2
	the function should return 5.

	Assume that:

	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package countingelements;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MissingInteger {


	public static int solution(int[] A) {
		
		ArrayList<Integer> numbers = IntStream.of(A).boxed()
									.filter(x->x>0).sorted().distinct()
									.collect(Collectors.toCollection(ArrayList<Integer>::new));		
		if (numbers.size() == 0){
			return 1;
		}
					
		for (int i = 0; i < numbers.size(); i++) {

			if (numbers.get(i) != i+1){
				return i+1;
			}				
		}

		return numbers.size()+1;
	}


	public static void main(String[] args) {

		int[] A = new int[]{1,3,6,4,1,2};
		System.out.println(solution(A));
	}
}






##. 
def solution(A):
    ''' Solve it with Pigeonhole principle.
        There are N integers in the input. So for the
        first N+1 positive integers, at least one of
        them must be missing.
    '''

    # We only care about the first N+1 positive integers.
    # occurrence[i] is for the integer i+1

    occurrence = [False] * (len(A) + 1)

    for item in A:
        if 1 <= item <= len(A) + 1:
            occurrence[item-1] = True

    # Find out the missing minimal positive integer.
    for index in xrange(len(A) + 1):
        if occurrence[index] == False:
            return index + 1

    # raise Exception("Should never be here.")
    return -1