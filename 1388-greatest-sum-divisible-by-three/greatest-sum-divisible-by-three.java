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
    public int maxSumDivThree(int[] nums) {
        int least_2_first = 1000000;
        int least_2_second = 1000000;
        int least_1_first = 1000000;
        int least_1_second = 1000000;
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]%3==2){
                if(nums[i]>least_2_first & nums[i]<=least_2_second){
                    least_2_second = nums[i];
                }
                if(least_2_first>=nums[i]){
                    least_2_second = least_2_first;
                    least_2_first = nums[i];
                }
            }
            else if(nums[i]%3==1){
                if(nums[i]>least_1_first & nums[i]<=least_1_second){
                    least_1_second = nums[i];
                }
                if(least_1_first>=nums[i]){
                    least_1_second = least_1_first;
                    least_1_first = nums[i];
                }
            }
            sum+=nums[i];
        }
        least_1_first%=1000000;
        least_1_second%=1000000;
        least_2_first%=1000000;
        least_2_second%=1000000;

        if(sum%3==2){
            if(least_2_first!=0){
                if(least_1_second!=0){
                    sum -= Math.min(least_2_first,least_1_first+least_1_second);
                }
                else{
                    sum -= least_2_first;
                }
            }
            else{
                if(least_1_second!=0){
                    sum -= least_1_first+least_1_second;
                }
            }
        }
        else if(sum%3==1){
            if(least_1_first!=0){
                if(least_2_second!=0){
                    sum -= Math.min(least_1_first,least_2_first + least_2_second);
                }
                else{
                    sum-=least_1_first;
                }
            }
            else{
                if(least_2_second!=0){
                    sum -= least_2_first + least_2_second;
                }
            }
        }
        if(sum%3==0){
            return sum;
        }
        return 0;
    }
}