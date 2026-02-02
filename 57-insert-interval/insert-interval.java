class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i=0;
        int n = intervals.length;
        ArrayList<int[]> al = new ArrayList<>();

        while(i<n && intervals[i][1] < newInterval[0]){
            al.add(intervals[i]);
            i++;
        }

        while(i<n && intervals[i][0] <= newInterval[1]){
            int min = Math.min(intervals[i][0], newInterval[0]);
            int max = Math.max(intervals[i][1], newInterval[1]);
            newInterval[0] = min;
            newInterval[1]=max;
            i++;
        }
        al.add(newInterval);

        while(i<n){
            al.add(intervals[i]);
            i++;
        }

        int ans[][] = new int[al.size()][2];
        for(int j=0;j<al.size();j++){
            ans[j] = al.get(j);
        }

        return ans;

    }
}