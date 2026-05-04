class Solution {
    /**
     * Calculate the minimum edit distance between two strings using dynamic programming.
     * Edit operations allowed: insert, delete, replace.
     * 
     * @param word1 The first string
     * @param word2 The second string
     * @return The minimum number of operations to convert word1 to word2
     */
    public int minDistance(String word1, String word2) {
        int word1Length = word1.length();
        int word2Length = word2.length();
      
        // dp[i][j] represents the minimum edit distance between 
        // the first i characters of word1 and the first j characters of word2
        int[][] dp = new int[word1Length + 1][word2Length + 1];
      
        // Initialize base case: converting empty string to word2[0...j-1]
        // requires j insertions
        for (int j = 1; j <= word2Length; j++) {
            dp[0][j] = j;
        }
      
        // Process each character of word1
        for (int i = 1; i <= word1Length; i++) {
            // Initialize base case: converting word1[0...i-1] to empty string
            // requires i deletions
            dp[i][0] = i;
          
            // Process each character of word2
            for (int j = 1; j <= word2Length; j++) {
                // If characters match, no operation needed
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // Characters don't match, choose minimum of three operations:
                    // 1. dp[i - 1][j] + 1: delete from word1
                    // 2. dp[i][j - 1] + 1: insert into word1
                    // 3. dp[i - 1][j - 1] + 1: replace character in word1
                    dp[i][j] = Math.min(
                        dp[i - 1][j],                              // delete
                        Math.min(dp[i][j - 1], dp[i - 1][j - 1])   // insert or replace
                    ) + 1;
                }
            }
        }
      
        // Return the minimum edit distance between complete strings
        return dp[word1Length][word2Length];
    }
}