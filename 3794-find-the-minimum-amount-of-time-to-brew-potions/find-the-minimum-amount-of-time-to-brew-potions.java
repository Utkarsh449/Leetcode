class Solution {
    public long minTime(int[] skill, int[] mana) {
        int n = skill.length, m = mana.length;

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + skill[i];

        // Build suffix max positions
        int[] suf = new int[n];
        int sn = 0;
        suf[sn++] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (skill[i] > skill[suf[sn - 1]]) suf[sn++] = i;
        }

        // Build prefix max positions
        int[] pre = new int[n];
        int pn = 0;
        pre[pn++] = 0;
        for (int i = 1; i < n; i++) {
            if (skill[i] > skill[pre[pn - 1]]) pre[pn++] = i;
        }

        long ans = 0;
        for (int j = 1; j < m; j++) {
            int[] arr;
            int len;

            if (mana[j - 1] < mana[j]) {
                arr = pre;
                len = pn;
            } else {
                arr = suf;
                len = sn;
            }

            long best = 0;
            long A = mana[j - 1], B = mana[j];

            for (int k = 0; k < len; k++) {
                int i = arr[k];
                long v = A * prefix[i + 1] - B * prefix[i];
                if (v > best) best = v;
            }

            ans += best;
        }

        ans += mana[m - 1] * prefix[n];
        return ans;
    }
}