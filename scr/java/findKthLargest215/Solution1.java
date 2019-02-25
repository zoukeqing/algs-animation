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
class Solution1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        System.out.println(new Solution1().findKthLargest(nums, 2));
//        // [3,2,3,1,2,4,5,5,6] 和 k = 4
        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(new Solution1().findKthLargest(nums2, 4));
//        [7,6,5,4,3,2,1]
//        5
        // 3
//        int[] nums3 = {7, 6, 5, 4, 3, 2, 1};
//        System.out.println(new Solution().findKthLargest(nums3, 5));

//[1]
//        1
        int[] nums3 = {1};
        System.out.println(new Solution1().findKthLargest(nums3,1));
    }

    /**
     * 分治算法，只算需要的部分
     *
     * @param nums
     * @param k
     * @return 返回堆顶元素
     */
    public int findKthLargest(int[] nums, int k) {
        int l = 0;
        int r = nums.length - 1;
        int index = partition(nums, l, r);
        while (k - 1 != index) {
            if (k - 1 < index) {
                r = index - 1;
                index = partition(nums, l, r);
            } else { // k - 1 > index
                l = index + 1;
                index = partition(nums, l, r);
            }
        }
        return nums[index];
    }

    /**
     * 快速排序，只做一次排序
     * 这里做一个降序排序，随机选择一个标准数，大于标准数的放左边，小于标准数的放右边
     *
     * @param nums
     * @param l
     * @param r
     * @return 返回基准数的下标
     */
    private int partition(int[] nums, int l, int r) {
        // 标准数
        int stand = nums[l];
        int low = l;
        int high = r;
        while (low < high) {
            while (low < high && stand >= nums[high]) {
                high--;
            }
            // 填，不做交换
            nums[low] = nums[high];
            while (low < high && stand <= nums[low]) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = stand;
        return low;
    }
}