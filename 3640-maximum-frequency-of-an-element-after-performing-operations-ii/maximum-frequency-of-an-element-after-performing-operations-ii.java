class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        radixSort(nums);
        int r = 0;
        int r2 = 0;
        int start = 0;
        int end = 0;
        int end2 = 0;
        int freq = 0;
        for (int i = 0; i < nums.length; i++) {
            final int n = nums[i];
            if (i > 0 && n == nums[i - 1]) {
                freq++;
            } else {
                freq = 1;
            }
            final int min = n - k;
            final int max = n + k;
            while (nums[start] < min) {
                start++;
            }
            while (end < nums.length && nums[end] <= max) {
                end++;
            }
            r = Math.max(r, Math.min(freq + numOperations, end - start));
            final int target = max + k;
            while (end2 < nums.length && nums[end2] <= target) {
                end2++;
            }
            r2 = Math.max(r2, end2 - i);
        }
        return Math.max(r, Math.min(numOperations, r2));
    }

    static final int BITS_PER_BYTE = 8;
    static final int BITS = 32; // each int is 32 bits
    static final int R = 1 << BITS_PER_BYTE; // 256
    static final int MASK = R - 1;
    static final int w = BITS / BITS_PER_BYTE; // 4 bytes

    public static int[] radixSort(int[] arr) {
        int[] aux = new int[arr.length];
        for (int q = 0; q < BITS; q += BITS_PER_BYTE) {
            int[] count = new int[R];
            for (int v : arr) count[(v >>> q) & MASK]++;
            updateCounts(count);
            for (int v : arr) aux[count[(v >>> q) & MASK]++] = v;
            // Copy back
            //System.arraycopy(aux, 0, arr, 0, arr.length);
            final var tmp = arr;
            arr = aux;
            aux = tmp;
        }
        return arr;
    }

    private static void updateCounts(int[] count) {
        int sum = 0;
        for (int r = 0; r < count.length; r++) {
            final int newSum = sum + count[r];
            count[r] = sum;
            sum = newSum;
        }
    }
}