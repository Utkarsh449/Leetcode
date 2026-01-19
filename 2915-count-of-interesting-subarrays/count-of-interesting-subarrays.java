import java.util.*;

class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long ans = 0;
        Map<Integer, Long> freq = new HashMap<>();
        freq.put(0, 1L); // prefix sum 0 initially occurs once

        long count = 0;
        for (int x : nums) {
            if (x % modulo == k) {
                count++;
            }
            int rem = (int)(count % modulo);
            int target = (rem - k + modulo) % modulo;
            
            ans += freq.getOrDefault(target, 0L);
            freq.put(rem, freq.getOrDefault(rem, 0L) + 1);
        }
        return ans;
    }
}
