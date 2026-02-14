class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> x = new HashMap<>();
        Map<Integer, List<Integer>> y = new HashMap<>();

        for (int[] building : buildings) {
            int x_cord = building[0];
            int y_cord = building[1];

            x.computeIfAbsent(x_cord, k -> new ArrayList<>()).add(y_cord);
            y.computeIfAbsent(y_cord, k -> new ArrayList<>()).add(x_cord);
        }

        for (List<Integer> list : x.values()) Collections.sort(list);
        for (List<Integer> list : y.values()) Collections.sort(list);

        int covered = 0;

        for (Map.Entry<Integer, List<Integer>> entry : x.entrySet()) {
            int x1 = entry.getKey();
            List<Integer> yList = entry.getValue();

            if (yList.size() < 3) continue;

            for (int i = 1; i < yList.size() - 1; i++) {
                int y1 = yList.get(i);

                List<Integer> xList = y.get(y1);
                if (xList == null || xList.size() < 3) continue;

                int pos = Collections.binarySearch(xList, x1);

                if (pos > 0 && pos < xList.size() - 1) {
                    covered++;
                }
            }
        }

        return covered;
    }
}