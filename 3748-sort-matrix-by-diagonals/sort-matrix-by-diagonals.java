class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;

        /* Brute Force */
        // int[][] sorted_grid = new int[n][n];
        // Map<Integer, List<Integer>> mp = new HashMap<>();

        // for(int row = 0; row < n; row++) {
        //     for(int col = 0; col < n; col++) {
        //         int key = row - col;
        //         mp.computeIfAbsent(key, k -> new ArrayList<>()).add(grid[row][col]);
        //     }
        // }

        // for(Map.Entry<Integer, List<Integer>> itr : mp.entrySet()) {
        //     int key = itr.getKey();
        //     List<Integer> lst = itr.getValue();

        //     int row = 0;
        //     int col = 0;
            
        //     if(key >= 0) {
        //         lst.sort((num1, num2) -> num2.compareTo(num1));
        //         row = key;
        //     } else {
        //         Collections.sort(lst);
        //         col = Math.abs(key);
        //     }

        //     for(int i = 0; i < lst.size(); i++) {
        //         sorted_grid[row][col] = lst.get(i);
        //         row++;
        //         col++;
        //     }
        // }

        // return sorted_grid;

        /* Better Approach */

        // // First the Bottom - Left Triangle
        // for(int row = n - 1; row >= 0; row--) {
        //     List<Integer> nums = new ArrayList<>();

        //     for(int steps = 0; (steps + row) < n; steps++) {
        //         nums.add(grid[row + steps][steps]);
        //     }
        //     nums.sort((num1, num2) -> num2.compareTo(num1));

        //     for(int steps = 0; (steps + row) < n; steps++) {
        //         grid[row + steps][steps] = nums.get(steps);
        //     }
        // }

        // // First the Top - Right Triangle
        // for(int col = 1; col < n; col++) {
        //     List<Integer> nums = new ArrayList<>();

        //     for(int steps = 0; (steps + col) < n; steps++) {
        //         nums.add(grid[steps][col + steps]);
        //     }
        //     Collections.sort(nums);

        //     for(int steps = 0; (steps + col) < n; steps++) {
        //         grid[steps][col + steps] = nums.get(steps);
        //     }
        // }

        // return grid;

        /* Best Approach */

        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                int x = row, y = col;

                while(x > 0 && y > 0) {
                    if(row >= col) {
                        if(grid[x - 1][y - 1] < grid[x][y]) {
                            int temp = grid[x][y];
                            grid[x][y] = grid[x - 1][y - 1];
                            grid[x - 1][y - 1] = temp;
                        }
                    } else {
                        if(grid[x - 1][y - 1] > grid[x][y]) {
                            int temp = grid[x][y];
                            grid[x][y] = grid[x - 1][y - 1];
                            grid[x - 1][y - 1] = temp;
                        }
                    }

                    x--;
                    y--;
                }
            }
        }

        return grid;
    }
}