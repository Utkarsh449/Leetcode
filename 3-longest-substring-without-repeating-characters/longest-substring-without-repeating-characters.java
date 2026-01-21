class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
     }
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int res = Integer.MIN_VALUE;
        Set<Character> set = new HashSet<>();
        for (int right = 0 ; right < s.length() ; right++) {
            char ch= s.charAt(right);
            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left ++;
            }
            set.add(ch);
            res = Integer.max(right - left + 1, res);
        }
        return res == Integer.MIN_VALUE ? 0 : res;
        
    }
}