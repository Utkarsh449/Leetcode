import java.util.*;

class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        // Use counting sort for O(n) when range is reasonable
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // If range is too large, fall back to regular sort
        long range = (long)max - min + 1;
        if (range > 2000000) {
            return sortBasedSolution(nums, k);
        }
        
        // Counting sort
        int[] count = new int[(int)range];
        for (int num : nums) {
            count[num - min]++;
        }
        
        // Process in order
        int distinctCount = 0;
        int prev = Integer.MIN_VALUE;
        
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) continue;
            
            int num = i + min;
            for (int j = 0; j < count[i]; j++) {
                int next = Math.max(num - k, prev + 1);
                if (next <= num + k) {
                    prev = next;
                    distinctCount++;
                }
            }
        }
        
        return distinctCount;
    }
    
    private int sortBasedSolution(int[] nums, int k) {
        Arrays.sort(nums);
        int count = 1;
        int prev = nums[0] - k;
        
        for (int i = 1; i < nums.length; i++) {
            int next = Math.max(nums[i] - k, prev + 1);
            if (next <= nums[i] + k) {
                prev = next;
                count++;
            }
        }
        
        return count;
    }
}