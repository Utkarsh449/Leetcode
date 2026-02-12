class Solution {
    public int findFinalValue(int[] nums, int original) {
           for(int index=0; index<nums.length; index++) {
        	  
        	  if(nums[index]==original) {
        		  original=original*2;
                   return findFinalValue(nums, original);
                  
        	  }
          }
          
          return original;

    }
}