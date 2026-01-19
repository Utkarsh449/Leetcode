class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        long ans = 0;
        int n = nums.length;

        int leftBound = -1; // last index with out-of-range element
        int lastMin = -1;   // last index where nums[i] == minK
        int lastMax = -1;   // last index where nums[i] == maxK

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x < minK || x > maxK) {
                leftBound = i; // reset window
            }
            if (x == minK) {
                lastMin = i;
            }
            if (x == maxK) {
                lastMax = i;
            }
            int minLast = Math.min(lastMin, lastMax);
            if (minLast > leftBound) {
                ans += minLast - leftBound;
            }
        }

        return ans;
    }
}