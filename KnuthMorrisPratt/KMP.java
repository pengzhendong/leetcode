public class KMP {
    private static int[] getNext(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        
        return next;
    }

    public static int kmp(String text, String pattern) {
        int[] next = getNext(pattern);
        int M = text.length();
        int N = pattern.length();

        return -1;
    }

    public static void main(String[] args) {
        String text = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";

        // JDK 中使用暴力破解法，因为大多数情况下，字符串都不长，暴力破解空间复杂度较小
        System.out.println(text.indexOf(pattern));

        long startTime = System.currentTimeMillis();
        System.out.println(kmp(text, pattern));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
        
    }
}