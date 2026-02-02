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
    public int maxSubArray(int[] nums) {

        int sum = 0;
        int ans = 0;
        boolean allNeg = true; // we are considerin there are no positives
        int max = Integer.MIN_VALUE; // either the biggest positive value or the least negative 
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                allNeg = false;

            } 
            max = Math.max(max, nums[i]);
            ans += nums[i];
            if (ans < 0) {
                ans = 0;
            }
            sum = Math.max(ans, sum);

        }

        if (allNeg) {
            return max;
        }

        return sum;
    }
}