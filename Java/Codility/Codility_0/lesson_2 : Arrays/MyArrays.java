import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;


public class MyArrays {


    // CyclicRotation : Rotate an array to the right by a given number of steps

	// A zero-indexed array A consisting of N integers is given. Rotation of the array 
	// means that each element is shifted right by one index, and the last element of 
	// the array is also moved to the first place.

	// For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal 
	// is to rotate array A K times; that is, each element of A will be shifted to the right 
	// by K indexes.

	// Write a function:

	// class Solution { public int[] solution(int[] A, int K); }

	// that, given a zero-indexed array A consisting of N integers and an integer K, returns the 
	// array A rotated K times.

	// For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return 
	// [9, 7, 6, 3, 8].

	// Assume that:

	// N and K are integers within the range [0..100];
	// each element of array A is an integer within the range [−1,000..1,000].
	// In your solution, focus on correctness. The performance of your solution will not be the 
	// focus of the assessment.


	/*solution-a*/
	public static int swap(int itself , int dummy){
		return itself;
	}

	public static void reverse(int[] A, int start, int end){

		int i = start, j = end;

		while(i < j){

			A[i] = swap(A[j], A[j] = A[i]);
			i++;
			j--;
		}
	}

	public int[] solution(int[] A, int K) {

		if(A == null || K > A.length){
			return null;
		}

		reverse(A, 0, A.length -1);
		reverse(A, 0, K-1);
		reverse(A, K, A.length -1);

		return A;
	}
	/*ENd of solution-a*/





    /**
    *  K = 3
    *  A = [3, 8, 9, 7, 6] 
    *  A = [9, 7, 6, 3, 8]
    */

	/*End of solution-b*/
	public static int[] solution1(int[] arr, int n){

		Map<Integer, Integer> map = new HashMap<>();

		for(int j = 0; j < arr.length; j++){

			if(j+n > arr.length -1){
				map.put(j+n - arr.length, arr[j]);
				continue;
			}

			map.put(j+n, arr[j]);
		}

		for(Map.Entry<Integer, Integer> entry : map.entrySet()){
			arr[entry.getKey()] = entry.getValue();
		}

		return arr;
	}
	/*ENd of solution-b*/






    /*OddOccurrencesInArray: Find value that occurs in odd number of elements.*/
    
	/* A non-empty zero-indexed array A consisting of N integers is given. The array contains 
	an odd number of elements, and each element of the array can be paired with another element 
	that has the same value, except for one element that is left unpaired.

	For example, in array A such that:

	  A[0] = 9  A[1] = 3  A[2] = 9
	  A[3] = 3  A[4] = 9  A[5] = 7
	  A[6] = 9
	the elements at indexes 0 and 2 have value 9,
	the elements at indexes 1 and 3 have value 3,
	the elements at indexes 4 and 6 have value 9,
	the element at index 5 has value 7 and is unpaired.
	Write a function:

	class Solution { public int solution(int[] A); }

	that, given an array A consisting of N integers fulfilling the above conditions, returns 
	the value of the unpaired element.

	For example, given array A such that:

    // {9, 3, 9, 3, 9, 7, 9}

	  A[0] = 9  A[1] = 3  A[2] = 9
	  A[3] = 3  A[4] = 9  A[5] = 7
	  A[6] = 9
	the function should return 7, as explained in the example above.

	Assume that:

	N is an odd integer within the range [1..1,000,000];
	each element of array A is an integer within the range [1..1,000,000,000];
	all but one of the values in A occur an even number of times.
	Complexity:

	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting 
	the storage required for input arguments).
	Elements of input arrays can be modified.*/

	public int solution(int[] arr) {
        
        int result = arr[0];

        for (int j =1; j < arr.length; j++) {
            result ^= arr[j];
        }

        return result;
    }
	/*ENd of solution */













    // ZooPlus AG test
    /*question: Given an array of values, design and code 
    an algorithm that returns whether there are two duplicates 
    within k indices of each other? k indices and within plus 
    or minus l (value) of each other? Do all, even the latter, 
    in O(n) running time and O(k) space.*/
    /*END OF SOLUTION*/




    /*  Running solution...
    Compilation successful.

    Example test:    '00-44  48 5555 8361' 
    WRONG ANSWER  (got 004-448-555-583-6- 8-361 expected 004-448-555-583-61) 

    Example test:    '0 - 22 1985--324' 
    WRONG ANSWER  (got 022-198-532-5---324 expected 022-198-53-24) 

    Example test:    '555372654' */

    /*solution-a*/
    public static String formatPhoneNumber(String S) {
        
        if(S == null || S.isEmpty() || S.length() < 2){
            return null;
        }
        
        String temp = "";
        
        for(int j = 0; j < S.length(); j++){
        
            char value = S.charAt(j);
        
            if(Character.isDigit(value) 
                && Character.getNumericValue(value) >= 0 
                && Character.getNumericValue(value) <= 200){
                
                temp += String.valueOf(value);
            }    
        }
        
        if(temp.length() == 2){
            return temp;
        }            
        
        String result = "";
        int len = temp.length();

        boolean bol = false; 

        if(len%3 == 0)
            bol = true;
        
        for(int index = 0; index < len; index++){
        
            int rest = len - (index+1);

            if(rest % 2 == 0 && rest %3 != 0 && rest/3 == 1 && !bol){

                System.out.println(rest);

                result += String.valueOf(temp.charAt(index)); 
                        
                String restString = temp.substring(index+1); 

                result +=  "-"+restString.substring(0, 2)+ "-" + restString.substring(2);
                break;
            }            
            
            if( (index+1) % 3 == 1 && index != 0) {
                result += "-";
            }        
           
           result += String.valueOf(temp.charAt(index));            
        
        }        

        return result;
    }
    /*END of solution-a*/


    /*solution-b*/
    public static String formatPhoneNumber(String s) {

        if (s == null) {
            return null;
        }

        return s.replaceAll("\\D", "")                // Discard all non-digits.
                .replaceAll("(\\d{2})(?=\\d{2}$)" +   // Final group of 4 digits
                            "|" +                     // ... or ...
                            "(\\d{3})(?!$)",          // non-final group of 3 digits,
                            "$1$2-");                 // insert separator.
    }
    /*END of solution-b*/






    /*solution-c*/
    // "Hello123 erere3435 efere 45 world.".replaceAll("[^\\d+]", "")
    public static String formatPhoneNumber3(String input) {

        // Guard
        if (input == null) {
            return input;
        }

        // Strip junk
        StringBuilder phone = new StringBuilder();

        IntStream.range(0, input.length())
                .filter(i -> Character.isDigit(input.charAt(i)))
                .forEachOrdered(i -> phone.append(Character.getNumericValue(input.charAt(i))));

        if (phone.length() <= 3) {
            return phone.toString();
        }

        // insert dashes... in reverse.
        // special cases for last group
        // set up the dash insert point for the end-of-groups.
        int dash = (phone.length() / 3) * 3;
        
        switch (phone.length() % 3) {

            case 0:
                // nothing to do for an exact grouping.
                break;
            case 1:
                // insert the dash making  2-2 groups instead of 3-1
                phone.insert(dash - 1, '-');
                break;
            case 2:
                // end up with a 3-2 group.
                phone.insert(dash, '-');
                break;
        }

        // easy cases for first groups.
        while (dash > 3) {
            dash -= 3;
            phone.insert(dash, '-');
        }

        return phone.toString();
    }
    /*END of solution-c*/







    /* eDreams ODIGEO */
    /*================*/

    /*A positive integer N is given. The goal is to construct the shortest possible 
    sequence of 
    integers ending with N, using the following rules:
    the first element of the sequence is 1; more specifically: A[0] = 1,
    each of the following elements is generated by multiplying the previous element by 2 or 
    increasing it by 1; more precisely: A = A[i−1] * 2 or A = A[i−1] + 1, for i ≥ 1.

    For example, for N = 17, the shortest sequence is:

      A[0] = 1
      A[1] = 2
      A[2] = 4
      A[3] = 8
      A[4] = 16
      A[5] = 17

    Write a function:
    class Solution { public int solution(int N); }
    that, given a positive integer N, returns the length of the shortest possible 
    sequence of integers satisfying the above conditions and ending with N.
    For example, given N = 17, the function should return 6, as explained above.

    Assume that:.
    N is an integer within the range [1..2,147,483,647].
    Complexity:
    expected worst-case time complexity is O(log(N));
    expected worst-case space complexity is O(1).. 1po*/


    public static int shortestSequence(int n){

        List<Integer> list = new ArrayList<>();
        list.add(n);

        boolean flag = true; 

        while(flag){

            int temp = n%2;

            if (n == 1){
                break;
            }                

            if (temp == 0){
                n = n/2;
                list.add(0, n);
            }

            else if(temp == 1){
                n = n-1;
                list.add(0, n);
            }
        }

        // System.out.println(list.toString());
        return list.size();
    }
    /*END of solution*/



    /*An integer X and a non-empty zero-indexed array A consisting of N integers are given. 
    We are interested in which elements of A are equal to X and which are different from X. 
    The goal is to split array A into two parts, such that the number of elements equal to X 
    in the first part is the same as the number of elements different from X in the other part. 
    More formally, we are looking for an index K such that:

    0 ≤ K < N and
    the number of elements equal to X in A[0..K−1] is the same as the number of elements different 
    from X in A[K..N−1]. (For K = 0, A[0..K−1] does not contain any elements. For K = N, A[K..N-1] 
    does not contain any elements.)
    
    For example, given integer X = 5 and array A such that:

    A = [5, 5, 1, 7, 2, 3, 5]

    K equals 4, because:

    two of the elements of A[0..3] are equal to X, namely A[0] = A[1] = X, and
    two of the elements of A[4..6] are different from X, namely A[4] and A[5].
    Write a function:

    int solution(int X, int A[], int N);

    that, given an integer X and a non-empty zero-indexed array A consisting of N integers, 
    returns the value of index K satisfying the above conditions. It can be shown such index 
    K always exists and it is unique.

    For example, given integer X and array A as above, the function should return 4, as explained 
    above.

    Assume that:

    N is an integer within the range [1..100,000];
    X is an integer within the range [0..100,000];
    Each element of array A is an integer within the range [0..100,000].
    Complexity:

    Expected worst-case time complexity is O(N);
    Expected worst-case space complexity is O(1), beyond input storage (not counting the storage 
    required for input arguments).

    Elements of input arrays can be modified.
    Except of one edge case the solution is as simple as counting for a number of X within the A 
    and then calculating K as that number substracted from an array length... Just think 
    of it a bit:

    e.g. assume there're 4 occurences of X within a given A;
    divide an array by cutting off 4 element from the right;
    whatever remained is gonna be on the left;
    even if after such a cut some of X's was 'lost' on the right - not a big deal; logically 
    enough that after division every X found on the right side is gracefully leveled by a 
    non-X on the left side. [3,5,1,5,7,8,3,2,8] -> [3,5,1,5,7,8,3 | 2,8] Two of X on the left = two 
    of non-X on the right

    [3,5,1,5,7,8,5,5,8] -> [3,5,1,5,7 | 8,5,5,8] Are two of X 'trapped' on the right side? Not 
    problem at all! That only means that we have got for two less X on the left and for two less 
    non-X on the right, thus anyway 2 of X on the left = 2 of non-X on the right;

    As for an exception mentioned earlier, the described solution will fail when all the X values 
    are grouped in one rightmost sequence, e.g. [3,5,5,5,5] -> [3 | 5,5,5,5] is wrong because for 
    X=5 by cutting 4 elements from the right eventually we will get an index of 1 thus leaving 
    no elements equal to X on the left at all. But as you may remember according to the condition 
    it's the first (i.e. left) part of an array that must contain X in quantity equal to a quantity 
    of non-X's in the second part (i.e. right). And as for this very edge case we're gonna return 
    just length of an input array which is a sort of an acceptable answer due to this: 
    "for K = N, A[K..N-1] does not contain any elements".*/


    public int splitArray(int X, int[] A) {
        
        if(A == null || A.length == 0){
            return -1;
        }
        
        if(X < 0 || X > 100000) return -1;
        
        int N = A.length;
        
        if(N < 1 || N > 100000) return -1;

        int sum = 0, seg  = 0;

        for (int j = 0; j < N; j++){
            
            if(A[j]< 0 || A[j] > 100000)
                return -1;

            if(A[j] == X){
                sum++;
                seg++;
            }

            else
                seg = 0;
        }

        return (A[N-1]!=X || sum > seg) ? (N - sum) : N;
    }
    /*END of solution:*/
    /*eDreams ODIGEO*/
    /*================*/






	
	public static void main(String[] args) {

		System.out.println("Seattle");	
		int[] arr = {3, 8, 9, 7, 6};
	}
}