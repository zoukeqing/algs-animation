package reverseString;

/**
 * @title 反转字符串
 */
/*
编写一个函数，其作用是将输入的字符串反转过来。

示例 1:

输入: "hello"
输出: "olleh"
示例 2:

输入: "A man, a plan, a canal: Panama"
输出: "amanaP :lanac a ,nalp a ,nam A"
 */
class Solution {
    public String reverseString(String s) {
        char[] array = s.toCharArray();
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            array[i] ^= array[length - 1 - i];
            array[length - 1 - i] ^= array[i];
            array[i] ^= array[length - 1 - i];
        }
        return new String(array);
    }
}