class Solution {
    static record Pair(Integer a, Integer b) {}

    public int minScoreTriangulation(int[] values) {
        return minScore_ray(values);
    }
    public static int minScore_ray(int[] values) {
        if (values.length == 3) {
            return values[0] * values[1] * values[2];
        }
        if (values.length == 4) {
            return Math.min(
                values[0] * values[2] * (values[1] + values[3]),
                values[1] * values[3] * (values[0] + values[2]));
        }
        int minSource = Integer.MAX_VALUE;
        for (int i = 0; i < values.length; i++) {
            if (values[i] < minSource) {
                minSource = values[i];
            }
        }
        List<Integer> minSourceIdxs = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == minSource) {
                minSourceIdxs.add(i);
            }
        }
        int minSourceTargetMul = Integer.MAX_VALUE;
        List<Pair> minCandidates = new ArrayList<>();
        for (Integer minSourceIndex : minSourceIdxs) {
            int minTarget = Integer.MAX_VALUE;
            int minTargetIndex = -1;
            for (int i = 0; i < values.length; i++) {
                if (i != minSourceIndex &&
                        i != (minSourceIndex - 1 + values.length) % values.length &&
                        i != (minSourceIndex + 1) % values.length) {
                    if (values[i] < minTarget) {
                        minTarget = values[i];
                        minTargetIndex = i;
                    }
                }
            }
            if (minSource * minTarget == minSourceTargetMul) {
                minCandidates.add(new Pair(minSourceIndex, minTargetIndex));
            } else if (minSource * minTarget < minSourceTargetMul) {
                minCandidates = new ArrayList<>();
                minSourceTargetMul = minSource * minTarget;
                minCandidates.add(new Pair(minSourceIndex, minTargetIndex));
            }
        }
        int globalMin = Integer.MAX_VALUE;
        for (Pair p : minCandidates) {
            int a = Math.min(p.a, p.b);
            int b = Math.max(p.a, p.b);
            int[] p1 = new int[b-a+1];
            int[] p2 = new int[values.length - b + a + 1];
            for (int i = a, k = 0; i <= b; i++) {
                p1[k++] = values[i];
            }
            for (int i = b, k = 0; i <= values.length + a; i++) {
                p2[k++] = values[i % values.length];
            }
            int iterMin = minScore_ray(p1) + minScore_ray(p2);
            if (iterMin < globalMin) {
                globalMin = iterMin;
            }
        }
        return globalMin;
    }
}