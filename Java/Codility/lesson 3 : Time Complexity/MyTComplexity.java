import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;


public class MyTComplexity {
	

	// [FrogJmp] - Count minimal number of jumps from position X to Y.


	/* A small frog wants to get to the other side of the road. The frog is currently located 
	at position X and wants to get to a position greater than or equal to Y. The small frog 
	always jumps a fixed distance, D.
	Count the minimal number of jumps that the small frog must perform to reach its target.

	Write a function:

	class Solution { public int solution(int X, int Y, int D); }
	that, given three integers X, Y and D, returns the minimal number of jumps from position X 
	to a position equal to or greater than Y.

	For example, given:

	  X = 10
	  Y = 85
	  D = 30
	the function should return 3, because the frog will be positioned as follows:

	after the first jump, at position 10 + 30 = 40
	after the second jump, at position 10 + 30 + 30 = 70
	after the third jump, at position 10 + 30 + 30 + 30 = 100
	Assume that:

	X, Y and D are integers within the range [1..1,000,000,000];
	X ≤ Y.
	Complexity:

	expected worst-case time complexity is O(1);
	expected worst-case space complexity is O(1) */


	/*solution-a*/
	public int solution(int X, int Y, int D) {

		int count =0;        
		while(true){

			X += D;
			count++;

			if(X > Y)
				break;
		}
		return count;
	}
	/*END of solution-a*/



	/*solution-b*/
	public int solution(int X, int Y, int D) {
        // write your code in Java SE 8
        // the frog can jump from position X to a position equal to Y
		if ((Y-X) % D == 0)
			return (Y-X) / D;
        // the frog can only jump from position X to a position greater than Y
		else
			return (Y-X) / D + 1;
	}
	/*END of solution-b*/




	/*solution-c*/
	public int solution(int X, int Y, int D) {

		return (int)Math.ceil((Y - X) / (double)D);
	}
	/*END of solution-c*/








	//  [PermMissingElem] - Find the missing element in a given permutation.

	/* A zero-indexed array A consisting of N different integers is given. The array contains 
	integers in the range [1..(N + 1)], which means that exactly one element is missing.

	Your goal is to find that missing element.

	Write a function:

	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A, returns the value of the missing element.

	For example, given array A such that:

	  A[0] = 2
	  A[1] = 3
	  A[2] = 1
	  A[3] = 5

	the function should return 4, as it is the missing element.

	Assume that:

	N is an integer within the range [0..100,000];
	the elements of A are all distinct;
	each element of array A is an integer within the range [1..(N + 1)].
	Complexity:

	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting 
	the storage 
	required for input arguments).
	Elements of input arrays can be modified.*/


	/*solution-a*/
	 public static int solution(int[] arr){


        if(arr == null || arr.length == 0)
            return -1;

	 // 	If you have a List<Integer>
		// int sum = list.stream().mapToInt(Integer::intValue).sum();

        int n = arr.length + 1;
        return  n*(n+1)/2 - IntStream.of(arr).sum();
    }



    /*solution-b*/
    public int solution(int[] A) {

        // write your code in Java SE 8
        // this is the key declaration.if you use int instead of long, you'll
        // not pass the performance test.
        long N = A.length + 1;
        // calculate the sum of 1 + 2 + ... + (N + 1)
        long result = (N * (N + 1)) / 2;
        // get the result by subtracting all the array element from 1 + 2 +
        // ... (N + 1)
        for (int element : A)
            result -= element;
        return (int)result;
    }






    /* A non-empty zero-indexed array A consisting of N integers is given. Array A 
	represents numbers on a tape.

	Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: 
	A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

	The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − 
	(A[P] + A[P + 1] + ... + A[N − 1])|

	In other words, it is the absolute difference between the sum of the first part and the 
	sum of the second part.

	For example, consider array A such that:

	  A[0] = 3
	  A[1] = 1
	  A[2] = 2
	  A[3] = 4
	  A[4] = 3
	We can split this tape in four places:

	P = 1, difference = |3 − 10| = 7 
	P = 2, difference = |4 − 9| = 5 
	P = 3, difference = |6 − 7| = 1 
	P = 4, difference = |10 − 3| = 7 
	Write a function:

	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the 
	minimal difference that can be achieved.

	For example, given:

	  A[0] = 3
	  A[1] = 1
	  A[2] = 2
	  A[3] = 4
	  A[4] = 3
	the function should return 1, as explained above.

	Assume that:

	N is an integer within the range [2..100,000];
	each element of array A is an integer within the range [−1,000..1,000].
	Complexity:

	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting
	the storage 
	required for input arguments).
	Elements of input arrays can be modified.*/



	/*solution-a*/
    public int solution(int[] A) {

        int len = A.length;
        int[] sum = new int[len];
        // get the sum from 0 to n and store it in the sum[n]
        sum[0] = A[0];
        for (int i = 1; i < len; i++) {
                    sum[i] = sum[i-1] + A[i];
        }
        int min = Math.abs(sum[len-1] -2*sum[0]);
        for (int i = 2; i < len; i++) {
            int temp = Math.abs(sum[len-1] -2*sum[i-1]);
            if (temp < min)
                min = temp;
        }
        
        return min;
    }



    /*solution-b*/
    public static int solution(int[] A) {

        if(A.length == 0) return -1;
        int min = 0, temp = 0;

        for (int i = 1; i < A.length; i++){

            int m = 0, n = A.length-1;
            int left = 0 , right = 0;

            while(true){

                if(m < i)
                    left += A[m];

                if(n >= i)
                    right += A[n];

                m++; 
                n--;

                if(n == i && (m+1) == i ){

                    temp = Math.abs(left - right);

                    System.out.println(temp);
                    break;
                }
            }
        }
        
        return min;
    }
    /*END of soution-b*/



	public static void main(String[] args) {

		System.out.println("Time Complexity");
	}
}