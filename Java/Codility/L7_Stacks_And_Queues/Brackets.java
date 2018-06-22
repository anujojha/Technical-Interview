/*
	A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
	S is empty;
	S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
	S has the form "VW" where V and W are properly nested strings.
	For example, the string "{[()()]}" is properly nested but "([)()]" is not.
	Write a function:
	class Solution { public int solution(String S); }
	that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
	For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
	Assume that:
	N is an integer within the range [0..200,000];
	string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 */

//SCORE:100/100
package stackandqueue;

import java.util.Stack;

public class Brackets {


	// For example, the string "{[()()]}" is properly nested but "([)()]" is not	
    /*
    * solution -a
    */
	public static int solution(String S) {

		Stack<Character> chars = new Stack<Character>();

		for (int i = 0; i < S.length(); i++) {

			if (chars.size() == 0){
				chars.push(S.charAt(i));
			}
			else{							
				if (isMatch(chars.peek(), S.charAt(i))){
					chars.pop();
				}else{
					chars.push(S.charAt(i));
				} 					
			}
		}		
		return chars.size()==0?1:0;
	}

	
	private static boolean isMatch(char a, char b) {
		
		switch(a) {

			case '{':{
				return b == '}';
			}
			case '(': {
				return b == ')';
			}
			case '[':{
				return b == ']';
			}
			
			default: 
				return false;
		}
	}



    /*
    * solution - b
    */
	public int solution(String S) {

        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < S.length(); i++) {

            switch (S.charAt(i)) {

                case '(':
                case '[':
                case '{':
                    stack.push(S.charAt(i));
                    break;

                case ')':
                    if (stack.isEmpty() || stack.peek() != '(') 
                        return 0;
                    else 
                        stack.pop();
                    break;
                case ']':
                    if (stack.isEmpty() || stack.peek() != '[') 
                        return 0;

                    else 
                        stack.pop();
                    break;
                case '}':
                    if (stack.isEmpty() || stack.peek() != '{') 
                        return 0;
                    else 
                        stack.pop();

                    break;
            }
        }
        return stack.isEmpty() ? 1 : 0;
    }



    /*
    * solution - c
    */
    public int solution(String s) {

        int a = 0;
        int b = 0;
        int c = 0;
        
        final char[] st = new char[s.length()];
        int sl = 0;
        
                
        for (char ch: s.toCharArray()) {

            switch (ch) {

                case '(': {
                    a++;
                    st[sl++] = ch;
                    break;
                }

                case '{': {
                    b++;
                    st[sl++] = ch;
                    break;
                }

                case '[': {
                    c++;
                    st[sl++] = ch;
                    break;
                }

                case ')': {
                    if (sl > 0 && st[sl - 1] == '(') {
                        a--;
                        sl--;
                    } else {
                        return 0;
                    }
                    break;
                }

                case '}': {
                    if (sl > 0 && st[sl - 1] == '{') {
                        b--;
                        sl--;
                    } else {
                        return 0;
                    }
                    break;
                }

                case ']': {
                    if (sl > 0 && st[sl - 1] == '[') {
                        c--;
                        sl--;
                    } else {
                        return 0;
                    }
                    break;
                }
            }
        }
        // END for loop 

        if (a == 0 && b == 0 && c == 0) {
            return 1;
        }
        return 0;
    }



	public static void main (String[] args) {

		String S= "{[()()]}";
		System.out.println(solution(S));
	}

}
