class Solution {

	private static int combMod2(int n, int k) {
		while (n > 0 || k > 0) {
			final int nb = n & 1, kb = k & 1;
			if (kb > nb) {
				return 0;
			}
			n >>= 1;
			k >>= 1;
		}
		return 1;
	}

	private static int combMod5(int n, int k, final int[][] C5) {
		int res = 1;
		while (n > 0 || k > 0) {
			final int nd = n % 5;
			final int kd = k % 5;
			if (kd > nd) {
				return 0;
			}
			res = res * C5[nd][kd] % 5;
			n /= 5;
			k /= 5;
		}
		return res;
	}

	public int triangularSum(final int[] nums) {
		final int n = nums.length;
		final int n1 = n - 1;
		// Precompute C(n, k) mod 5 for digits 0..4
		final int[][] C5 = new int[5][5];
		for (int i = 0; i < 5; ++i) {
			C5[i][0] = 1;
			C5[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				C5[i][j] = (C5[i - 1][j - 1] + C5[i - 1][j]) % 5;
			}
		}
		int ans = 0;
		for (int i = 0; i <= n1; ++i) {
			final int a2 = combMod2(n1, i);
			final int a5 = combMod5(n1, i, C5);
			// Combine via CRT
			final int coeffMod10 = (5 * a2 + 6 * a5) % 10;
			ans = (ans + coeffMod10 * (nums[i] % 10)) % 10;
		}
		return ans;
	}

}