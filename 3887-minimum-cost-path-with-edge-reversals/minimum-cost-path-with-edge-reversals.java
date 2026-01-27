import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, 2 * w});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<long[]> pq =
            new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            if (u == n - 1) return (int) d;
            if (d > dist[u]) continue;

            for (int[] next : graph[u]) {
                int v = next[0];
                long cost = next[1];

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                    pq.offer(new long[]{dist[v], v});
                }
            }
        }

        return -1;
    }
}
