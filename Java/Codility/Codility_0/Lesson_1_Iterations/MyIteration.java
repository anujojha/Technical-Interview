import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.*;




public  class MyIteration {

	
	/*BinaryGap: Find longest sequence of zeros in binary representation of an integer.*/

	/* A binary gap within a positive integer N is any maximal sequence of consecutive zeros 
	that is surrounded by ones at both ends in the binary representation of N.

	For example, number 9 has binary representation 1001 and contains a binary gap of length 2. 
	The number 529 has binary representation 1000010001 and contains two binary gaps: one of 
	length 4 and one of length 3. The number 20 has binary representation 10100 and contains one 
	binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps.

	Write a function:

	class Solution { public int solution(int N); }
	that, given a positive integer N, returns the length of its longest binary gap. The function 
	should return 0 if N doesn't contain a binary gap.

	For example, given N = 1041 the function should return 5, because N has binary representation 
	10000010001 and so its longest binary gap is of length 5.

	Assume that:

	N is an integer within the range [1..2,147,483,647].
	Complexity:

	expected worst-case time complexity is O(log(N));
	expected worst-case space complexity is O(1).*/

	public static int solution(int n) {

		int len = Stream.of(Integer.toBinaryString(n)
            .replaceAll("0+$", "")
            .split("1+")).filter(a -> a != null).max((a,b) -> Integer.compare(a.length(), b.length())).map(String::
            length).orElse(0);

        return len;
	}





// ==============================
    // Hasso Plattner Institute (HPI)
    // ==============================


    // problem - l
    // -----------
    public static int countUneatenLeaves(int N, int[] A) {

        int total = 0;

        for (int i = 0; i < A.length; i++) {

            int multiplier = (int) Math.pow(-1, i);
            total += multiplier * combination(A, i + 1, N);
        }

        return N - total;
    }

    private static int calc(int[] combination, int[] elements, int num) {

        int eaten = 0;

        if (combination.length == 1) {
            eaten = (int) Math.floor(num / elements[combination[0]]);
        } 

        else {

            int lcm = lcm(elements[combination[0]], elements[combination[1]]);
            for (int i = 2; i < combination.length; i++) {
                lcm = lcm(lcm, elements[combination[i]]);
            }
            eaten = Math.abs((int) Math.floor(num / lcm));
        }
        return eaten;
    }

    private static int lcm(int a, int b) {
        return a * (b / findGCD(a, b));
    }

    private static int findGCD(int number1, int number2) {
        //base case
        if (number2 == 0) {
            return number1;
        }

        return findGCD(number2, number1 % number2);
    }

    public static int combination(int[] elements, int K, int num) {

        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4 
        int N = elements.length;

        // get the combination by index 
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>       0   |   1   |   2
        //  element ==>     A   |   B   |   C

        int r = 0;
        int index = 0;
        int total = 0;

        while (r >= 0) {

            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if (index <= (N + (r - K))) {
                combination[r] = index;

                // if we are at the last position print and increase the index
                if (r == K - 1) {

                    //do something with the combination e.g. add to list or print
                    total += calc(combination, elements, num);
                    index++;
                } else {
                    // select index for next position
                    index = combination[r] + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0) index = combination[r] + 1;
                else index = combination[0] + 1;
            }
        }
        return total;
    }
    /* END ofsolution - l*/
    

    

    // problem - ll
    // ------------

    static String mergeStrings(String a, String b) {

        boolean eql =  a.length() ==  b.length();
        boolean less =  a.length() < b.length();
        // boolean greater =  a.length() > b.length();

        int min = less ? a.length(): b.length();

        String rst = "";
        
        for (int i = 0; i < min; i++){            
            
            rst +=  "" + a.charAt(i) + b.charAt(i);
            // rst +=   new StringBuilder().append(a.charAt(i)).append(b.charAt(i)).toString();
            // rst += String.valueOf(a.charAt(i)) + String.valueOf(b.charAt(i));
        } 

        if(eql){
            return rst;
        }

        else if(less){
            return rst += b.substring(min);
        }

        return rst += a.substring(min);
    }
    /* END ofsolution -  ll*/
     




    // problem - lll
    // -------------

    /*
        Victor 
        Veronica 
        Ryan 
        Dave 
        Maria 
        Maria 
        Farah 
        Farah
        Ryan 
        Veronica 

        ================
        answer: Veronica
        ================ 
    */

    // String[] votes = { "Victor", "Veronica", "Ryan", "Dave", "Maria",
    //                      "Maria", "Farah", "Farah","Ryan",  "Veronica"};

    public static String electionWinner(String[] votes) {

        // TreeMap will put the values in the ascending order 
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        List<String> keys = new ArrayList<String>();

        int max =  Collections.max(map.values());

        for(Map.Entry<String, Integer> entry : map.entrySet()){

            if(entry.getValue() == max){
                keys.add(entry.getKey());
            }
        }

        // the list is already sorded in the ascending order due 
        // to the use of TreeMap
        
        String rst = keys.get(keys.size() - 1);
        return rst;
    }


    public static String electionWinner1(String[] votes) {

        // TreeMap will put the values in the ascending order 
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        int max =  Collections.max(map.values());

        Iterator it = map.entrySet().iterator();

        while(it.hasNext()) {

            Map.Entry entry = (Map.Entry)it.next();
            
            if(!(entry.getValue()).equals(max)){
                it.remove();
            }
        }

        // System.out.println(map.lastEntry());
        
        return map.lastKey();
    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
    }
    /* END ofsolution -  lll*/

    
    public static void mySetTest(){

        Set<Integer> set = new TreeSet<Integer>();

        set.add(3);
        set.add((int)3.0);
        set.add(2);
        set.add(2);

        set.add(new Integer(2));
        set.add(Integer.parseInt("2"));

        System.out.println(set);
        // [2, 3]
    }    
    /*END of solution*/



	public static void main(String[] args) {
		System.out.println("Hello");
	}
}