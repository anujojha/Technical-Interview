/**
 * Write a function:
 * int solution(int A[], int N);
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
 * For example, given:
 * A[0] = 1
 * A[1] = 3
 * A[2] = 6
 * A[3] = 4
 * A[4] = 1
 * A[5] = 2
 * the function should return 5.
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [-2,147,483,648..2,147,483,647].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
/*1. Time complexity is O(N)*/
class Solution {

    public int solution(int[] A) {
        boolean[] counter = new boolean[A.length + 1];
        for(int i = 0; i < counter.length; i++){ 
            counter[i] = false;
        }
        
        for(int a : A){
            if (a > 0 && a < counter.length){ 
                counter[a - 1] = true;    
            }
        }

        for(int j = 0; j < counter.length; j++){ 
            if(!counter[j]){
                return j + 1;
            }
        }
        
        return A.length + 1;
    }

}

/*Score: 100%*/