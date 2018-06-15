/*
	A non-empty zero-indexed array A consisting of N integers is given. A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
	For example, the following array A:
	    A[0] = 1 
	    A[1] = 5 
	    A[2] = 3 
	    A[3] = 4 
	    A[4] = 3 
	    A[5] = 4 
	    A[6] = 1 
	    A[7] = 2 
	    A[8] = 3 
	    A[9] = 4 
	    A[10] = 6 
	    A[11] = 2
	has exactly four peaks: elements 1, 3, 5 and 10.
	You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum number of flags on the peaks, according to certain rules.
	
	Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.
	For example, given the mountain range represented by array A, above, with N = 12, if you take:
	two flags, you can set them on peaks 1 and 5;
	three flags, you can set them on peaks 1, 5 and 10;
	four flags, you can set only three flags, on peaks 1, 5 and 10.
	You can therefore set a maximum of three flags in this case.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.
	For example, the following array A:
	    A[0] = 1 
	    A[1] = 5 
	    A[2] = 3 
	    A[3] = 4 
	    A[4] = 3 
	    A[5] = 4 
	    A[6] = 1 
	    A[7] = 2 
	    A[8] = 3 
	    A[9] = 4 
	    A[10] = 6 
	    A[11] = 2
	the function should return 3, as explained above.
	Assume that:
	N is an integer within the range [1..200,000];
	each element of array A is an integer within the range [0..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */



// REQUIRE PRACTICE 


//Score: 100/14
package primeandcompositenumbers;

import java.util.ArrayList;

public class Flags {
	

	public static int solution(int[] A) {

		int N = A.length;

		// [1, 1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1]
		int[] next = nextPeak(A);

		int i = 1;
		int result = 0;


		// this is for reducing the number of iteration
        while ((i - 1) * i <= N) {

        // while (i * i <= N) {
        // while (i <= N) {

            int positionalIndex = 0;
            int numberOfFlags = 0;

            while (positionalIndex < N && numberOfFlags < i) {

                positionalIndex = next[positionalIndex];

                if (positionalIndex == -1) {
                    break;
                }

                numberOfFlags += 1;
                positionalIndex += i;
            }

            result = Math.max(result, numberOfFlags);
            i++;
        }

		return result;
	}


	/*
	* we have an array with peak values and -1 		
	*/
	public static int[] nextPeak(int[] A) {

		int N = A.length;

		ArrayList<Integer> peaks = new ArrayList<Integer>();
		
		// addition is deterministic and sequential 
		for (int i = 1; i < A.length-1; i++){			
			if (A[i] > A[i-1] && A[i] > A[i+1]){			
				peaks.add(i);
			}
		}
		
		int[] next = new int[N];
		next[N-1] = -1;

		for (int i = N-2; i >= 0; i--) {
			if(peaks.contains(i)){
				next[i] = i;
			} else {
				next[i] = next[i+1];
			}
		}

		return next;
	}
	

	/*
	* find all the peaks for the mountain
	*/
	// public static ArrayList<Integer> createPeaks(int[] A) {
		
	// 	ArrayList<Integer> peaks = new ArrayList<Integer>();
		
	// 	for (int i = 1; i < A.length-1; i++)
	// 		if (A[i] > A[i-1] && A[i] > A[i+1])
	// 			peaks.add(i);
	// 	return peaks;
	// }







	public int solution(int[] A) {
        ArrayList<Integer> array = new ArrayList<Integer>();  
		for (int i = 1; i < A.length - 1; i++) {  
			if (A[i - 1] < A[i] && A[i + 1] < A[i]) {  
				array.add(i);  
			}  
		}  
	    if (array.size() == 1 || array.size() == 0) {  
			return array.size();  
	    }  
        int sf = 1;  
        int ef = array.size();  
        int result = 1;  
        while (sf <= ef) {  
            int flag = (sf + ef) / 2;  
            boolean suc = false;  
            int used = 0;  
            int mark = array.get(0);  
            for (int i = 0; i < array.size(); i++) {  
                if (array.get(i) >= mark) {  
                    used++;  
                    mark = array.get(i) + flag;  
					if (used == flag) {                       
						suc = true;  
						break;  
					}  
                }  
            }  
            if (suc) {  
                result = flag;  
                sf = flag + 1;  
            }else {  
                ef = flag - 1;  
            }  
        }  
       return result;  
     }




     public int solution(int[] a) {
        if (a.length < 3) {
            return 0;
        }
        final int[] nexts = new int[a.length];
        int next = a.length;
        nexts[a.length - 1] = a.length;
        int peaks = 0;
        for (int i = a.length - 2; i >= 1; i--) {
            if (a[i - 1] < a[i] && a[i] > a[i + 1]) {
                next = i;
                peaks++;
            }
            nexts[i] = next;
        }
        nexts[0] = next;
        if (peaks == 0) {
            return 0;
        }
        int result = 1;
        final int start = nexts[0];
        int p = 1;
        int maxp = 1;
        while (maxp * maxp <= a.length) {
            maxp++;
        }
        // there might be a situation when the peaks are packed, for intstance 010001000100010. sqrt(n) = 3, but the answer is 4.
        if (maxp * maxp != a.length) {
            maxp++;
        }
        // but no more than total peaks
        if (peaks < maxp) {
            maxp = peaks;
        }
        // outer loop is O(sqrt(n)) and each inner loop is performed no more than p which is no more than O(sqrt(n))
        // it gives total O(n) complexity
        while (p <= maxp) {
            int setFlags = 0;
            for (int j = start; setFlags < p && j < a.length; j = (j + p < nexts.length ? nexts[j + p] : a.length)) {
                setFlags++;
            }
            if (result < setFlags) {
                result = setFlags;
            }
            p++;
        }
        return result;
    }

	public static void main(String[] args) {
		
		int[] N = new int[] {1,5,3,4,3,4,1,2,3,4,6,2};
		System.out.println(solution(N));
	}
}
