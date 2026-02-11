public class Solution {
    public int minimumOneBitOperations(int n) {
        // Base case
        if (n == 0) return 0;
        // Precompute function[i] = number of operations to flip the i-th bit alone
        long[] function = new long[32];
        function[0] = 1; // flipping the least significant bit needs 1 operation
        for (int i = 1; i < 32; i++) {
            function[i] = 2 * function[i - 1] + 1;
            // Pattern: 1, 3, 7, 15, 31, ... = (1 << (i + 1)) - 1
        }
        int result = 0;
        int sign = 1; // alternates between +1 and -1 for each bit set
        // Traverse bits from high (MSB) to low (LSB)
        for (int i = 30; i >= 0; i--) {
            // Check if i-th bit is set in n
            if ((n & (1 << i)) == 0) continue;

            // Add or subtract corresponding function[i]
            if (sign > 0) {
                result += function[i];
            } else {
                result -= function[i];
            }
            // Flip sign (since each set bit changes operation direction)
            sign *= -1;
        }
        return result;
    }
}