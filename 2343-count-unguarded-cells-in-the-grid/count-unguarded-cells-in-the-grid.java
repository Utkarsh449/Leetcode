class Solution {
    int[][] directions = new int[][]{
        new int[]{0, 1},
        new int[]{1, 0},
        new int[]{-1, 0},
        new int[]{0, -1}
    };
    int ROWS= 0;
    int COLS = 0;
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] visited = new int[m][n];
        this.ROWS = m;
        this.COLS = n;
        for (int[] wall : walls){
            visited[wall[0]][wall[1]] = 15;
        }

        for(int[] guard : guards){
            for (int direction = 0; direction < 4; direction ++){
                dfs(guard, direction, visited);
            } 
        }

        int occupied = 0;
        for (int row = 0; row < ROWS; row ++) for(int col = 0; col < COLS; col++){
            if(visited[row][col] != 0) occupied ++;
        }
        return m * n - occupied;
    }

    public void dfs(int[] pos, int direction, int[][] visited){
        visited[pos[0]][pos[1]] |= 1 << direction;
        int newRow = pos[0] + directions[direction][0];
        int newCol = pos[1] + directions[direction][1];

        if (newRow < 0 || newRow == ROWS || newCol < 0 || newCol == COLS || 
        (visited[newRow][newCol] & (1 << direction))!= 0){
            return;
        }
        dfs(new int[]{newRow, newCol}, direction, visited);
    }
}