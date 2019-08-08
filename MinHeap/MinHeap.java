
public class MinHeap {
    private int[] data;
    private int capacity;
    private int count;

    private static int leftChild(int parent) { 
        return 2 * parent + 1; 
    }

    private static int parent(int child) { 
        return (child == 0) ? -1 : (child - 1) / 2; 
    }

    private void swap(int i1, int i2) {
        int tmp = this.data[i1];
        this.data[i1] = this.data[i2];
        this.data[i2] = tmp;
    }

    public MinHeap() {
        this.data = new int[10];
        this.capacity = 10;
        this.count = 0;
    }

    public MinHeap(int[] data) {
        this.data = data;
        this.capacity = data.length;
        this.count = data.length;

        for (int i = count / 2; i > 0; i--) {
            shiftDown(i);
        }
    }

    private void shiftDown(int parent) {
        while (leftChild(parent) < this.count) {
            int child = leftChild(parent);
            // 存在右孩子，且右孩子的值更小，取右孩子
            if (child + 1 < this.count && this.data[child] > this.data[child + 1]) child += 1;
            // 当前节点比子节点的值更小，无需 shiftDown
            if (this.data[parent] < this.data[child]) break;
            // 否则交换节点的值，继续将这个值比较大的节点一直 shiftDown 到叶子节点
            swap(parent, child);
            parent = child;
        }
    }

    public void offer(int value) {
        if (this.count == this.capacity) {
            this.capacity *= 2;
            int[] data = new int[this.capacity];
            System.arraycopy(this.data, 0, data, 0, this.data.length);
        }
        this.data[this.count] = value;
        shiftUp(count++);
    }

    private void shiftUp(int child) {
        while (parent(child) >= 0) {
            int parent = parent(child);
            if (this.data[parent] < this.data[child]) break;
            swap(child, parent);
            child = parent;
        }
    }

    public int poll() {
        int value = this.data[0];
        this.data[0] = this.data[--count];
        shiftDown(0);
        return value;
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print(this.data[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        int[] data = new int[] {2, 3, 5, 1, 4, 8, 6, 9};
        for (int value : data) {
            heap.offer(value);
        }

        while (heap.count > 0) {
            System.out.print(heap.poll() + " ");
        }
    }
}