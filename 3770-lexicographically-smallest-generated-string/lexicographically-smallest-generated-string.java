import java.util.*;

class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int len = n + m - 1;
        
        char[] word = new char[len];
        Arrays.fill(word, '?');
        
        // Step 1: Apply mandatory 'T' constraints
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (word[i + j] != '?' && word[i + j] != str2.charAt(j)) {
                        return "";
                    }
                    word[i + j] = str2.charAt(j);
                }
            }
        }
        
        // Step 2: Track the final '?' for each 'F' constraint
        List<Integer>[] criticalWindows = new ArrayList[len];
        for (int i = 0; i < len; i++) {
            criticalWindows[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                int lastQ = -1;
                
                for (int j = 0; j < m; j++) {
                    if (word[i + j] == '?') {
                        lastQ = i + j;
                    }
                }
                
                if (lastQ == -1) {
                    boolean match = true;
                    for (int j = 0; j < m; j++) {
                        if (word[i + j] != str2.charAt(j)) {
                            match = false; 
                            break;
                        }
                    }
                    if (match) return ""; 
                } else {
                    criticalWindows[lastQ].add(i);
                }
            }
        }
        
        // Step 3: Greedily fill remaining '?' characters
        for (int idx = 0; idx < len; idx++) {
            if (word[idx] == '?') {
                boolean[] forbidden = new boolean[26];
                
                for (int i : criticalWindows[idx]) {
                    boolean match = true;
                    for (int j = 0; j < m; j++) {
                        if (i + j == idx) continue; 
                        if (word[i + j] != str2.charAt(j)) {
                            match = false; 
                            break;
                        }
                    }
                    if (match) {
                        forbidden[str2.charAt(idx - i) - 'a'] = true;
                    }
                }
                
                boolean placed = false;
                for (int c = 0; c < 26; c++) {
                    if (!forbidden[c]) {
                        word[idx] = (char) ('a' + c);
                        placed = true;
                        break;
                    }
                }
                
                if (!placed) return "";
            }
        }
        
        return new String(word);
    }
}