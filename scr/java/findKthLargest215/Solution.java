package findKthLargest215;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
class Solution {
    /**
     * 最小堆
     */
    private int[] minHeap;
    /**
     * 最小堆的长度
     */
    private int size;

    public static void main(String[] args) {
//        int[] nums = new int[5];
//        System.out.println(Arrays.toString(nums));
//        int[] nums = {3, 2, 1, 5, 6, 4};
//        System.out.println(new Solution().findKthLargest(nums, 2));
//        // [3,2,3,1,2,4,5,5,6] 和 k = 4
//        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        System.out.println(new Solution().findKthLargest(nums2, 4));
//        [7,6,5,4,3,2,1]
//        5
        // 3
        int[] nums3 = {7,6,5,4,3,2,1};
        System.out.println(new Solution().findKthLargest(nums3, 5));
    }

    /**
     * @param nums
     * @param k
     * @return 返回堆顶元素
     */
    public int findKthLargest(int[] nums, int k) {
        if (k < 1 || k > nums.length || nums == null) {
            return -1;
        }
        minHeap = new int[k];
        size = 0;
        for (int i = size; i < k; i++) {
            minHeap[i] = nums[i];
            size++;
            siftUp(i);
        }
        for (int i = size; i < nums.length; i++) {
            if (minHeap[0] < nums[i]) {
                minHeap[0] = nums[i];
                siftDown(0);
            }
        }
        return minHeap[0];
    }

    private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parent = parent(index);
        while (index > 0 && minHeap[index] < minHeap[parent]) {
            swap(index, parent);
            index = parent;
            parent = parent(index);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void siftDown(int index) {
        while (leftChild(index) < size) {
            int leftIndex = leftChild(index);
            int minLR = leftIndex;
            if (leftIndex + 1 < size && minHeap[leftIndex] > minHeap[leftIndex + 1]) {
                // 此时和左节点比较后，右节点的值是更小的
                minLR++;
            }
            if (minHeap[index] < minHeap[minLR]) {
                break;
            }
            swap(index, minLR);
            index = minLR;
        }
    }

    /**
     * 交换
     *
     * @param i
     * @param j
     */
    private void swap(int i, int j) {
        minHeap[i] ^= minHeap[j];
        minHeap[j] ^= minHeap[i];
        minHeap[i] ^= minHeap[j];
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }
}