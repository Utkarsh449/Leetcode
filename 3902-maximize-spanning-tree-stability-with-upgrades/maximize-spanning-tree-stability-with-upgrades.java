import java.util.*;

class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if(parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if(pa == pb) return false;

            if(rank[pa] < rank[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }

            parent[pb] = pa;
            if(rank[pa] == rank[pb]) rank[pa]++;
            return true;
        }
    }

    private boolean can(int n, int[][] edges, int k, int x) {

        DSU dsu = new DSU(n);
        int used = 0;
        int upgrades = 0;

        List<int[]> optional = new ArrayList<>();

        for(int[] e : edges) {
            int u = e[0], v = e[1], s = e[2], must = e[3];

            if(must == 1) {
                if(s < x) return false;

                if(dsu.union(u, v))
                    used++;
                else
                    return false;
            } else {
                optional.add(e);
            }
        }

        optional.sort((a,b) -> b[2] - a[2]);

        for(int[] e : optional) {
            int u = e[0], v = e[1], s = e[2];

            if(s >= x) {
                if(dsu.union(u,v))
                    used++;
            }
        }

        for(int[] e : optional) {
            int u = e[0], v = e[1], s = e[2];

            if(s < x && 2*s >= x && upgrades < k) {
                if(dsu.union(u,v)) {
                    upgrades++;
                    used++;
                }
            }
        }

        return used == n - 1;
    }

    public int maxStability(int n, int[][] edges, int k) {

        int lo = 1, hi = 200000;
        int ans = -1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(can(n, edges, k, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}