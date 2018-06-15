/*
	You are given two non-empty zero-indexed arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
	Next, you are given a non-empty zero-indexed array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
	We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
	The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
	For example, given arrays A, B such that:
	    A[0] = 1    B[0] = 4
	    A[1] = 4    B[1] = 5
	    A[2] = 5    B[2] = 9
	    A[3] = 8    B[3] = 10
	four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
	Given array C such that:
	    C[0] = 4
	    C[1] = 6
	    C[2] = 7
	    C[3] = 10
	    C[4] = 2
	if we use the following nails:
	0, then planks [1, 4] and [4, 5] will both be nailed.
	0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
	0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
	0, 1, 2, 3, then all the planks will be nailed.
	Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
	Write a function:
	class Solution { public int solution(int[] A, int[] B, int[] C); }
	that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
	If it is not possible to nail all the planks, the function should return −1.
	For example, given arrays A, B, C such that:
	    A[0] = 1    B[0] = 4
	    A[1] = 4    B[1] = 5
	    A[2] = 5    B[2] = 9
	    A[3] = 8    B[3] = 10
	    
	    C[0] = 4
	    C[1] = 6
	    C[2] = 7
	    C[3] = 10
	    C[4] = 2
	the function should return 4, as explained above.
	Assume that:
	N and M are integers within the range [1..30,000];
	each element of arrays A, B, C is an integer within the range [1..2*M];
	A[K] ≤ B[K].
	Complexity:
	expected worst-case time complexity is O((N+M)*log(M));
	expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
	package binarysearch;

	import java.util.Arrays;


/*
	We say that a plank (A[K], B[K]) is nailed if there 
	exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].

	The goal is to find the minimum number of nails that 
	must be used until all the planks are nailed. In other 
	words, you should find a value J such that all planks 
	will be nailed after using only the first J nails.
*/

public class NailingPlanks {


	public static int solution(int[] A, int[] B, int[] C) {

		// A and B are also in the ascending sorted order
		// Otherwise, do the soring please 

		int N = A.length;
		int M = C.length;

		int[][] sortedNails = new int[M][2];

		for (int i = 0; i < M; i++) {
			sortedNails[i][0] = C[i];
			sortedNails[i][1] = i;
		}

		// sort based on the size of the nail in ascending order
		Arrays.sort(sortedNails, (int[] x, int[] y) -> (Integer.compare(x[0], y[0])));

		int res = 0;

		for (int i = 0; i < N; i++) {

			// insert the plank sizes, nails and the approx. result in the 

			// Thus, four is the minimum number of nails that, used sequentially, 
			// allow all the planks to be nailed.
			res = minIndex(A[i], B[i], sortedNails, res);
			
			if (res == -1){
				return -1;
			}
		}

		return res+1;
	}


	/*
	* get the minimum index for the nail 
	*/
	public static int minIndex(int pStart, int pEnd, int[][] nails, int oldRes) {

		int beg = 0;
		int end = nails.length-1;

		int res=-1;

		// find the index of the best nail for given plank 
		while(beg<=end) {

			int middle= (beg + end) / 2;

			if (nails[middle][0] < pStart) {
				beg = middle+1;
			}

			else if(nails[middle][0] > pEnd) {
				end = middle -1;
			}

			else {
				end = middle-1;
				res = middle;
			}
		}

		if (res == -1 || nails[res][0] > pEnd){
			return -1;
		}

		int min= nails[res][1];

		while(res < nails.length && nails[res][0] <= pEnd) {			

			// we get the index for the min. nail
			min = Math.min(min, nails[res][1]);

			if(min <= oldRes){
				return oldRes;
			}

			res++;
		}

		return min;
	}






	public int solution(int[] A, int[] B, int[] C) {
    
        // the main algorithm is that getting the minimal index of nails which
        // is needed to nail every plank by using the binary search
        int N = A.length;
        int M = C.length;
        // two dimension array to save the original index of array C
        int[][] sortedNail = new int[M][2];
        for (int i = 0; i < M; i++) {
            sortedNail[i][0] = C[i];
            sortedNail[i][1] = i;
        }
        // use the lambda expression to sort two dimension array
        Arrays.sort(sortedNail, (int x[], int y[]) -> x[0] - y[0]);
        int result = 0;
        for (int i = 0; i < N; i++) {//find the earlist position that can nail each plank, and the max value for all planks is the result
            result = getMinIndex(A[i], B[i], sortedNail, result);
            if (result == -1)
                return -1;
        }
        return result + 1;
    }
    // for each plank , we can use binary search to get the minimal index of the
    // sorted array of nails, and we should check the candidate nails to get the
    // minimal index of the original array of nails.
    public int getMinIndex(int startPlank, int endPlank, int[][] nail, int preIndex) {
        int min = 0;
        int max = nail.length - 1;
        int minIndex = -1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (nail[mid][0] < startPlank)
                min = mid + 1;
            else if (nail[mid][0] > endPlank)
                max = mid - 1;
            else {
                max = mid - 1;
                minIndex = mid;
            }
        }
        if (minIndex == -1)
            return -1;
        int minIndexOrigin = nail[minIndex][1];
        //find the smallest original position of nail that can nail the plank
        for (int i = minIndex; i < nail.length; i++) {
            if (nail[i][0] > endPlank)
                break;
            minIndexOrigin = Math.min(minIndexOrigin, nail[i][1]);
            // we need the maximal index of nails to nail every plank, so the
            // smaller index can be omitted
            if (minIndexOrigin <= preIndex)
                return preIndex;
        }
        return minIndexOrigin;
    }





    public int solution(int[] a, int[] b, int[] c) {
        final int[] nails = new int[2 * c.length + 1];
        int begin = 0;
        int end = c.length;
        int result = -1;
        // binary search. O(log(M)) times
        while(begin <= end) {
            int middle = (begin + end) / 2;
            // prepare helper array. O(M)
            for (int i = 0; i < nails.length; i++) {
                nails[i] = 0;
            }
            // set existing nails to 1. O(M)
            for (int i = 0; i < middle; i++) {
                nails[c[i]] = 1;
            }
            // count nails from 0 to i. O(M)
            int counter = 0;
            for (int i = 0; i < nails.length; i++) {
                if (nails[i] == 1) {
                    counter++;
                }
                nails[i] = counter;
            }
            // find nails number between a[i] and b[i]
            boolean found = true;
            for (int i = 0; i < a.length; i++) {
                if (nails[b[i]] - nails[a[i] - 1] == 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                end = middle - 1;
                result = middle;
            } else {
                begin = middle + 1;
            }
        }
        return result;
    }

	
	public static void main(String[] args) {

		int[] A = new int[]{1,4,5,8};
		int[] B = new int[]{4,5,9,10};
		int[] C = new int[]{4,6,7,10,2};

		System.out.println(solution(A, B, C));
	} 	
}
