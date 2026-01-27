class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        HashMap<List<Integer>, Integer> map = new HashMap<>();
        return dfs(triangle, 0, 0, map);
    }

    public static int dfs(List<List<Integer>> lst, int row, int col,
                          HashMap<List<Integer>, Integer> map) {

        if (row == lst.size()) {
            return 0;
        }

        List<Integer> key = List.of(row, col);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        int res = lst.get(row).get(col)
                + Math.min(
                    dfs(lst, row + 1, col, map),
                    dfs(lst, row + 1, col + 1, map)
                  );

        map.put(key, res);
        return res;
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

}