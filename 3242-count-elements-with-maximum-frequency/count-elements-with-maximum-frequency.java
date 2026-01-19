import java.util.*;

class Solution {
    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;

        // Count frequencies
        for (int x : nums) {
            int f = freq.getOrDefault(x, 0) + 1;
            freq.put(x, f);
            maxFreq = Math.max(maxFreq, f);
        }

        // Count total elements having maxFreq
        int ans = 0;
        for (int f : freq.values()) {
            if (f == maxFreq) {
                ans += f;
            }
        }
        return ans;
    }
}