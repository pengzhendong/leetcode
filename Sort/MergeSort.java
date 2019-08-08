public class MergeSort {
    // 归并排序
    public static void Merge(int[] data, int lBegin, int lEnd, int rEnd) {
        // 记录开始索引，用于将结果放回
        int begin = lBegin;
        int rBegin = lEnd + 1;

        int[] temp = new int[rEnd - lBegin + 1];
        int temPos = 0;

        // 合并两个子数组
        while (lBegin <= lEnd && rBegin <= rEnd) {
            if (data[lBegin] <= data[rBegin]) temp[temPos++] = data[lBegin++];
            else temp[temPos++] = data[rBegin++];
        }

        // 将剩下的数直接插 temp 入后面
        while (lBegin <= lEnd) {
            temp[temPos++] = data[lBegin++];
        }
        while (rBegin <= rEnd) {
            temp[temPos++] = data[rBegin++];
        }

        // 将 temp 中的有序数放回 data
        for (int i = begin; i <= rEnd; i++) {
            data[i] = temp[i - begin];
        }
    }

    // 合并排序结果
    public static void MergeSort(int[] data, int begin, int end) {
        if (end > begin) {
            int mid = (begin + end) / 2;
            MergeSort(data, begin, mid);
            MergeSort(data, mid + 1, end);
            Merge(data, begin, mid, end);
        }
    }
}