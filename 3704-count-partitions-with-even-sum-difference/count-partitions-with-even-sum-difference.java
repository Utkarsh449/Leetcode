class Solution {
    public int countPartitions(int[] nums) {
        int[] pre = new int[nums.length];
        pre[0] = nums[0];
            for (int i=1;i<nums.length;i++){
            pre[i] = pre[i-1]+nums[i];

        }
        int tot = pre[nums.length-1];
        int c=0;

        for(int i=0;i<nums.length-1;i++){
            int l = pre[i];
            int r = tot - pre[i];
            if((l-r)%2 == 0) c++;
        }
        return c;
    }
}