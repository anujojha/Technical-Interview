/*
	A non-empty zero-indexed array A consisting of N integers is given.
	The leader of this array is the value that occurs in more than half of the elements of A.
	An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 3
	    A[2] = 4
	    A[3] = 4
	    A[4] = 4
	    A[5] = 2
	we can find two equi leaders:
	0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
	2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
	The goal is to count the number of equi leaders. Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
	For example, given:
	    A[0] = 4
	    A[1] = 3
	    A[2] = 4
	    A[3] = 4
	    A[4] = 4
	    A[5] = 2
	the function should return 2, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package leader;

import java.util.Stack;



// REQUIRES PRACTICE 

/*
	The leader of this array is the value that occurs in more than half of 
	the elements of A.

	An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences 
	A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders 
	of the same value.
*/


public class EquiLeader {


	/*
	* solution - a
	*/
	// The goal is to count the number of equi leaders
	public static int solution(int[] A) {

		//check if it is dominator at all
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < A.length; i++) {

			if (stack.isEmpty()) {
				stack.push(A[i]);
				continue;
			}

			if (stack.peek() == A[i])
				stack.push(A[i]);
			
			else
				stack.pop();
		}

		//there's no dominator
		if (stack.isEmpty()){
			return 0; 
		}
		
		int dominator = stack.peek();
		int domOccurances = 0;
		
		for (int i = 0; i < A.length; i++) {			
			if(A[i] == dominator){
				domOccurances++;
			}
		}

		//not dominator
		// so no leader == no equi leader
		if (domOccurances <= A.length/2){
			return 0;
		}

		// we have a dominator 
		int nonDomOccurances = A.length - domOccurances;
		stack.clear();

		int dom=0;
		int nonDom=0;
		int equiLeaders=0;
		

		/*
			An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences 
			A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders 
			of the same value.
		*/
		for (int i = 0; i < A.length; i++) {

			if (A[i] == dominator){
				dom++;
			}
			else {
				nonDom++;
			}

	/*
    A[0] = 4
    A[1] = 3
    A[2] = 4
    A[3] = 4
    A[4] = 4
    A[5] = 2
	
	A = [4, 3, 4, 4, 4, 2]
	we can find two equi leaders:

	0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
	2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
	*/


			// domOccurances = 4
			// nonDomOccurances = 2

			// Core Algorithm 
			// --------------

				// v.1
				// curr. dom > curr non dom AND rest dom > rest non dom
				// curr. dom > curr non dom ensures the dom value occures more in the curent sec
				// REST dom > REST non dom ensures the dom value occures more in the REST sec

				// Let the number of X in L first elements be C. So the number of other 
				// elements in L first elements is L-C. So we have C > L-C, which is the 
				// same as 2C > L, or C > L/2	

				// v.2 
				// Leader for current sec and rest section
			if (dom > nonDom && (domOccurances - dom) > (nonDomOccurances-nonDom)){
				equiLeaders++;	
			}
		}

		return equiLeaders;
	}

	

	/*
	* solution - b
	*/
	public int solution(int[] A) {

        if(A.length==1) {
            return 0;    
        }
        
        int value = A[0];
        int size=0;

        for(int i=0;i<A.length;i++) {
            if(size==0) {
                size++;    
                value = A[i];
            }else {
                if(A[i]==value) {
                    size++;    
                }else {
                    size--;
                }                
            }   
        }

        int candidate = -1;
        int count = 0;   

        if(size>0) {
           candidate = value;     
        }
        
        for(int i=0;i<A.length;i++) {
            if(A[i]==candidate) {
                count++;
            }    
        }

        if(count<=A.length/2) {  
            return 0;
        }
        
        int leader = candidate;
        int equiCount = 0;
        int leaderCount = 0;

        for(int i=0; i<A.length; i++) {

            if (A[i] == leader) {
                leaderCount++;    
            }
            
            if(leaderCount>(i+1)/2  && (count-leaderCount) > (A.length-i-1)/2) {
                equiCount++;    
            }
        }
        
        return equiCount;
    }



 //    // SAME AS solution - b
 //    /*
	// * solution - c
	// */
 //    public int solution(int[] a) {

 //        int v = 0;
 //        int s = 0;

 //        for (final int value : a) {
 //            if (s == 0) {
 //                v = value;
 //                s = 1;
 //            } else {
 //                if (v != value) {
 //                    s--;
 //                } else {
 //                    s++;
 //                }
 //            }
 //        }
 //        if (s == 0) {
 //            return 0;
 //        }

 //        int total = 0;
 //        for (final int value : a) {
 //            if (value == v) {
 //                total++;
 //            }
 //        }
 //        if (total < (a.length / 2 + 1)) {
 //            return 0;
 //        }
 //        int n = 0;
 //        int r = 0;
 //        for (int i = 0; i < a.length - 1; i++) {
 //            if (a[i] == v) {
 //                n++;
 //            }
 //            if ((n > (i + 1) / 2) && (total - n) > (a.length - i - 1) / 2) {
 //                r++;
 //            }
 //        }
 //        return r;
 //    }


	public static void main (String[] args) {

		int[] A = new int[]{4,3,4,4,4,2};
		System.out.println(solution(A));
		
	}
}
