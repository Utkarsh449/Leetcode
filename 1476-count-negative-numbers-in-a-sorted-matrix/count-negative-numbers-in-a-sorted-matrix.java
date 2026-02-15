class Solution {
    public int countNegatives(int[][] grid) {
        int cols = grid[0].length;
        int count = 0;
        
        for (int[] row : grid) {
            for (int j = 0; j < cols; j++) {
                // Optimization: First negative means all subsequent 
                // numbers in this row are also negative.
                if (row[j] < 0) {
                    count += cols - j;
                    break;
                }
            }
        }
        return count;
    }
}