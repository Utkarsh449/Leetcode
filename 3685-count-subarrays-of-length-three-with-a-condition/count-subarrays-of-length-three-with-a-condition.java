class Solution {
    public int countSubarrays(int[] nums) {
        int ans=0;

        for(int i=0;i<nums.length-2;i++)
        {
            int a=nums[i],b=nums[i+1],c=nums[i+2];
            if(2*(a+c)==(b)) ans++;

        }
        return ans;
    }
}