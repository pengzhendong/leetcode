public class ExchangeSort {
    // 冒泡排序
    public static void BubbleSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    Swap.Swap(data, j, j + 1);
                    swapped = true;
                }
            }
            // 如果没有交换，则数组已有序
            if (!swapped) break;
        }
    }

    // 快速排序
    public static void QuickSort(int[] data, int begin, int end) {
        if (end > begin) {
            // 返回基准值的位置
            int pos = Partition(data, begin, end);
            // 对左边的数进行快速排序
            QuickSort(data, begin, pos - 1);
            // 对右边的数进行快速排序
            QuickSort(data, pos + 1, end);
        }
    }

    public static int Partition(int[] data, int begin, int end) {
        int pivot = data[begin];
        // 记录最终基准值应该放置的位置
        int pos = begin;
        for (int i = begin + 1; i <= end; i++) {
            if (data[i] <= pivot) {
                // 找到比基准值小的数，pos 右移
                pos++;
                // 将比基准值小的数放到一起
                Swap.Swap(data, pos, i);
            }
        }
        // 将基准值和分界处那个小的交换，即 data[pos]
        Swap.Swap(data, begin, pos);
        return pos;
    }
}