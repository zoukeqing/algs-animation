package removeElements;

/**
 * @author zoukeqing
 * @data 2019/1/29 9:30
 * @description 203 删除链表中的元素
 */
/*
    删除链表中等于给定值val的所有元素
    1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6 ,val = 6
    return 1 --> 2 --> 3 --> 4 --> 5
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        // 使用递归
        if (head == null)
            return head;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }
}
