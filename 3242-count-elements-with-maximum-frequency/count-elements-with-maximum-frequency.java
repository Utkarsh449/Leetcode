class Solution {
    public int maxFrequencyElements(int[] nums) {
        if(nums.length<=1) return nums.length;
        byte[] freq=new byte[101];
        int freqNum=0;
        int maxFreq=0;
        for(int elem:nums){
            if(++freq[elem]==maxFreq){
                freqNum++;
            }
            else if(freq[elem]>maxFreq){
                maxFreq=freq[elem];
                freqNum=1;
            }
        }
        return freqNum*maxFreq;
    }
}