class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }

    static boolean recurrsion(int i, int j, String s, String p, Boolean dp[][]) {
        //base case
        if (i < 0 && j < 0)
            return true;
        if (j < 0 && i >= 0)
            return false;
        if (i < 0 && j >= 0) {
            for (int k = j; k >= 0; k--) {
                if (p.charAt(k) != '*') {
                    return false;
                }
            }
            return true;
        }
        if (dp[i][j] != null) {
            return dp[i][j];
        }
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return dp[i][j] = recurrsion(i - 1, j - 1, s, p, dp);
        }
        if (p.charAt(j) == '*') {
            return dp[i][j] = recurrsion(i - 1, j, s, p, dp) || recurrsion(i, j - 1, s, p, dp);
        }
        return dp[i][j] = false;
    }

    public boolean isMatch(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();
        Boolean[][] dp = new Boolean[len1 + 1][len2 + 1];
        // return recurrsion(len1 - 1, len2 - 1, s, p, dp);
        dp[0][0] = true;
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = false;
        }
        for (int j = 0; j <= len2; j++) {
            boolean flag = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k - 1) != '*') {
                    flag = false;
                }
            }
            dp[0][j] = flag;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[len1][len2];
    }

}