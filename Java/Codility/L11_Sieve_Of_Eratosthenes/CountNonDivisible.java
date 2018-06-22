/*
	You are given a non-empty zero-indexed array A consisting of N integers.
	For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
	For example, consider integer N = 5 and array A such that:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	For the following elements:
	A[0] = 3, the non-divisors are: 2, 6,
	A[1] = 1, the non-divisors are: 3, 2, 3, 6,
	A[2] = 2, the non-divisors are: 3, 3, 6,
	A[3] = 3, the non-divisors are: 2, 6,
	A[6] = 6, there aren't any non-divisors.
	Write a function:
	class Solution { public int[] solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
	The sequence should be returned as:
	a structure Results (in C), or
	a vector of integers (in C++), or
	a record Results (in Pascal), or
	an array of integers (in any other programming language).
	For example, given:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	the function should return [2, 4, 3, 2, 0], as explained above.
	Assume that:
	N is an integer within the range [1..50,000];
	each element of array A is an integer within the range [1..2 * N].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */



/*
	A[0] = 3, the non-divisors are: 2, 6,
	A[1] = 1, the non-divisors are: 3, 2, 3, 6,
	A[2] = 2, the non-divisors are: 3, 3, 6,
	A[3] = 3, the non-divisors are: 2, 6,
	A[4] = 6, there aren't any non-divisors.

	int[] ret = {2,4,3,2,0}
*/


//SCORE: 100/100
package sieveoferastothenes;

// REQUIRES PRACTICE 

import java.util.Arrays;


/*
    The D data structure/matrix is such that the 0th column and jth row counts the 
    number of times  j occurs in the array A. In other words D[A[j]][0] is the number 
    of times the value of A[j] is in the array.

    After the loops, the 1st column and kth row count the number of elements in the 
    array which divide A[k]. In other words D[A[k]][1] is the number of divisors of 
    A[k] in the array.

    At the end the result r[j] is just r[j] = (A.length) - D[A[j]][1]. Since we want 
    the number of elements which are NOT divisors.

    Why do the loops work?

    Well if A[i] % j == 0 then what we would want to do is count the number of times j 
    appears in A and then add that to D[A[i]][1]. That's why you have the line 
    D[A[i]][1] += D[j][0]; . Furthermore A[i]/j will also be a different factor 
    (except if A[i] = j).

    The math part comes in proving that the set { A, B | A * B = N & A < sqrt(N) } = 
    {the set of divisors of N}. In other words you have to prove that all divisors 
    are covered. 
*/
public class CountNonDivisible {


	/*
	* solution-a
	*/
	// int[] A = {3, 1, 2, 3, 6};
	public static int[] solution(int[] A) {

	    	    	    	    
	    int[][] D = new int[2*A.length + 1][2];
	    int[] res = new int[A.length];	    

    	  //      ----- 
    	  //       0 0 
          //       1 -1 
          //       1 -1 
          //       2 -1 
          //       0 0 
          //       0 0 
          //       1 -1 
          //       0 0 
          //       0 0 
          //       0 0 
          //       0 0
    	  //      -----

	    for (int i = 0; i < A.length; i++) {			
            D[A[i]][0]++;
			D[A[i]][1] = -1;
		}

        /*
            Proving that the set { A, B | A * B = N & A < sqrt(N) } 
            = {the set of divisors of N}. In other words you have to 
            prove that all divisors are covered
        */
	    for (int i = 0; i < A.length; i++){

            // we find the indexes where A has elements 
	    	if(D[A[i]][1] == -1){
	    		
	    		D[A[i]][1]=0;

                /*
                    Reduce the number of iteration to find 
                    divisors for the i-th index of A Array 
                */
	    		for (int j = 1; j*j <= A[i]; j++) {

                    // we find a divisor for the A[i]
					if(A[i] % j == 0) {
			
                        // D[j][0] contains the count of elem with same value as index j
                        // for example, D[3][0] = 2 which means we have 3 two times in 
                        // the array A

                        // We just set the D[A[i]][1] as zero 

                        // Finally, we get the count of elem with same value as index
						D[A[i]][1] += D[j][0];

                        // j is less than the square root of A[i]						
						if (A[i]/j != j){
							D[A[i]][1]+= D[A[i]/j][0];
						}
					}					
				}
			}
		}

	    for (int i = 0; i < A.length; i++) {

            // count of non-divisors index i 
            // = length - count of divisors for ith index A
			res[i] = A.length - D[A[i]][1]; 
		}

	    return res;
	} 	





    // How do I convert an array like {1,2,3,4} to {1, 2, 3, 4, 6, 8, 12, 24}
	/*
	* solution - b
	*/
	public static int[] solution(int[] A) {

        int[][] D = new int[A.length*2 + 1][2];

        for (int i = 0; i < A.length; i++) {
            D[A[i]][0]++;
            D[A[i]][1] = -1;
        }

        for (int i = 0; i < A.length; i++) {

            if (D[A[i]][1] == -1) {

                D[A[i]][1] = 0;

                for (int j = 1; j <= Math.sqrt(A[i]) ; j++) {

                    if (A[i] % j == 0 && A[i] / j != j) {
                        D[A[i]][1] += D[j][0];
                        D[A[i]][1] += D[A[i]/j][0];
                    } else if (A[i] % j == 0 && A[i] / j == j) {
                        D[A[i]][1] += D[j][0];
                    }
                }
            }
        }
        for (int i = 0; i < A.length; i++) {
            A[i] = A.length - D[A[i]][1];
        }
        return A;
    }
    // SOL-B





	/*
	* solution-c 
	*/
	public int[] solution(int[] A){

        Set<Integer> setA = asSet(A);
        List<Set<Integer>> divisors = computeDivisors(A.length * 2);

        int occurrences[] = computeOccurrences(A);
        int nonDivisors[] = new int[A.length];
        
        for (int i=0; i<A.length; i++){

            int value = A[i];
            Set<Integer> d = divisors.get(value);
            
            int totalOccurances = 0;
            
            for (Integer divisor : d){

                if (setA.contains(divisor)){

                    totalOccurances += occurrences[divisor];
                }
            }
            nonDivisors[i] = A.length-totalOccurances;
        }
        return nonDivisors;
    }


    /**
     * Returns a set containing all elements of the given array
     * 
     * Space: O(N)
     * Time: O(N)
     * 
     * @param A The input array
     * @return The set
     */
    private static Set<Integer> asSet(int A[])
    {
        Set<Integer> result = new HashSet<Integer>();
        
        for (int value : A){

            result.add(value);
        }
        return result;
    }


    /**
     * Computes a list that contains for each i in [0...maxValue+1] a set
     * with all divisors of i. This is basically an "Eratosthenes Sieve". 
     * But in addition to setting the entries of a list to 'false' 
     * (indicating that the respective numbers are non-prime), this 
     * methods inserts the divisors into the corresponding set.
     *  
     * Space: O(N) (?)
     * Time: O(N*logN) (?)
     * 
     * @param maxValue The maximum value
     * @return The list 
     */
    private static List<Set<Integer>> computeDivisors(int maxValue){

        List<Boolean> prime = new ArrayList<Boolean>();
        prime.addAll(Collections.nCopies(maxValue+1, Boolean.TRUE));
        
        List<Set<Integer>> divisors = new ArrayList<Set<Integer>>();
        
        for (int i = 0; i < maxValue + 1; i++){

            Set<Integer> d = new HashSet<Integer>();
            d.add(1);
            d.add(i);
            divisors.add(d);
        }

        for (int i = 2; i <= maxValue; i++){

            int next = i + i;
            while (next <= maxValue){

                divisors.get(next).addAll(divisors.get(i));
                prime.set(next, Boolean.FALSE);
                next += i;
            }
        }
        return divisors;
    }

    /**
     * Computes an array of length 2*A.length+1, where each entry i contains
     * the number of occurrences of value i in array A
     * 
     * Space: O(N)
     * Time: O(N)
     * 
     * @param A The input array
     * @return The occurrences array
     */
    private static int[] computeOccurrences(int A[]){

        int occurances[] = new int[A.length * 2 + 1];
        for (int i=0; i<A.length; i++){

            int value = A[i];
            occurances[value]++;
        }
        return occurances;
    }
    // SOL - c





    /*
    * solution- a
    */
    // public int[] solution(int[] A) {
        
    //     int[][] D = new int[A.length*2 + 1][2];
    
    //     for (int i = 0; i < A.length; i++) {
    //         D[A[i]][0]++;
    //         D[A[i]][1] = -1;
    //     }
    
    //     for (int i = 0; i < A.length; i++) {

    //         if (D[A[i]][1] == -1) {

    //             D[A[i]][1] = 0;

    //             for (int j = 1; j <= Math.sqrt(A[i]) ; j++) {

    //                 if (A[i] % j == 0 && A[i] / j != j) {
    //                     D[A[i]][1] += D[j][0];
    //                     D[A[i]][1] += D[A[i]/j][0];
    //                 } else if (A[i] % j == 0 && A[i] / j == j) {
    //                     D[A[i]][1] += D[j][0];
    //                 }
    //             }
    //         }
    //     }
    //     for (int i = 0; i < A.length; i++) {
    //         A[i] = A.length - D[A[i]][1];
    //     }
    //     return A;
    // }



    /*
    * solution- a
    */
    public int[] solution(int[] a) {

        final int[] s = new int[a.length];
        
        System.arraycopy(a, 0, s, 0, a.length);
        Arrays.sort(s); // O(n * log(n))
        
        // max value is <= 2 * N
        final int[] n = new int[a.length * 2];
        int c = 0;


        // loops give O(n * log(n)) because the total sum of steps is no more than
        // n/1 + n/2 + n/3 + ... + n/n which is O(n * log(n))
        for (int i = 0; i < s.length; i++) {
            c += 1;
            if (i + 1 < s.length && s[i] == s[i + 1]) {
                continue;
            }
            for (int j = s[i] - 1; j < n.length; j += s[i]) {
                n[j] += c;
            }
            c = 0;
        }
        for (int i = 0; i < a.length; i++) {
            s[i] = a.length - n[a[i] - 1];
        }
        return s;
    }



    // UTILITY //
    //////////////////////////////////////////////////////////////////////////////////////////
    /**
     * print an 2D array like table
     *
     * @param grid
     */
    public static void printArrayLikeTable(int[][] grid) {

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c] + " ");
            }

            System.out.println();
        }
    }
    /*
    * END of solution-b
    */

	public static void main (String[] args) {
		int[] A = new int[] {3,1,2,3,6};
		System.out.println(Arrays.toString(solution(A)));
	}
}
