class Solution {
    private static final int MOD = 1000000007;
    public int countPartitions(int[] nums, int k) {
        int len = nums.length, minL = 0, maxL = 0, minR = 0, maxR = 0;
        var pre = new int[len + 1];
        var minDeque = new int[len];
        var maxDeque = new int[len];
        pre[0] = 1;
        pre[1] = 2;
        int p = 2; // p = pre[1]
        minDeque[0] = 0;
        maxDeque[0] = 0;
        for (int l = 0, r = 1; r < len; ++r) {
            int cur = nums[r], i;
            while (minR >= minL && nums[minDeque[minR]] >= cur) minR--;
            minDeque[++minR] = r;
            while (maxR >= maxL && nums[maxDeque[maxR]] <= cur) maxR--;
            maxDeque[++maxR] = r;        
            while (nums[maxDeque[maxL]] - nums[minDeque[minL]] > k) {
                ++l;
                if (minDeque[minL] < l) ++minL;
                if (maxDeque[maxL] < l) ++maxL;
            }
            int sum = l == 0 ? p : (p - pre[l - 1] + MOD) % MOD;
            if (r == len - 1) return sum;
            p = pre[r + 1] = (sum + p) % MOD; 
        }
        return -1; // dump return
    }
}