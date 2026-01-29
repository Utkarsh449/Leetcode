class Solution {
    public int[] getNoZeroIntegers(int n) {
        for (int a = 1; a < n; a++) {
            int b = n - a;
            if (!hasZero(a) && !hasZero(b)) {
                return new int[]{a, b};
            }
        }
        return new int[]{}; // zal nooit nodig zijn, want oplossing is gegarandeerd
    }

    // Helperfunctie: checkt of een getal een 0 bevat
    private boolean hasZero(int x) {
        while (x > 0) {
            if (x % 10 == 0) return true;
            x /= 10;
        }
        return false;
    }
}