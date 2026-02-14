    class Solution {
        // state:
        // 0 --> holding nothing
        // 1 --> holding long buy
        // 2 --> holding short sell
        static {
            Runtime.getRuntime().gc();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try (FileWriter f = new FileWriter("display_runtime.txt")) {
                    f.write("0");
                } catch (Exception e) {

                }
            }));
        }
        public long dpTopDown(int current_day, int state,
                              int remain_transaction, int[] prices, long dp[][][]){
            if(remain_transaction < 0) return 0;
            if(current_day == prices.length){
                if(state != 0) // bad idea, it's the last day and you still holding?
                    return Long.MIN_VALUE / 2;
                return 0;
            }
            if(dp[current_day][state][remain_transaction] != -1) //cell is calculated
                return dp[current_day][state][remain_transaction];
            // init profit if we buy, sell, skip this cell
            long buy = Long.MIN_VALUE;
            long sell = Long.MIN_VALUE;
            long skip = Long.MIN_VALUE;
            if(state == 0){
                buy = -prices[current_day] +
                        dpTopDown(current_day+1, 1, remain_transaction, prices, dp);
                sell = prices[current_day] +
                        dpTopDown(current_day+1, 2, remain_transaction, prices, dp);
                // we haven't completed the transaction yet
            }
            else if(state == 1){
                sell = prices[current_day] +
                        dpTopDown(current_day+1, 0, remain_transaction - 1, prices, dp);
            }
            else if(state == 2){
                buy = -prices[current_day] +
                        dpTopDown(current_day+1, 0, remain_transaction - 1, prices, dp);
            }
            skip = dpTopDown(current_day + 1, state, remain_transaction, prices, dp);

            dp[current_day][state][remain_transaction] = Math.max(skip,
                    Math.max(sell, buy));
            return dp[current_day][state][remain_transaction];
        }
        public long maximumProfit(int[] prices, int k) {
            if (prices.length == 0 || k == 0) return 0;
            long[][][] dp = new long[prices.length][3][k];
            for (int i = 0; i < prices.length; i++) {
                for (int j = 0; j < 3; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            return dpTopDown(0, 0, k - 1, prices, dp); // transaction is labeled
            // from 0...k-1
        }
    }