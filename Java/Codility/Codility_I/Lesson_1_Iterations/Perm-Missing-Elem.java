/**
 * A zero-indexed array A consisting of N different integers is given. 
 * The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * Your goal is to find that missing element.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A, returns the value of the missing element.
 * For example, given array A such that:
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * Assume that:
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * Complexity: 
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
 
/*1. This solution is not good. If the number is too large, int is overflow. Double or float is not accurate.*/
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int realSum = 0;
        int N = A.length;
        for (int i=0; i<N;i++){
            realSum = realSum + A[i];            
        }        
        int originalSum = (N+1)*(N+2)/2;
        return (originalSum - realSum);
    }
}

/*score : 80%*/

/*2. This idea is, if A[i]= n, then set A[n-1] = 0. The space j !=0, then return j+1 is missing*/
class Solution 
{
    public int solution(int[] A) 
    {
        for (int i = 0; i < A.length; i++)
        {
            if (A[i] == 0)
            {
                continue;   
            }
            
            int n = A[i] - 1;
            while (n != -1 && n < A.length)
            {
                int next = A[n] - 1;
                A[n] = 0;
                n = next;
            }
        }
        
        for (int i = 0; i < A.length; i++)
        {
            if (A[i] != 0)
            {
                return i + 1;   
            }
        }
        
        return A.length + 1;
    }
}

/*score : 100%*/