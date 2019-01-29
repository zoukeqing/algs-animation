package structure.binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zoukeqing
 * @data 2019/1/29 15:11
 * @description 二分搜索树
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        E e;
        Node left, right;

        // 初始化
        Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root; // 根节点
    private int size;

    BST() {
        this.root = null;
        size = 0;
    }

    // 向二分搜索树添加节点
    public void add(E e) {
        if (this.root == null)
            this.root = new Node(e);
        else
            add(this.root, e);
    }

    // 第一种，以node为根的二分搜索树插入元素e，递归算法
    private void add(Node node, E e) {
        // 二分搜索树没有重复的节点，首先判断当前节点与e的值
        if (e.equals(node.e))
            return;
        else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            this.size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            this.size++;
            return;
        }
        if (e.compareTo(node.e) < 0)
            add(node.left, e);
        else
            add(node.right, e);
    }

    // 第二种 ， 优化第一种
    private Node add2(Node node, E e) {
        if (node == null)
            return new Node(e);
        if (e.compareTo(node.e) < 0)
            add2(node.left, e);
        else if (e.compareTo(node.e) > 0)
            add2(node.right, e);
        return node;
    }

    // 查找，查看二分搜索树是否包含元素e
    public boolean contains(E e) {
        return contains(this.root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
//        if (e.equals(node.e))
//            return true;
//        else if (e.compareTo(node.e) < 0)
//            return contains(node.left,e);
//        else if (e.compareTo(node.e)>0)
//            return contains(node.right,e);
//        return false;
        // or
        if (e.equals(node.e))
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(this.root);
    }

    // 前序遍历，递归算法
    private void preOrder(Node node) {
//        if (node == null) {
//            System.out.println("NULL");
//            return;
//        }
//        System.out.print(node.e + " --> ");
//        preOrder(node.left);
//        preOrder(node.right);
        // or
        if (node != null) {
            System.out.print(node.e + " --> ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 巧的是中序遍历得到的是二分搜索树的排序，所以二分搜索树也可以叫排序树
    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print(node.e + " -- ");
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.e + " -- ");
    }

    /*
    总结：
        前中后遍历操作之后发现一个特点，想象一下二叉树，
        前序遍历 在每一个节点的左边制作一个标记，从根节点起始向左沿着线出发，触碰到这个标记则弹出这个节点
        同样中后序遍历分别在每个节点的下边和右边做一个标记，触碰到这个标记也同样弹出来
     */

    // 前序遍历的非递归遍历
    public void prevOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curNode = stack.pop();
            System.out.print(curNode + " -- ");
            if (curNode.right != null)
                stack.push(curNode.right);
            if (curNode.left != null)
                stack.push(curNode.left);
        }
    }

    // 中序的非递归遍历？？
    // 后序的非递归遍历？？

    // 层序遍历，非递归，（广度优先遍历）
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e + " -- ");
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    // 二分搜索树最小的元素
    public E minElement() {
        if (this.size == 0)
            throw new IllegalArgumentException("BST is empty !");
        return minElement(this.root).e;
    }

    private Node minElement(Node node) {
        if (node.left == null)
            return node;
        return minElement(node.left);
    }

    // 同理，二分搜索树最大的元素

    // 删除最小节点，并返回最小元素
    public E removeMin() {
        E e = minElement();
        root = removeMin(this.root);
        return e;
    }
    /*
    循环返回最小元素可以作为排序算法
     */

    // 删除以node为根的二分搜索树的最小节点，返回当前根
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除最大节点并返回最大元素

    /*
     删除任意节点，有三种情况
     删除只有左孩子的节点
     删除只有右孩子的节点
     删除左右都有孩子的节点（1962年，Hibbard提出 Hibbard Deletion）
     */
    // 删除以node为根的二分搜索树并且值为e的节点，递归算法，返回删除节点后的新的二分搜索树的根
    public void remove(E e) {
        root = remove(this.root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e.equals(node.e)
            // 三种情况
            if (node.right == null){ // 只有左孩子的节点
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else if (node.left == null) { // 只有右孩子的节点
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else { // 第三种情况
                Node successor = minElement(node.right); // 找右孩子最小的节点替换被删掉的节点的值
                successor.right = removeMin(node.right);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
                // or找左孩子最大的节点替换当前节点的值
            }
        }
    }

    // 简陋的表示二分搜索树

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以root为根节点，深度为depth的描述二叉树的字符串，递归算法
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
