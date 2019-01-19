package MinStack;

/**
 * @title 最小栈
 */
/*
设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.
 */
class MinStack {
    /**
     * initialize your data structure here.
     */

    Integer[] nums;
    int size;

    public MinStack() {
        nums = new Integer[5];
        size = 0;
    }

    public void push(int x) {
        if (size >= nums.length) {
            resize(nums.length * 2);
        }
        nums[size] = x;
        size++;
    }

    public void pop() {
        if (size < nums.length / 4) {
            resize(nums.length / 2);
        }
        nums[size - 1] = null;
        size--;
    }

    public int top() {
        return nums[size - 1];
    }

    public int getMin() {
        Integer min = nums[0];
        for (int i = 1; i < size; i++) {
            if (nums != null && min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    private void resize(int length) {
        // 重置容量
        Integer[] newNums = new Integer[length];
        for (int i = 0; i < size; i++) {
            newNums[i] = nums[i];
        }
        nums = newNums;
    }
}

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj =
 * new MinStack(); obj.push(x); obj.pop(); int param_3 = obj.top(); int param_4
 * = obj.getMin();
 */