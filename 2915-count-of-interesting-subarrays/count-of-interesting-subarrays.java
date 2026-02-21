class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int N = nums.size();
        if ( modulo == 1 ) return (long)N * (N+1) / 2;
        int[] sum = new int[N+1];

        for ( int ii = 0; ii < N; ii++ ) {
            sum[ii+1] = sum[ii] + (nums.get(ii) % modulo == k ? 1 : 0);
        }

        long result = 0;

        int[] cnt = new int[Math.min(modulo,N+1)];
        for ( int ss : sum ) {
            if ( ss >= k ) result += cnt[(ss-k)%modulo];
            cnt[ss%modulo]++;
        }

        return result;
    }
}