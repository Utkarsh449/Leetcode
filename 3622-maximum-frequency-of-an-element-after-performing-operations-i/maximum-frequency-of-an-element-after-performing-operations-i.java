class Solution {
    public int maxFrequency(int[] nums, int k, int numOperations) {
        int maxNum = 0;
        for(int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        int[] count = new int[maxNum + 1];
        for(int num : nums) {
            count[num]++;
        }

        int m = Math.min(maxNum, k);
        int total = 0;
        int freq = 0;
        for(int i = 0; i <= m; i++) {
            total += count[i];
        }

        int ans = Math.min(total, numOperations);
        for(int i = 1; i <= maxNum; i++) {
            if(i + k <= maxNum) {
                total += count[i + k];
            }
        
            if(i - k - 1 >= 0) {
                total -= count[i - k - 1];
            }

            freq = Math.min(total, count[i] + numOperations);
            ans = Math.max(ans, freq);
        }

        return ans;

        // Map<Integer, Integer> count = new HashMap<>();
        // Map<Integer, Integer> diff = new TreeMap<>();
        // for(int num : nums) {
        //     count.put(num, count.getOrDefault(num, 0) + 1);

        //     int l = num - k;
        //     int r = num + k;
        //     diff.put(l, diff.getOrDefault(l, 0) + 1);
        //     diff.put(r + 1, diff.getOrDefault(r + 1, 0) - 1);
        //     diff.putIfAbsent(num, 0);
        // }

        // int c = 0;
        // int ans = 0;
        // for(int key : diff.keySet()) {
        //     c += diff.get(key);
        //     int freq = 0;
        //     if(count.containsKey(key)) {
        //         freq = Math.min(c, numOperations + count.get(key));
        //     } else {
        //         freq = Math.min(c, numOperations);
        //     }

        //     ans = Math.max(ans, freq);
        // }
        
        // return ans;
    }
}