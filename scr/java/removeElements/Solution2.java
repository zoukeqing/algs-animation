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
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        // 给定虚拟头节点
        ListNode prevNode = new ListNode(-1);
        prevNode.next = head; // 传地址
        while (prevNode.next != null && prevNode.next.val == val){
            ListNode delNode = prevNode.next;
            prevNode.next = delNode.next;
            delNode = null;
        }
        return prevNode.next;
    }
}
