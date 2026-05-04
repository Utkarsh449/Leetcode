class Solution {
    public int minDistance(int x, int y, int[][] dp, String word1, String word2) {
        if(x==0 || y==0) return Math.max(x,y);
        if(dp[x][y]!=0) return dp[x][y];
        if(word1.charAt(x-1)==word2.charAt(y-1)) {
            dp[x][y] = minDistance(x-1,y-1,dp,word1,word2);
        } else {
            int r = minDistance(x-1,y-1,dp,word1,word2)+1;
            int d = minDistance(x-1,y,dp,word1,word2)+1;
            int i = minDistance(x,y-1,dp,word1,word2)+1;
            dp[x][y] = Math.min(r,Math.min(d,i));
        }
        return dp[x][y];
    }
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length();
        int[][] dp = new int[n1+1][n2+1];
        return minDistance(n1,n2,dp,word1,word2);
    }
}