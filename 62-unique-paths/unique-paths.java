class Solution {

    public int uniquePaths(int m, int n) {
        int dp[][] = new int[m][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[m - 1][i] = 1;
        }

        for (int j = m - 1; j >= 0; j--) {
            dp[j][n - 1] = 1;
        }

        for (int r = m - 2; r >= 0; r--) {
            for (int c = n - 2; c >= 0; c--) {
                dp[r][c] = dp[r][c + 1] + dp[r + 1][c];
            }
        }

        return dp[0][0];
    }
}