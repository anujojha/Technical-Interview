1.

<div id="standard_task_description" class="protected" style="height: 100%; overflow: auto;"><div class="task-description-content">

<meta http-equiv="content-type" content="text/html; charset=utf-8">


<div id="brinza-task-description">
<p>In this problem we consider binary trees. The figure below shows an example binary tree consisting of seven nodes.</p>
<p><img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/tree_most_distinct_path/static/images/auto/4a4fad897b080c028e2c27a47ee835b3.png"></p>
<p>A <i>binary tree</i> is either an empty tree or a node (called the <i>root</i>) containing a single integer value and linked to two further binary trees. We are interested in paths (sequences of linked adjacent nodes) that start at the root and follow the tree edges (marked as arrows in the figure above). For example, the sequence of nodes A, B, D is a valid path, but the sequence A, B, G is not.</p>
<p></p><h2>Problem</h2>
<p></p>
<p>We would like to find the maximum number of distinct values that appear on a path starting at the root of the tree. For example, on the path consisting of nodes A, B, D, G there are two distinct values (4 and 5). On the path A, C, E there are three distinct values (1, 4 and 6). There is no path that contains four or more distinct values.</p>
<p><img class="inline-description-image" src="https://codility-frontend-prod.s3.amazonaws.com/media/task_static/tree_most_distinct_path/static/images/auto/c558ed02fdd113d8ad580b18ada5bded.png"></p>
<p>Write a function:</p>
<blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int solution(Tree T); }</tt></p></blockquote>
<p>that, given a binary tree T consisting of N nodes, returns the maximum number of distinct values that appear on a path starting at the root of tree T. For example, given the tree shown above, the function should return 3.</p>
<p></p><h2>Technical details</h2>
<p></p>
<p>A binary tree is given using a pointer data structure. Assume that the following declarations are given:</p>
<blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Tree {
  public int x;
  public Tree l;
  public Tree r;
}</tt></p></blockquote>
<p>An empty tree is represented by an empty pointer (denoted by <tt style="white-space:pre-wrap">null</tt>). A non-empty tree is represented by a pointer to an object representing its root. The attribute <tt style="white-space:pre-wrap">x</tt> holds the integer contained in the root, whereas attributes <tt style="white-space:pre-wrap">l</tt> and <tt style="white-space:pre-wrap">r</tt> hold the left and right subtrees of the binary tree, respectively.</p>
<p></p><h2>Assumptions</h2>
<p></p>
<p>Assume that:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>N is an integer within the range [<span class="number">1</span>..<span class="number">50,000</span>];</li>
<li>the height of tree T (number of edges on the longest path from root to leaf) is within the range [<span class="number">0</span>..<span class="number">3,500</span>];</li>
<li>each value in tree T is an integer within the range [<span class="number">1</span>..<span class="number">N</span>].</li>
</ul>
</blockquote><p>Complexity:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>expected worst-case time complexity is O(N);</li>
<li>expected worst-case space complexity is O(N).</li>
</ul>
</blockquote></div>
<div style="margin-top:5px">
<small>Copyright 2009–2018 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.</small>
</div>

</div></div>


package com.codility;


import java.util.*;

public class App {


    private static class Tree {

        public int key;

        public Tree left;
        public Tree right;

        boolean visited;

        Tree(int v) {
            this.key = v;
            this.visited = false;
        }
    }

    private static ArrayList<ArrayList<Tree>> getAllPaths(Tree root) {

        Stack<Tree> st = new Stack<>();
        ArrayList<ArrayList<Tree>> result = new ArrayList<>();

        st.push(root);
        root.visited = true;

        while (!st.isEmpty()) {

            Tree top = st.peek();

            if (top.left != null && !top.left.visited) {
                st.push(top.left);
                top.left.visited = true;
            } else {
                if (top.right == null && top.left == null) {


                    ArrayList<Tree> tmpList = new ArrayList<>();
                    for (Tree t : st) {
                        tmpList.add(t);
                    }
                    result.add(tmpList);
                    st.pop();
                } else if (top.right != null && !top.right.visited) {
                    st.push(top.right);
                    top.right.visited = true;
                } else {
                    st.pop();
                }
            }
        }

        return result;
    }


    public static int solution(Tree root) {

        ArrayList<ArrayList<Tree>> paths = getAllPaths(root);

        int result = 0;

        List<Integer> list = null;

        for (ArrayList<Tree> path : paths) {

            list = new ArrayList<>();

            for (Tree p : path) {

                if (list.contains(p.key)) {
                    continue;
                }

                list.add(p.key);
            }

            result = Math.max(result, list.size());
        }


        return result;
    }

    public static void main(String[] args) {

        Tree root = new Tree(4);

        root.left = new Tree(5);
        root.left.left = new Tree(4);
        root.left.left.left = new Tree(5);

        root.right = new Tree(6);
        root.right.left = new Tree(1);

        root.right.right = new Tree(6);

        System.out.println(solution(root));

    }
}

==========================================================================================


2. 

<div class="FancyBox__FloatingFancyBox-hy7ocn-2 hGyGOC FancyBox__Wrapper-hy7ocn-1 iHQzMo"><div class="FancyBox__ChildWrapper-hy7ocn-0 kbVwhn" style="height: 100%; position: relative;"><div style="height: 100%; overflow: auto;" id="standard_task_description" class="protected"><div class="task-description-content">

<meta http-equiv="content-type" content="text/html; charset=utf-8">


<div id="brinza-task-description">
<p>A <i>prefix</i> of a string S is any leading contiguous part of S. For example, the string "<tt style="white-space:pre-wrap">codility</tt>" has the following prefixes: "<tt style="white-space:pre-wrap"></tt>", "<tt style="white-space:pre-wrap">c</tt>", "<tt style="white-space:pre-wrap">co</tt>", "<tt style="white-space:pre-wrap">cod</tt>", "<tt style="white-space:pre-wrap">codi</tt>", "<tt style="white-space:pre-wrap">codil</tt>", "<tt style="white-space:pre-wrap">codili</tt>", "<tt style="white-space:pre-wrap">codilit</tt>" and "<tt style="white-space:pre-wrap">codility</tt>". A prefix of S is called <i>proper</i> if it is shorter than S.</p>
<p>A <i>suffix</i> of a string S is any trailing contigous part of S. For example, the string "<tt style="white-space:pre-wrap">codility</tt>" has the following suffixes: "<tt style="white-space:pre-wrap"></tt>", "<tt style="white-space:pre-wrap">y</tt>", "<tt style="white-space:pre-wrap">ty</tt>", "<tt style="white-space:pre-wrap">ity</tt>", "<tt style="white-space:pre-wrap">lity</tt>", "<tt style="white-space:pre-wrap">ility</tt>", "<tt style="white-space:pre-wrap">dility</tt>", "<tt style="white-space:pre-wrap">odility</tt>" and "<tt style="white-space:pre-wrap">codility</tt>". A suffix of S is called <i>proper</i> if it is shorter than S.</p>
<p>Write a function:</p>
<blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int solution(String S); }</tt></p></blockquote>
<p>that, given a string S consisting of N characters, returns the length of the longest string that is both a proper prefix of S and a proper suffix of S.</p>
<p>For example, given S = "<tt style="white-space:pre-wrap">abbabba</tt>" the function should return 4, because:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>proper prefixes of S are: "<tt style="white-space:pre-wrap"></tt>", "<tt style="white-space:pre-wrap">a</tt>", "<tt style="white-space:pre-wrap">ab</tt>", "<tt style="white-space:pre-wrap">abb</tt>", "<tt style="white-space:pre-wrap">abba</tt>", "<tt style="white-space:pre-wrap">abbab</tt>", "<tt style="white-space:pre-wrap">abbabb</tt>";</li>
<li>proper suffixes of S are: "<tt style="white-space:pre-wrap"></tt>", "<tt style="white-space:pre-wrap">a</tt>", "<tt style="white-space:pre-wrap">ba</tt>", "<tt style="white-space:pre-wrap">bba</tt>", "<tt style="white-space:pre-wrap">abba</tt>", "<tt style="white-space:pre-wrap">babba</tt>", "<tt style="white-space:pre-wrap">bbabba</tt>";</li>
<li>string "<tt style="white-space:pre-wrap">abba</tt>" is both a proper prefix and a proper suffix of S;</li>
<li>this is the longest such string.</li>
</ul>
</blockquote><p>For example, given S = "<tt style="white-space:pre-wrap">codility</tt>" the function should return 0, because:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>string "<tt style="white-space:pre-wrap"></tt>" is both a proper prefix and a proper suffix of S;</li>
<li>this is the longest such string.</li>
</ul>
</blockquote><p>Complexity:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>expected worst-case time complexity is O(N);</li>
<li>expected worst-case space complexity is O(N) (not counting the storage required for input arguments).</li>
</ul>
</blockquote><p>Assume that:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>the length of S is within the range [<span class="number">1</span>..<span class="number">1,000,000</span>];</li>
<li>string S consists only of lowercase letters (<tt style="white-space:pre-wrap">a</tt>−<tt style="white-space:pre-wrap">z</tt>).</li>
</ul>
</blockquote></div>
<div style="margin-top:5px">
<small>Copyright 2009–2018 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.</small>
</div>

</div></div></div></div>




    public static int solution(String S) {

        List<String> prefixes = new ArrayList<>();
        List<String> suffixes = new ArrayList<>();

        int N = S.length();

        for (int i = 0; i < N - 1; i++) {

            String prefix = S.substring(0, i + 1);
            prefixes.add(prefix);
        }

        for (int i = N - 1; i >= 1; i--) {

            String suffix = S.substring(i, N);
            suffixes.add(suffix);
        }

        int M = prefixes.size();
        int result = 0;

        for (int i = M-1; i >= 0; i--) {

            if (prefixes.get(i).equals(suffixes.get(i))) {

                result = prefixes.get(i).length();
                return result;
            }
        }

        return result;
    }



==========================================================================================

3.



<div class="FancyBox__ChildWrapper-hy7ocn-0 jTEAHY Tabs__Wrapper-tumhs7-0 hulkqD" id="task_description"><div class="Tabs__Head-tumhs7-1 hmoWAt"><div class="Tab__Wrapper-xy4vlv-0 dJslpI"><div class="Tab__NameWrapper-xy4vlv-1 fIAAVX">Task</div><div class="TabStatus__IconWrapper-s1rwyq9o-0 iOkcZV" data-test-id="tab-status"></div></div><div class="Tabs__WidgetsWrapper-tumhs7-2 kABosp FancyBox__Wrapper-hy7ocn-1 ffYRgV"></div></div><div class="Tabs__Body-tumhs7-3 hYruva"><div class="FancyBox__FloatingFancyBox-hy7ocn-2 hGyGOC FancyBox__Wrapper-hy7ocn-1 iHQzMo"><div class="FancyBox__ChildWrapper-hy7ocn-0 kbVwhn" style="height: 100%; position: relative;"><div style="height: 100%; overflow: auto;" id="standard_task_description" class="protected"><div class="task-description-content">

<meta http-equiv="content-type" content="text/html; charset=utf-8">


<div id="brinza-task-description">
<p>Two non-negative integers N and M are said to be <i>similar</i> if their decimal representations can be obtained from each other by rearranging their digits. Note that a correct decimal representation does not contain leading zeroes (except for number 0). For example:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>1234 is similar to 4312;</li>
<li>12 is similar to 12;</li>
<li>113 is NOT similar to 133 (there are different numbers of individual digits);</li>
<li>100 is NOT similar to 10 (010 contains a leading zero, so it is not a correct decimal representation).</li>
</ul>
</blockquote><p>Write a function:</p>
<blockquote><p style="font-family: monospace; font-size: 9pt; display: block; white-space: pre-wrap"><tt>class Solution { public int solution(int N); }</tt></p></blockquote>
<p>that, given a non-negative integer N, returns the number of non-negative integers similar to N.</p>
<p>For example, given N = 1213 the function should return 12 because there are twelve integers similar to 1213, namely: 1123, 1132, 1213, 1231, 1312, 1321, 2113, 2131, 2311, 3112, 3121 and 3211.</p>
<p>Given N = 123 the function should return 6 because there are six integers similar to 123, namely: 123, 132, 213, 231, 312 and 321.</p>
<p>Given N = 100 the function should return 1 because there is only one similar integer (the number itself). 001 and 010 are both incorrect decimal representations of integers.</p>
<p>Given N = 0 the function should return 1 bacause there is only one similar integer (the number itself).</p>
<p>Assume that:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>N is an integer within the range [<span class="number">0</span>..<span class="number">2,000,000,000</span>].</li>
</ul>
</blockquote><p>Complexity:</p>
<blockquote><ul style="margin: 10px;padding: 0px;"><li>expected worst-case time complexity is O(log(N));</li>
<li>expected worst-case space complexity is O(1).</li>
</ul>
</blockquote></div>
<div style="margin-top:5px">
<small>Copyright 2009–2018 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.</small>
</div>

</div></div></div></div></div></div>



    public static int solution(int N) {

        int result = 0;

        String s = String.valueOf(N);
        int M = s.length();

        String t = s.substring(0, 1);
        boolean check = false;
        
        for (int i = 1; i < M; i++) {

            if (!t.equals(s.substring(i, i + 1))) {
                 check = true;
            }
        }

        /*
        * all are same
        * */
        if(!check){
            return 1;
        }

        Set<String> set = permutation(s);

        result = set.size();
        return result;
    }

    public static Set<String> permutation(String str) {

        Set<String> lis = new HashSet<>();

        permutation("", str, lis);
        return lis;
    }

    private static void permutation(String prefix, String str,
                                    Set<String> set) {

        int N = str.length();

        if (N == 0) {

            if (!prefix.startsWith("0")) {
                set.add(prefix);
            }
        } else {

            for (int i = 0; i < N; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1), set);
            }
        }
    }




