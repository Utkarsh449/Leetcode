class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        if (nums.size() == 2) return 1;

        // convert list to an array
        Integer[] numsArray = nums.toArray(new Integer[nums.size()+1]);
        numsArray[numsArray.length-1] = Integer.MIN_VALUE; // add a dummy, that will finish increasing subarray

        int maxLength = 1;

        int currentIncreasingLength = 1;
        int previousIncreasingLength = 1;

        Integer previousValue = numsArray[0];
        for (int i = 1; i < numsArray.length; i++) {
            Integer currentValue = numsArray[i];
            if (currentValue > previousValue) {
                currentIncreasingLength++;
            } else {
                maxLength = Math.max(maxLength, currentIncreasingLength / 2); // treat subarray as 2 subarrays of half-length
                maxLength = Math.max(maxLength, Math.min(currentIncreasingLength, previousIncreasingLength));

                previousIncreasingLength = currentIncreasingLength;
                currentIncreasingLength = 1;
            }
            previousValue = currentValue;
        }

        return maxLength;
    }
}