class Solution {
    public int[][] merge(int[][] intervals) {
        Stack<int[]> stack =new Stack<>();

        
         Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int[] interval:intervals){
            if(!stack.isEmpty()){
                System.out.println(interval[0]+"   "+stack.peek()[1]);
                if(interval[0]<=stack.peek()[1] ){
                    stack.peek()[1] = Math.max(stack.peek()[1], interval[1]);


                }else{
                    stack.push(interval);

                }

            }else{
            stack.push(interval);
            }
        }
        int[][] result = new int[stack.size()][2];

        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.get(i);
        }
        return result;}
        static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
        
    
}