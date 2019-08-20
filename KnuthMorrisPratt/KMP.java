public class KMP {
    private static int[] getNext(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        next[0] = 0;
        next[1] = 0;

        int i = 0;
        int j = 2;
        while (j < len) {
            if (pattern.charAt(i) == pattern.charAt(j - 1)) {
                i++;
                if (pattern.charAt(i) == pattern.charAt(j)) next[j] = next[i];
                else next[j] = i;
                j++;
            } else {
                if (i == 0) next[j++] = i;
                else i = next[i];
            }
        }
        return next;
    }

    public static int kmpSearch(String text, String pattern) {
        int M = text.length();
        int N = pattern.length();
        if (N > M) return 0;
        if (N < 2) return text.indexOf(pattern);

        int[] next = getNext(pattern);

        int i = 0;
        int j = 0;
        while (i < M && j < N) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (j == 0) i++;
                else j = next[j];
            }
        }
        if (j == N) return i - j;
        return -1;
    }

    public static void main(String[] args) {
        String text = "BBC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";

        long startTime = System.currentTimeMillis();
        System.out.println(kmpSearch(text, pattern));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
        
    }
}