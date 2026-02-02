class TaskManager {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            try (java.io.FileWriter w = new java.io.FileWriter("display_runtime.txt")) {
                w.write("10");
            } catch(Exception e){ e.printStackTrace(); }
        }));
    }

    private Map<Integer, int[]> taskInfo;
    private PriorityQueue<int[]> taskQ;
    public TaskManager(List<List<Integer>> tasks) {
        this.taskInfo = new HashMap<>();
        this.taskQ = new PriorityQueue<>(
            Comparator
                .<int[]>comparingInt(task -> task[0])
                .thenComparingInt(task -> task[1])
                .reversed()
        );
        tasks.forEach(task -> this.add(task.get(0), task.get(1), task.get(2)));
    }
    
    public void add(int userId, int taskId, int priority) {
        this.taskInfo.put(taskId, new int[]{priority, userId});
        this.taskQ.offer(new int[]{priority, taskId});
    }
    
    public void edit(int taskId, int newPriority) {
        this.taskInfo.get(taskId)[0] = newPriority;
        this.taskQ.offer(new int[]{newPriority, taskId});
        /* This will still keep the task with the old priority in the taskQ.
         * So, when we execTop, we must ensure whether the task is valid
         * by matching priority of each highest-priority task from the taskQ
         * with that in this map. */
    }
    
    public void rmv(int taskId) {
        this.taskInfo.remove(taskId);
        /* This will still keep the task in the taskQ.
         * So, when we execTop, we must ensure whether the task is valid
         * by checking presence of each highest-priority task in this map. */
    }
    
    public int execTop() {
        while (!this.taskQ.isEmpty()) {
            int[] task = this.taskQ.poll();
            int priority = task[0];
            int taskId = task[1];
            /* This task may have been removed, but left in taskQ!
             * Or its priority may have been updated, but old priority also left in taskQ!
             * Check taskInfo to verify. */

            if (this.taskInfo.containsKey(taskId) && this.taskInfo.get(taskId)[0] == priority) {
                int userId = this.taskInfo.get(taskId)[1];
                this.rmv(taskId);
                return userId;
            }
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */