public class InsertionSort {
    // 简单插入排序
    public static void SimpleInsertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int j = i;

            // temp 前面的数都是有序的
            while (j > 0 && data[j - 1] > temp) {
                // 将所有比 temp 大的元素往后移一个位置
                data[j] = data[j - 1];
                j--;
            }
            // 插入 temp
            data[j] = temp;
        }
    }

    // 折半插入排序
    public static void HalfInsertSort(int[] data) {
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