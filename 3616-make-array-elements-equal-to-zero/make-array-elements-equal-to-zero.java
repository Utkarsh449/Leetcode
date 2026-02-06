/*

The Logic
To reduce the entire array to zeros, the total "impact" (bounces) sent to the left must balance the total impact sent to the right.

For any chosen starting zero at index i:

If LeftSum == RightSum: You can start in either direction (Left or Right). Both will result in a perfect balance where all numbers reach zero. (Add 2 to count).

If |LeftSum - RightSum| == 1: You must strictly start in the direction of the larger sum to "knock down" the extra value immediately and equalize the sides. (Add 1 to count).

Otherwise: It is impossible to clear the array. (Add 0).

*/

class Solution {
    public int countValidSelections(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int validCount = 0;
        int currentPrefixSum = 0;

        for (int num : nums) {
            if (num != 0) {
                currentPrefixSum += num;
            } else {
                // For a zero at this index:
                // LeftSum  = currentPrefixSum
                // RightSum = totalSum - currentPrefixSum
                int rightSum = totalSum - currentPrefixSum;

                if (currentPrefixSum == rightSum) {
                    validCount += 2; // Can go Left or Right
                } else if (Math.abs(currentPrefixSum - rightSum) == 1) {
                    validCount += 1; // Must go towards the larger sum
                }
            }
        }

        return validCount;
    }
}