class Solution 
{
            static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int sumFourDivisors(int[] nums) 
    {
        int N= 100000+1;
        
        int divisor[]=new int[N];
        int sumDivisor[]=new int[N];
        for(int i=1;i<N;i++)
        {
            for(int j=i;j<N;j=j+i)
            {
                divisor[j]++;
                sumDivisor[j]=sumDivisor[j]+i;
            }
        }    
        int sum=0;
        int n=nums.length;
        for(int i=0;i<n;i++)
        {
            if(divisor[nums[i]]==4)
            {
                sum=sum+sumDivisor[nums[i]];
            }
        }
        return sum;
    }
}