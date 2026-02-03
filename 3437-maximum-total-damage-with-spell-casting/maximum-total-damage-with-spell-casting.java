class Solution {
    public long maximumTotalDamage(int[] power) {
        radixSort(power);
        int prev = power[power.length - 1];
        long d0 = prev, d1 = 0, d2 = 0, d3 = 0;
        for (int i = power.length - 2; i >= 0; i--) {
            final int p = power[i];
            if (p == prev) {
                d0 += p;
            } else {
                final int shift = prev - p;
                if (shift == 1) {
                    if (d2 > d3) d3 = d2;
                    d2 = d1;
                    d1 = d0;
                } else if (shift == 2) {
                    if (d2 > d3) d3 = d2;
                    if (d1 > d3) d3 = d1;
                    d2 = d0;
                } else {
                    if (d2 > d3) d3 = d2;
                    if (d1 > d3) d3 = d1;
                    if (d0 > d3) d3 = d0;
                }
                d0 = p + d3;
                prev = p;
            }
        }
        return Math.max(Math.max(d0, d1), Math.max(d2, d3));
    }

    static final int BITS_PER_BYTE = 8;
    static final int BITS = 32; // each int is 32 bits
    static final int R = 1 << BITS_PER_BYTE; // 256
    static final int MASK = R - 1;
    static final int w = BITS / BITS_PER_BYTE; // 4 bytes

    public static void radixSort(int[] arr) {
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