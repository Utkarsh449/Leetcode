class Solution {
    /**
     * Sets entire rows and columns to zero if an element is zero.
     * Uses two boolean arrays to track which rows and columns contain zeros.
     * 
     * @param matrix The input matrix to be modified in-place
     */
    public void setZeroes(int[][] matrix) {
        // Get matrix dimensions
        int rows = matrix.length;
        int cols = matrix[0].length;
      
        // Arrays to track which rows and columns should be set to zero
        boolean[] zeroRows = new boolean[rows];
        boolean[] zeroCols = new boolean[cols];
      
        // First pass: identify all rows and columns that contain zeros
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    // Mark this row and column to be zeroed out
                    zeroRows[i] = true;
                    zeroCols[j] = true;
                }
            }
        }
      
        // Second pass: set elements to zero based on marked rows and columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If current row or column is marked, set element to zero
                if (zeroRows[i] || zeroCols[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}