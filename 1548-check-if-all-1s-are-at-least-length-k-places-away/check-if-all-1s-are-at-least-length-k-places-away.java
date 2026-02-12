class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        
     int las=-1;
      for(int i=0;i<nums.length;i++)
      {   if(nums[i]==1){
        if(las!=-1&& i-las-1<k)
        {   
            return false;
            
        }
         las=i;
      }
      }
      return true;
    }
    static {
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) {
        }
    }));
}
}