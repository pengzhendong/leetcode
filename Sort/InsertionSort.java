public class InsertionSort {
    // 简单插入排序
    public static void SimpleInsertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int j = i;
            // i 前面的数都是有序的
            while (j > 0 && data[j] < data[j - 1]) {
                swap(data, j, j - 1);
                j--;
            }
        }
    }

    // 无监督项插入排序
    public static void InsertionSort(int[] data) {
        // 将最小的数移动到最前面，则在插入期间避免判断数组越界
        int index = 0;
        for (int i = 1; i < data.length; i++) {
            if (data[i] < data[index]) index = i;
        }
        // 为了保证排序的稳定性，需要一个一个移动
        for (int i = index; i > 0; i--) {
            swap(data, i, i - 1);
        }

        for (int i = 2; i < data.length; i++) {
            int j = i;
            // i 前面的数都是有序的
            while (data[j] < data[j - 1]) {
                swap(data, j, j - 1);
                j--;
            }
        }
    }

    // 交换数组中的两个元素
    public static void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // 折半插入排序
    public static void HalfInsertionSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int low = 0;
            int high = i - 1;
            int mid = 0;
            // 二分法查找该插入的位置
            while (high >= low) {
                mid = (low + high) / 2;
                if (temp > data[mid]) low = mid + 1;
                else high = mid - 1;
            }
            // 将 data[low] ~ data[i - 1] 往后移一个位置
            for (int j = i; j > low; j--) {
                data[j] = data[j - 1];
            }
            // 插入 temp, 此时 low > high
            data[low] = temp;
        }
    }

    // 希尔排序
    public static void ShellSort(int[] data) {
        // 增量不断减少为一半
        for (int increment = data.length / 2; increment != 0; increment /= 2) {
            for (int i = increment; i < data.length; i++) {
                int temp = data[i];
                int j = i;
                // 该增量前面还有数且比它小
                while (j >= increment && data[j - increment] > temp) {
                    // 将前面比它小的数右移
                    data[j] = data[j - increment];
                    j -= increment;
                }
                // 插入
                data[j] = temp;
            }
        }
    }
}