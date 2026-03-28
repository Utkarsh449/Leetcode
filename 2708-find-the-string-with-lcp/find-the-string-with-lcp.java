class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char nonLetter = (char) ('a' - 1);
        char c = nonLetter;
        char[] word = new char[n];
        
        // Initialize the word array with a dummy character
        for (int i = 0; i < n; i++) {
            word[i] = nonLetter;
        }

        for (int i = 0; i < n; ++i) {
            if (word[i] != nonLetter) {
                continue;
            }
            if (++c > 'z') {
                return "";
            }
            // Fill characters based on LCP values
            for (int j = i; j < n; ++j) {
                if (lcp[i][j] > 0) {
                    word[j] = c;
                }
            }
        }

        // Validate the generated word against the LCP matrix
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int nextLcp = (i + 1 < n && j + 1 < n) ? lcp[i + 1][j + 1] : 0;
                int currLcp = (word[i] == word[j]) ? 1 + nextLcp : 0;
                if (lcp[i][j] != currLcp) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}