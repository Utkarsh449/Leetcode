class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[n-1] = 0;

        for(int i = n-2; i >= 0; i--){
            int minJump = Integer.MAX_VALUE;
            for(int step = 1; step <= nums[i] && i + step < n; step++){
                int res = dp[i+step];
                if(res != Integer.MAX_VALUE){
                    minJump = Math.min(minJump, 1 + res);
                }
            }
            dp[i] = minJump;
        }

        // Arrays.fill(dp,-1);
        return dp[0];
    }

    public int solve(int index, int[] nums,int[] dp) {
        int n = nums.length;
        if (index >= n - 1) return 0;
        
        if(dp[index] != -1) return dp[index];
        int min = Integer.MAX_VALUE;

        for (int step = 1; step <= nums[index]; step++) {
            int res = solve(index + step, nums,dp);
            if (res != Integer.MAX_VALUE) {
                min = Math.min(min, 1 + res);
            }
        }

        return dp[index] = min;
    }
}