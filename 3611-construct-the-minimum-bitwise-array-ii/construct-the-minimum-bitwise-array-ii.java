class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int currentNum = nums.get(i);
            if (currentNum == 2) {
                result[i] = -1;
            } else {
                for (int bitPosition = 1; bitPosition < 32; bitPosition++) {
                    if ((currentNum >> bitPosition & 1) == 0) {
                        result[i] = currentNum ^ (1 << (bitPosition - 1));
                        break;
                    }
                }
            }
        }
      
        return result;
    }
}