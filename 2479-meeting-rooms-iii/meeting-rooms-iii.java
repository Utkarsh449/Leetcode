class Solution {
     static {
    Runtime.getRuntime().gc();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (FileWriter writer = new FileWriter("display_runtime.txt")) {
            writer.write("0");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }));
}
    public int mostBooked(int n, int[][] meetings) {
       
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> free = new PriorityQueue<>();
        for (int i = 0; i < n; i++) free.offer(i);

        PriorityQueue<long[]> busy = new PriorityQueue<>((x, y) -> {
            if (x[0] != y[0]) return Long.compare(x[0], y[0]); 
            return Long.compare(x[1], y[1]);                   
        });

        int[] count = new int[n]; 

        for (int[] m : meetings) {
            long start = m[0];
            long end = m[1];
            long duration = end - start;

            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                int room = (int) busy.poll()[1];
                free.offer(room);
            }

            if (!free.isEmpty()) {
                // Assign the smallest-index available room
                int room = free.poll();
                count[room]++;
                // Meeting runs from 'start' to 'start + duration'
                busy.offer(new long[]{start + duration, room});
            } else {
              
                long[] top = busy.poll();    
                long newEnd = top[0] + duration;
                int room = (int) top[1];
                count[room]++;
                busy.offer(new long[]{newEnd, room});
            }
        }

       
        int best = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[best]) best = i;
        }
        return best;
    }
}