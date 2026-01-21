class Solution {
    public long countSubarrays(int[] nums, int k) {
        long count = 0;
        int max = 0;
        for(int i:nums)if(i>max)max = i;
        int n = nums.length;
        int l = 0, r = 0; int curr_cnt = 0;
        while(r<n){
            if(nums[r]==max)curr_cnt++;
            while(curr_cnt>=k){
                count+=(n-r);
                if(nums[l]==max)curr_cnt--;
                l++;
            }
            r++;
        }
        return count;
    }
}