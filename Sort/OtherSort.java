public class OtherSort {
    // 计数排序
    public static void CountingSort(int[] data, int range) {
        // count 下标为随机数的取值范围
        int[] count = new int[range + 1];
        int[] bucket = new int[data.length + 1];
        for (int i = 0; i <= range; i++) {
            count[i] = 0;
        }
        // count[x] 记录 data 中 x 的个数
        for (int i = 0; i < data.length; i++) {
            count[data[i]]++;
        }
        // count[x] 记录 data 中数值小于 x 的数的个数
        for (int i = 1; i <= range; i++) {
            count[i] += count[i - 1];
        }
        // count-- 为了处理相同的数
        for (int i = data.length - 1; i >= 0; i--) {
            bucket[count[data[i]]--] = data[i];
        }
        //将数放回 data
        for (int i = 0; i < data.length; i++) {
            data[i] = bucket[i + 1];
        }
    }

    // 基数排序
    public static void RadixSort(int[] data) {
        int radix = 10;
        int[] count = new int[radix];
        int[] bucket = new int[data.length];

        int max = data[0];
        for (int i = 1; i < data.length; i++) {
            if (data[i] > max) max = data[i];
        }
        int size = getSize(max);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < radix; j++) {
                count[j] = 0;
            }

            for (int j = 0; j < data.length; j++) {
                int n = getNum(data[j], i);
                // 统计各个桶将要装入的数据个数
                count[n]++;
            }
            // count[i] 表示第i个桶的位置
            for (int j = 1; j < radix; j++) {
                count[j] = count[j] + count[j - 1];
            }

            for (int j = data.length - 1; j >= 0; j--) {
                // 获取关键码
                int n = getNum(data[j], i);
                // 放入对应的桶中
                bucket[count[n] - 1] = data[j];
                // 对应桶的装入数据的位置减一
                count[n]--;
            }
            //将前位数有序的数据倒出来，进行更高位的排序
            for (int j = 0; j < data.length; j++) {
                data[j] = bucket[j];
            }
        }
    }

    // 获取数字的第 d 位
    public static int getNum(int num, int d) {
        return (int) (num / Math.pow(10, d)) % 10;
    }

    // 获取数字长度
    public static int getSize(int num) {
        int size = 1;
        while (num >= 10) {
            num = num / 10;
            size++;
        }
        return size;
    }
}