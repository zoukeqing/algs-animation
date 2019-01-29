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
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode = null;
        }
        if (head == null)
            return null;
        ListNode tmpNode = head;
        while (tmpNode.next != null) {
            if (tmpNode.next.val == val) {
                ListNode delNode = tmpNode.next;
                tmpNode.next = delNode.next;
                delNode = null;
            } else
                tmpNode = tmpNode.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        System.out.println(new Solution().removeElements(head,6));
        System.out.println(new Solution2().removeElements(head,6));
    }
}
