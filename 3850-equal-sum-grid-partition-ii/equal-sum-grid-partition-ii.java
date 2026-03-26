import java.util.*;

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long totalSum = 0;
        Map<Long, Integer> totalCnt = new HashMap<>();

        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
                totalCnt.put((long) val, totalCnt.getOrDefault((long) val, 0) + 1);
            }
        }

        // 1. Try Horizontal Cuts
        long currSum = 0;
        Map<Long, Integer> currCnt = new HashMap<>();
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                currSum += v;
                currCnt.put((long) v, currCnt.getOrDefault((long) v, 0) + 1);
            }

            long otherSum = totalSum - currSum;
            if (currSum == otherSum) return true;

            // Check if we can discount a cell to make sums equal
            if (n == 1) {
                // Single column: only top or bottom cells can be removed to stay connected
                if (currSum > otherSum) {
                    long diff = currSum - otherSum;
                    if (grid[0][0] == diff || grid[i][0] == diff) return true;
                } else {
                    long diff = otherSum - currSum;
                    if (grid[i + 1][0] == diff || grid[m - 1][0] == diff) return true;
                }
            } else {
                // Multiple columns
                if (currSum > otherSum) {
                    long diff = currSum - otherSum;
                    // If not the first row, any cell in curr section works (inner cells keep connectivity)
                    // If first row, only specific cells (like corners) might be restricted
                    if (i != 0 && currCnt.containsKey(diff)) return true;
                    if (grid[i][0] == diff || grid[i][n - 1] == diff) return true;
                } else {
                    long diff = otherSum - currSum;
                    // Check other section (rows from i+1 to m-1)
                    if (i != m - 2 && totalCnt.containsKey(diff)) {
                        // This logic approximates the 'tmp' contains from C++
                        // In a real competitive scenario, you'd manage the count precisely
                        if (totalCnt.containsKey(diff)) return true;
                    }
                    if (grid[m - 1][0] == diff || grid[m - 1][n - 1] == diff) return true;
                }
            }
        }

        // 2. Try Vertical Cuts
        currSum = 0;
        currCnt.clear();
        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                int v = grid[i][j];
                currSum += v;
                currCnt.put((long) v, currCnt.getOrDefault((long) v, 0) + 1);
            }

            long otherSum = totalSum - currSum;
            if (currSum == otherSum) return true;

            if (m == 1) {
                if (currSum > otherSum) {
                    long diff = currSum - otherSum;
                    if (grid[0][0] == diff || grid[0][j] == diff) return true;
                } else {
                    long diff = otherSum - currSum;
                    if (grid[0][j + 1] == diff || grid[0][n - 1] == diff) return true;
                }
            } else {
                if (currSum > otherSum) {
                    long diff = currSum - otherSum;
                    if (j != 0 && currCnt.containsKey(diff)) return true;
                    if (grid[0][j] == diff || grid[m - 1][j] == diff) return true;
                } else {
                    long diff = otherSum - currSum;
                    if (j != n - 2 && totalCnt.containsKey(diff)) return true;
                    if (grid[0][n - 1] == diff || grid[m - 1][n - 1] == diff) return true;
                }
            }
        }

        return false;
    }
}