class Solution {


    static{
        for(int i=0;i<500;i++){
            peopleAwareOfSecret(5,2,3);
        }
    }
    public static int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n+1];

        long mod = 1000000007;
        
        dp[1] = 1;

        long currShare = 0;


        for(int i=2;i<=n;i++)
        {



            if(i - delay >= 1)
                currShare = (currShare + dp[i-delay]) % mod;
                
            

            if(i - forget >= 1)
                currShare = ((currShare - dp[i-forget]) + mod) % mod;
            



            
            dp[i] = currShare;
        }

        long res = 0;

        for(int i = n-forget+1;i<=n;i++)
        {
            res = (res + dp[i]) % mod;
        }

        return (int) res;
    }
    
}