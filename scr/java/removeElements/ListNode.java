package removeElements;

import java.io.IOException;

/**
 * @author zoukeqing
 * @data 2019/1/29 9:33
 * @description
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x){
        this.val = x;
    }
    ListNode(int[] nums){
        if (nums == null || nums.length ==0)
            throw new IllegalArgumentException("nums 数组不能为空");
//        this(nums[0]);
        this.val = nums[0];
        ListNode cur = this;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur.next != null){
            res.append(cur.next.val + " --> ");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
