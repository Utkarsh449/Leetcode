class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int columnLength = matrix[0].length;
        int rowLength = matrix.length;
        int resultRow = -1;
        for (int i = 0; i < rowLength; i++) {
            if (matrix[i][columnLength - 1] >= target) {
                resultRow = i;
                break;
            }
        }
        if (resultRow == -1) {
            return false;
        }
        for (int i = 0; i < columnLength; i++) {
            if (matrix[resultRow][i] == target) {
                return true;
            }
        }
        return false;
    }
}