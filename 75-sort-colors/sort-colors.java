class Solution {
    /**
     * Sorts an array containing only 0s, 1s, and 2s in-place using Dutch National Flag algorithm.
     * Time Complexity: O(n), Space Complexity: O(1)
     * 
     * @param nums Array containing values 0, 1, or 2 to be sorted
     */
    public void sortColors(int[] nums) {
        // leftBoundary: rightmost index of 0s section (initially -1, before array)
        int leftBoundary = -1;
      
        // rightBoundary: leftmost index of 2s section (initially at array length, after array)
        int rightBoundary = nums.length;
      
        // current: current element being examined
        int current = 0;
      
        // Process elements while current pointer hasn't reached the 2s section
        while (current < rightBoundary) {
            if (nums[current] == 0) {
                // Move 0 to the left section and advance both pointers
                leftBoundary++;
                swap(nums, leftBoundary, current);
                current++;
            } else if (nums[current] == 2) {
                // Move 2 to the right section, don't advance current 
                // (need to check the swapped element)
                rightBoundary--;
                swap(nums, rightBoundary, current);
            } else {
                // Element is 1, leave it in the middle section
                current++;
            }
        }
    }
  
    /**
     * Helper method to swap two elements in an array.
     * 
     * @param nums The array containing elements to swap
     * @param i Index of first element
     * @param j Index of second element
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}