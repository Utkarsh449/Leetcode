class Solution {
    public int countSubarrays(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;
        int count = 0;
        // We check every possible subarray of length 3
        // middle index i runs from 1 to n-2
        for (int i = 1; i <= n - 2; i++) {
            if ((nums[i - 1] + nums[i + 1]) * 2 == nums[i]) {
                count++;
            }
        }
        return count;
    }
}