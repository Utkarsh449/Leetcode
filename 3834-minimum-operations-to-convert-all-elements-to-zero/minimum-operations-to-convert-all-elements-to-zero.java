class Solution {
    public int minOperations(int[] nums) {
        int[] stack = new int[nums.length + 1];
        int top = 0;
        int ans = 0;

        for (int x : nums) {
            if (x == 0) {       
                ans += top;
                top = 0;
                continue;
            }

            while (stack[top] > x) {
                top--;
                ans++;
            }

            if (stack[top] != x) {
                stack[++top] = x;
            }
        }

        return ans + top;
    }
}