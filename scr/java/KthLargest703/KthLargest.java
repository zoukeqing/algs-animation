package KthLargest703;

class KthLargest {
    private int k;
    MinHeap<Integer> arr;

    public static void main(String[] args) {
//        int k = 3;
//        int[] arr = {4, 5, 8, 2};
//        KthLargest kthLargest = new KthLargest(3, arr);
//        // 3 returns 4
//        System.out.println(kthLargest.add(3));
//        // 5 returns 5
//        System.out.println(kthLargest.add(5));
//        // 10 returns 5
//        System.out.println(kthLargest.add(10));
//        // 9 returns 8
//        System.out.println(kthLargest.add(9));
//        // 4 returns 8
//        System.out.println(kthLargest.add(4));

//        ["KthLargest","add","add","add","add","add"]
//        [[1,[]],[-3],[-2],[-4],[0],[4]]

        int k = 1;
        int[] arr = {};
        KthLargest kthLargest = new KthLargest(3, arr);
        // 3 returns 4
        System.out.println(kthLargest.add(-3));
        // 5 returns 5
        System.out.println(kthLargest.add(-2));
        // 10 returns 5
        System.out.println(kthLargest.add(-4));
        // 9 returns 8
        System.out.println(kthLargest.add(0));
        // 4 returns 8
        System.out.println(kthLargest.add(4));
    }

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // 只有k长度的最小堆
        arr = new MinHeap<>(k);
        for (int num : nums) {
            if (arr.getSize()  < k) {
                arr.add(num);
            }
        }
    }

    public int add(int val) {
        arr.heapify(val);
        return arr.peek();
    }

    /**
     * 自定义最小堆，底层采用数组数据结构
     * E 数据类型需要继承Comparable，具有数值比较的功能
     */
    private class MinHeap<E extends Comparable<E>> {
        private Array<E> data;

        public MinHeap() {
            data = new Array<>();
        }

        public MinHeap(int capacity) {
            data = new Array<>(capacity);
        }

        /**
         * 0
         * 1   2
         * 3   4   5   6
         * 父节点 i
         * 左节点 2 * i + 1
         * 右节点 2 * i + 2
         *
         * @param index
         * @return 返回index父节点的索引
         */
        private int parent(int index) {
            if (index == 0) {
                throw new IllegalArgumentException("索引为0没有父节点");
            }
            return (index - 1) / 2;
        }

        private int leftChild(int index) {
            return index * 2 + 1;
        }

        public void add(E e) {
            // 使用数组数据结构添加元素置于末尾
            data.add(e);
            siftUp(getSize() - 1);
        }

        /**
         * 最小堆的性质：左右节点的元素值不能大于父节点的元素值
         *
         * @param index 当前索引
         */
        private void siftUp(int index) {
            // 当前节点与父结点进行比较，当前节点比父节点小则进行交换
            while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) > 0) {
                data.swap(index, parent(index));
                index = parent(index);
            }
        }

        /**
         * 这里和Java中的PriorityQueue类 heapify 方法实现同样的效果
         * ★★★★★
         * 根据题意
         * 如果待比较的元素比最小堆堆顶大，则替换
         *
         * @param e 待比较的元素值
         */
        public void heapify(E e) {
            if (peek() == null){
                return;
            }
            if (peek().compareTo(e) > 0) {
                data.set(e);
                // 从堆顶开始
                siftDown(0);
            }
        }

        /**
         * @param index
         */
        private void siftDown(int index) {
            // while循环，依据条件是是否存在左孩子
            while (leftChild(index) < data.getSize()) {
                int j = leftChild(index);
                // 寻找左右孩子的最小的元素值
                if (j + 1 < data.getSize() && data.get(j).compareTo(data.get(j + 1)) > 0) {
                    // 存在右孩子而且右孩子的元素值比左孩子的元素值更小
                    j++;
                }
                if (data.get(index).compareTo(data.get(j)) < 0) {
                    break;
                }
                // 运行到此处说明存在左右孩子的元素值比父节点更小
                data.swap(index, j);
                index = j;
            }
        }

        /**
         * @return 返回顶节点
         */
        public E peek() {
            return data.get(0);
        }

        public int getSize() {
            return data.getSize();
        }

        public boolean isEmpty() {
//            return getSize() == 0 ? true: false;
            return data.isEmpty();
        }
    }

    /**
     * 自定义数组，数据类型为E
     */
    private class Array<E> {
        private E[] data;
        private int size;

        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        public Array() {
            // 数组长度默认为10
            this(10);
        }

        /**
         * @param index 当前索引
         * @return 返回元素值
         */
        public E get(int index) {
//            if (index < 0 || index > size - 1) {
//                throw new IllegalArgumentException("数组越界");
//            }
            return data[index];
        }

        public void set(E e) {
            set(0, e);
        }

        public void set(int index, E e) {
            data[index] = e;
        }

        /**
         * 添加元素
         *
         * @param index 索引
         * @param e     元素值
         */
        public void add(int index, E e) {
//            if (index < 0 || index > size) {
//                throw new IllegalArgumentException("index索引不在数组范围内");
//            }
            // 扩容
            if (size == data.length) {
                resize(2 * data.length);
            }
            // 将index索引后面的数值往后移一位
            for (int i = size - 1; i > index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = e;
            size++;
        }

        /**
         * 末尾添加元素
         *
         * @param e 元素值
         */
        public void add(E e) {
            add(size, e);
        }

        /**
         * 删除元素
         *
         * @param index 索引
         * @return 返回被删除的元素
         */
        public E remove(int index) {
//            if (index < 0 || index > size) {
//                throw new IllegalArgumentException("index索引不在数组范围内");
//            }
            E ret = data[index];
            // index索引后的元素往前移动一位
            for (int i = index; i < size - 1; i++) {
                data[index] = data[index + 1];
            }
            size--;
            data[size] = null;
            // 缩容，而且数组为1的时候就不能再除2 ，1/2 = 0，长度为0 的数组就不能扩容
            if (size == data.length / 4 && data.length / 2 != 0) {
                resize(data.length / 2);
            }
            return ret;
        }

        /**
         * 输出末尾元素
         *
         * @return 返回被删除的元素
         */
        public E remove() {
            return remove(size - 1);
        }

        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }

        /**
         * @return 返回数组的长度
         */
        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0 ? true : false;
        }

        public void swap(int index1, int index2) {
            E tmp = data[index1];
            data[index1] = data[index2];
            data[index2] = tmp;
        }
    }
}