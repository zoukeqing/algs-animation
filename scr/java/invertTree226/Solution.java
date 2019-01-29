package invertTree226;

import structure.binary_search_tree.BST;

/**
 * @author zoukeqing
 * @data 2019/1/29 21:44
 * @description 反转二叉树
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = invertTree(root.right);
        newRoot.right = invertTree(root.left);
        return newRoot;
    }

    // Test
    public static void main(String[] args) {
//        BST<Integer> bst = new BST<>();
        int[] nums = {4, 2, 7, 1, 3, 6, 9};
//        for (int i = 0; i < nums.length; i++) {
//            bst.add(nums[i]);
//        }
//        System.out.println(bst.toString());
//        System.out.println(new Solution().invertTree());
        Solution solution = new Solution();
        for (int i = 0; i < nums.length; i++) {
            solution.add(nums[i]);
        }
        System.out.println(solution.toString());
        System.out.println("=================");
//        System.out.println(solution.invertTree(solution.getRoot()));
        // 测试结果
        testResult(solution.invertTree(solution.getRoot()));
    }

    private static void testResult(TreeNode node) {
        if (node == null)
            return;
        testResult(node.left);
        System.out.println(node.val);
        testResult(node.right);
    }

    private TreeNode root;
    private int size;

    // 初始化
    public Solution() {
        root = null;
        size = 0;
    }

    public TreeNode getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    // 二分搜索树添加元素
    public void add(Integer e) {
        if (this.root == null)
            this.root = new TreeNode(e);
        else
            add(this.root, e);
    }

    private void add(TreeNode node, Integer e) {
        if (node == null) {
            node = new TreeNode(e);
            return;
        }
        if (e.equals(node.val))
            return;
        if (e.compareTo(node.val) < 0 && node.left == null) {
            node.left = new TreeNode(e);
            return;
        } else if (e.compareTo(node.val) > 0 && node.right == null) {
            node.right = new TreeNode(e);
            return;
        }
        // 递归
        if (e.compareTo(node.val) < 0)
            add(node.left, e);
        else //  e.compareTo(node.val) > 0
            add(node.right, e);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateTreeString(root,0,res);
        return res.toString();
    }

    public void generateTreeString(TreeNode node, int depth, StringBuilder res) {
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.val + "\n");
        generateTreeString(node.left,depth+1,res);
        generateTreeString(node.right,depth+1,res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
