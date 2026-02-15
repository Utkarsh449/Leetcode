class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        for(int i = 0; i < n-2; i++){
            for(int j=0; j < m-2; j++){
                if(isMagic(grid, i, j)) count++;
            }
        }

        return count;
    }

    public boolean isMagic(int[][] g, int r, int c){
        boolean[] seen = new boolean[10];

        for(int i = r; i <= r+2; i++){
            for(int j = c; j <= c+2; j++){
                int val = g[i][j];
                if(val < 1 || val>9 || seen[val] ) return false;
                seen[val] = true;
            }
        }

        for(int i = r; i <= r+2; i++){
            if(g[i][c]+g[i][c+1]+g[i][c+2]!=15) return false;
        }
        for(int i = c; i <= c+2; i++){
            if(g[r][i]+g[r+1][i]+g[r+2][i]!=15) return false;
        }
        if(g[r][c]+g[r+1][c+1]+g[r+2][c+2] != 15) return false;
        if(g[r][c+2]+g[r+1][c+1]+g[r+2][c] != 15) return false;

        return true;

    }
}