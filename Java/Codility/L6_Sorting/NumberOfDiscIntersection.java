/*
	Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. We say that the J-th disc and K-th disc intersect if J ≠ K and J-th and K-th discs have at least one common point.
	Write a function:
	int solution(int A[], int N);
	that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, given N=6 and:
	
		A[0] = 1  A[1] = 5  A[2] = 2 
		A[3] = 1  A[4] = 4  A[5] = 0  

	intersecting discs appear in eleven pairs of elements:

		0 and 1,
		0 and 2,
		0 and 4,
		1 and 2,
		1 and 3,
		1 and 4,
		1 and 5,
		2 and 3,
		2 and 4,
		3 and 4,
		4 and 5.

	so the function should return 11.
	The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
	Assume that:

	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [0..2147483647].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

public class NumberOfDiscIntersection {

    /*
        The J-th disc is drawn with its center at (J, 0) and radius A[J].
        We say that the J-th disc and K-th disc intersect if J ≠ K and the 
        J-th and K-th discs have at least one common point (assuming that 
        the discs contain their borders).

        Given an array A describing N discs as explained above, returns the 
        number of (unordered) pairs of intersecting discs. The function should 
        return −1 if the number of intersecting pairs exceeds 10,000,000.
    */

    // Intersection Premise  
    // --------------------    
    // i + A[i] > j - A[j]    
    /*
    * solution - a
    */
    /*
        ---------------------------------------------------------------
        Type        Size    in Bytes   Range
        -------------------------------------------------------------------
        byte        1 byte      -128 to 127
        
        short       2 bytes     -32,768 to 32,767
        
        int         4 bytes     -2,147,483,648 to 2,147,483, 647 (2 x 10ˆ9)
        
        long        8 bytes     -9,223,372,036,854,775,808 to (9 x 10ˆ18)
        9,223,372,036,854,775,807


        float       4 bytes     approximately ±3.40282347E+38F 
        (6-7 significant decimal digits) 
        Java implements IEEE 754 standard
        double      8 Bytes     approximately ±1.79769313486231570E+308
        (15 significant decimal digits)
        char        2 byte      0 to 65,536 (unsigned)
        boolean not precisely defined*  true or false
        -------------------------------------------------------------------
    */
	public static int solution(int[] A) {

		int x = 0;

		for (int i = 0; i < A.length - 1; i++) {

			for (int j = i+1; j < A.length; j++) {

				if ((long)A[i]+i >= j - (long)A[j]) {
					x++;
					if (x>10000000)
						return -1;
				}
			}
		}

		return x;
	}


	/*
	* solution -b
	*/
    /* 
    Improvedolution: Time complexity is O(N*log(N)) or O(N). The largest value 
    of right-A[right] is n-1. We just need to find right-A[right] > 0 and how 
    many i+A[i] is smaller than it.
    */
    // NEED to see 
    public int solution(int[] A) {

        int n = A.length;
        int[] sum = new int[n];
        
        // For intersection, max(j - A[j]) < N-1
        for (int i = 0; i < n; i++) {

            int right;
            
            // i + A[i] > j - A[j]
            // IF i+A[i]<= n-1, that's it, extract this i+A[i], let sum[i+A[i]]++, means there is one disk that i+A[i]
            if (n - i - 1 >= A[i]){
                right = i + A[i];
            } 

            else {
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



    /*
	* silution -c
    */
    public int solution(int[] a) {
        final long[] lefts = new long[a.length];
        final long[] rights = new long[a.length];
        for (int i = 0; i < a.length; i++) {
            lefts[i] = (long) i - (long) a[i];
            rights[i] = (long) i + (long) a[i];
        }
        Arrays.sort(lefts);
        Arrays.sort(rights);
        final int[] lm = new int[lefts.length];
        int v = 0;
        for (int i = lefts.length - 2; i >= 0; i--) {
            if (lefts[i] != lefts[i + 1]) {
                v = lefts.length - i - 1;
            }
            lm[i] = v;
        }
        v = 0;
        final int[] rl = new int[rights.length];
        for (int i = 1; i < rights.length; i++) {
            if (rights[i - 1] != rights[i]) {
                v = i;
            }
            rl[i] = v;
        }
        int c = 0;
        for (int i = 0; i < a.length; i++) {
            final long ar = (long) i + (long) a[i];
            int idx = Arrays.binarySearch(lefts, ar);
            int e;
            if (idx < 0) {
                idx = -1 - idx;
                e = a.length - idx;
            } else {
                e = lm[idx];
            }
            final long al = (long) i - (long) a[i];
            idx = Arrays.binarySearch(rights, al);
            if (idx < 0) {
                idx = -1 - idx;
                e += idx;
            } else {
                e += rl[idx];
            }
            c = c + (a.length - e - 1);
            if (c > 20000000) {
                return -1;
            }
        }
        return c / 2;
    }

	public static void main (String[] args) {
		
		int[] A = new int[] {1, 5, 2, 1, 4, 0};
		System.out.println(solution(A));
	}	
}


