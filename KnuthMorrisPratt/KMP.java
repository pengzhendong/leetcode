public class KMP {
    // private static int[] getNext(String pattern) {
    //     int len = pattern.length();
    //     int[] next = new int[len];
    //     next[0] = 0;
    //     next[1] = 0;

    //     int max = 0;
    //     int i = 2;
    //     while (i < len) {
            
    //     }
    //     return next;
    // }

    private static int[] getNext(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        next[0] = -1;
        
        int i = -1;
        int j = 0;
        while (j < len - 1) {
            if (i == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[j] = i;
                // if (pattern.charAt(i) != pattern.charAt(j)) next[j] = i;
                // else next[j] = next[i];
            } else i = next[i];
        }
        
        return next;
    }

    public static int kmpSearch(String text, String pattern) {
        int M = text.length();
        int N = pattern.length();
        if (N > M) return 0;
        if (N < 2) return text.indexOf(pattern);

        int[] next = getNext(pattern);
        for (int i = 0; i < next.length; i++) {
            System.out.print(next[i] + " ");
        }
        System.out.println();

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
        String text = "ABCABDCABCABDA";
        String pattern = "ABCABAA";

        long startTime = System.currentTimeMillis();
        System.out.println(kmpSearch(text, pattern));
        long endTime = System.currentTimeMillis();
        System.out.println("Runtime: " + (endTime - startTime) + " ms");
        
    }
}