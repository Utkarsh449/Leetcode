class Solution {
    public int lenOfVDiagonal(int[][] grid) {
        return new Solver(grid).solve();
    }

    static final class Solver {
        static final int ONE = 1_000_000;
        static final int ONE2 = 2 * ONE;

        final int[][] grid;
        final int m;
        final int n;
        final int m1;
        final int n1;

        public Solver(int[][] grid) {
            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;
            this.m1 = m - 1;
            this.n1 = n - 1;
        }

        int solve() {
            int[][][] q = new int[4][][];
            q[0] = prepare();
            q[1] = prepare();
            lens1(q[0]);
            lens2(q[1]);
            int r = max(q[0], q[1]);
            if (r == 0) {
                return r;
            }
            q[2] = prepare();
            q[3] = prepare();
            lens3(q[2]);
            lens4(q[3]);
            for (int i = 1; i < 4; i++) {
                r = Math.max(r, max(q[i], q[(i + 1) % 4]));
            }
            return r == 0 ? 1 : r;
        }

        void lens1(int[][] r) {
            for (int i = 1; i < m; i++) {
                final var prevg = grid[i - 1];
                final var prevr = r[i - 1];
                final var curr = r[i];
                for (int j = 1; j < n; j++) {
                    final int current = grid[i][j];
                    curr[j] = current == 1 ? ONE : matches(prevg[j - 1], current) ? prevr[j - 1] + 1 : 1;
                }
            }
        }

        void lens2(int[][] r) {
            for (int i = 1; i < m; i++) {
                final var prevg = grid[i - 1];
                final var prevr = r[i - 1];
                final var curr = r[i];
                for (int j = n - 2; j >= 0; j--) {
                    final int current = grid[i][j];
                    curr[j] = current == 1 ? ONE : matches(prevg[j + 1], current) ? prevr[j + 1] + 1 : 1;
                }
            }
        }

        void lens3(int[][] r) {
            for (int i = m - 2; i >= 0; i--) {
                final var prevg = grid[i + 1];
                final var prevr = r[i + 1];
                final var curr = r[i];
                for (int j = n - 2; j >= 0; j--) {
                    final int current = grid[i][j];
                    curr[j] = current == 1 ? ONE : matches(prevg[j + 1], current) ? prevr[j + 1] + 1 : 1;
                }
            }
        }

        void lens4(int[][] r) {
            for (int i = m - 2; i >= 0; i--) {
                final var prevg = grid[i + 1];
                final var prevr = r[i + 1];
                final var curr = r[i];
                for (int j = 1; j < n; j++) {
                    final int current = grid[i][j];
                    curr[j] = current == 1 ? ONE : matches(prevg[j - 1], current) ? prevr[j - 1] + 1 : 1;
                }
            }
        }

        int[][] prepare() {
            final int[][] r = new int[m][n];
            for (int i = 0; i < m; i++) {
                r[i][0] = grid[i][0] == 1 ? ONE : 1;
                r[i][n1] = grid[i][n1] == 1 ? ONE : 1;
            }
            for (int i = 1; i < n; i++) {
                r[0][i] = grid[0][i] == 1 ? ONE : 1;
                r[m1][i] = grid[m1][i] == 1 ? ONE : 1;
            }
            return r;
        }


        int max(final int[][] a, final int[][] b) {
            int max = -1;
            for (int i = 0; i < m; i++) {
                final var arow = a[i];
                final var brow = b[i];
                for (int j = 0; j < n; j++) {
                    final int bb = brow[j];
                    if (bb >= ONE) {
                        max = Math.max(max, (bb + arow[j]) % ONE);
                    }
                }
            }
            return max < 1 ?  max + 1 : max;
        }

        private boolean matches(int prev, int current) {
            return prev + current == 2 || prev == 1 && current == 2;
        }
    }
}