/**
 * Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. 
 * We say that the J-th disc and K-th disc intersect if J != K and J-th and K-th discs have at least one common point.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, given N=6 and:
 * A[0] = 1  A[1] = 5  A[2] = 2
 * A[3] = 1  A[4] = 4  A[5] = 0
 * intersecting discs appear in eleven pairs of elements:
 * 0 and 1,
 * 0 and 2,
 * 0 and 4,
 * 1 and 2,
 * 1 and 3,
 * 1 and 4,
 * 1 and 5,
 * 2 and 3,
 * 2 and 4,
 * 3 and 4,
 * 4 and 5.
 * so the function should return 11.
 * The function should return -1 if the number of intersecting pairs exceeds 10,000,000.
 * Assume that:
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2,147,483,647].
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */

 /*1. Simple solution, treat it as a math problem. The only situation that two disks don't intersect is : right-A[right]>i+A[i]
    However, n(n-1)/2 may cause arithmetic overflow of integer and the O(n*n)performance is bad. Use long instead.*/
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int n = A.length;
        int sum = 0;
        int interect = 0;
		long total = (long) n * (n - 1) / 2;
        for (int i=0; i<n; i++) {
            for (int right = i+1; right<n;right++) {                
                if (right- A[i]- i > A[right]) { //right>A[i]+i+A[right] will cause overflow
                    sum++;
                }    
            }    
        }
		
		if ((long)(total - sum) > 10000000) {
			return -1;    
		}
        return (int)(total - sum);
        
    }
}
/*Score: 81%*/

/*2. Improvedolution. Time complexity is O(N*log(N)) or O(N).
The largest value of right-A[right] is n-1. We just need to find right-A[right]>0 and how many i+A[i] is smaller than it.*/
class Solution {
    public int solution(int[] A) {
        int n = A.length;
        int[] sum = new int[n];
        
        for (int i = 0; i < n; i++) {
            int right;
            //if i+A[i]<= n-1, that's it, extract this i+A[i], let sum[i+A[i]]++, means there is one disk that i+A[i]
            if (n - i - 1 >= A[i]){
                right = i + A[i];
            } else {
                right = n - 1;    
            }
            
            sum[right]++;
        }
        
        for (int i = 1; i < n; i++) {
            sum[i] += sum[i - 1];  //sum[i] means that there are sum[i] number of values that <= i;
        }
        
        long ans = (long) n * (n - 1) / 2;
        
        for (int i = 0; i < n; i++) {
            int left;
            
            if (A[i] > i) {
                left = 0;
            } else {
                left = i - A[i];// Find the positive i-A[i].     
            }
            
            if (left > 0){
                ans -= sum[left - 1];//Find the number that is smaller than 1-A[i], sum[n-1] will never be used as we only need sum[n-1-1] at most.  
            } 
        }
        
        if (ans > 10000000) {
            return -1;    
        }
        
        return (int) ans;
    }
}
/*Score: 100%*/