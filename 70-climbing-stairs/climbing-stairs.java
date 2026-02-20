// // // Recursion - tc: O(2^n), sc:O(n)
// // class Solution {
// //     public int climbStairs(int n) {
// //         if(n == 1 || n==2) return n;

// //         return climbStairs(n-1) + climbStairs(n-2);
// //     }
// // }


// // // Memoization - tc=O(n), sc=O(n) + O(n)
// // class Solution {
// //     public int climbStairs(int n) {
// //         int[] dp= new int[n+1];
// //         return ways(n, dp);
// //     }
// //     private int ways(int n, int[] dp){
// //         if(n==1 || n==2) return n;
// //         if(dp[n] != 0) return dp[n];
// //         return dp[n] = ways(n-1, dp) + ways(n-2, dp);
// //     }
// // }


// // // Tabulation - tc=O(n), sc=O(n)
// // class Solution {
// //     public int climbStairs(int n) {
// //         if(n==1 || n==2) return n;
// //         int[]dp = new int[n+1];
// //         dp[1] = 1;
// //         dp[2] = 2;
// //         for(int i=3; i<=n; i++){
// //             dp[i] = dp[i-1]+dp[i-2];
// //         } 
// //         return dp[n];
// //     }
// // }


// // Tabulation - tc=O(n), sc = O(1)
// class Solution {
//     public int climbStairs(int n) {
//         if(n==1 || n==2) return n;
//         int ans = 0;
//         int prev1 = 1;
//         int prev2 = 2;
//         for(int i=3; i<=n; i++){
//             ans = prev1+prev2;
//             prev1 = prev2;
//             prev2 = ans;
//         }
//         return ans;
//     }
// }

class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return find(n,dp);
    }
    private int find(int n, int[] dp){
        if(n<=2) return n;
        if(dp[n] != 0) return dp[n];
        return dp[n] = find(n-1,dp) + find(n-2,dp);
    }
}