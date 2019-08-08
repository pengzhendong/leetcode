public class SelectSort {
    // 简单选择排序
    public static void SimpleSelectSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                // 选出后面最小的数
                if (data[j] < data[min]) min = j;
            }
            // 放到前面去
            Swap.Swap(data, min, i);
        }
    }

    // 堆排序
    public static void HeapSort(int[] data) {
        //从下往上构造最大堆，即维护 N 个最大堆
        for (int i = data.length / 2; i >= 0; i--) {
            PercDown(data, i, data.length);
        }

        for (int i = data.length - 1; i > 0; i--) {
            //将堆顶的最大元素换到最后面去，相当于出堆
            Swap.Swap(data, 0, i);
            //从上往下维护堆，长度减一
            PercDown(data, 0, i);
        }
    }

    // 维护堆，即将小元素从父节点换到子节点
    public static void PercDown(int[] data, int p, int len) {
        while (LeftChild(p) < len) {
            // 选中左孩子
            int child = LeftChild(p);
            // 该父节点有两个孩子节点且右孩子大，选中右孩子
            if (child != len - 1 && data[child + 1] > data[child]) child++;
            // 如果孩子大，交换父节点和孩子节点
            if (data[p] < data[child]) Swap.Swap(data, p, child);
            else break;
            // 处理子节点以下的堆
            p = child;
        }
    }

    public static int LeftChild(int parent) {
        return 2 * parent + 1;
    }
}