package backspaceCompare;

/**
 * @title 比较含退格的字符串
 */
/*
给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。



示例 1：

输入：S = "ab#c", T = "ad#c"
输出：true
解释：S 和 T 都会变成 “ac”。
示例 2：

输入：S = "ab##", T = "c#d#"
输出：true
解释：S 和 T 都会变成 “”。
示例 3：

输入：S = "a##c", T = "#a#c"
输出：true
解释：S 和 T 都会变成 “c”。
示例 4：

输入：S = "a#c", T = "b"
输出：false
解释：S 会变成 “c”，但 T 仍然是 “b”。


提示：

1 <= S.length <= 200
1 <= T.length <= 200
S 和 T 只含有小写字母以及字符 '#'。
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().backspaceCompare("ab#c", "ad#c"));
        System.out.println(new Solution().backspaceCompare("ab##", "c#d#"));
        System.out.println(new Solution().backspaceCompare("a##c#####", "#a#c"));
        System.out.println(new Solution().backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stackS = new Stack<Character>(S.length());
        Stack<Character> stackT = new Stack<Character>(T.length());
        char[] cs = S.toCharArray();
        char[] ct = T.toCharArray();
        for (char c : cs) {
            if (c == '#') {
                stackS.pop();
            } else {
                stackS.push(c);
            }
        }
        for (char c : ct) {
            if (c == '#') {
                stackT.pop();
            } else {
                stackT.push(c);
            }
        }
        if (stackS.length() != stackT.length()) {
            return false;
        }
        for (int i = 0; i < stackS.length(); i++) {
            if (stackS.pop() != stackT.pop()) {
                return false;
            }
        }
        return true;
    }

    private class Stack<E> {
        E[] es;
        int size;

        public Stack(int length) {
            es = (E[]) new Object[length];
            size = 0;
        }

        public void push(E e) {
            es[size] = e;
            size++;
        }

        public E pop() {
            E prev = null;
            if (size > 0) {
                prev = es[size - 1];
                es[size - 1] = null;
                size--;
            }
            return prev;
        }

        public boolean isEmpry() {
            return size == 0 ? true : false;
        }

        public int length() {
            return size;
        }
    }
}
