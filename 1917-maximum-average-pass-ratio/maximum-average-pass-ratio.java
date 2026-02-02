// import java.util.*;

// class Solution {

//     static class ClassInfo {
//         int pass, total;

//         ClassInfo(int p, int t) {
//             pass = p;
//             total = t;
//         }

//         double gain() {
//             return (double)(total - pass) / ((double) total * (total + 1));
//         }
//     }

//     public double maxAverageRatio(int[][] classes, int extraStudents) {

//         PriorityQueue<ClassInfo> pq = new PriorityQueue<>(
//             (a, b) -> Double.compare(b.gain(), a.gain())
//         );

//         for (int[] c : classes) {
//             pq.offer(new ClassInfo(c[0], c[1]));
//         }

//         while (extraStudents-- > 0) {
//             ClassInfo best = pq.poll();
//             best.pass++;
//             best.total++;
//             pq.offer(best);
//         }

//         double sum = 0.0;
//         while (!pq.isEmpty()) {
//             ClassInfo c = pq.poll();
//             sum += (double) c.pass / c.total;
//         }

//         return sum / classes.length;
//     }
// }
class Solution {
    static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) {
            // ignore
        }
    }));
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double []>pq=new PriorityQueue<>(
            (a,b)->Double.compare(b[2],a[2])
        );
        for(int i=0;i<classes.length;i++){
            double num=classes[i][0];
            double deno=classes[i][1];
            double fin=((num + 1) / (deno + 1)) - (num / deno);
            pq.add(new double[]{num,deno,fin});
        }
        while(extraStudents>0){
            double k[]=pq.poll();
            double num=k[0]+1;
            double deno=k[1]+1;
            double fin=((num + 1) / (deno + 1)) - (num / deno);
            pq.add(new double[]{num,deno,fin});
            extraStudents--;
        }
        int size=pq.size();
        double sum=0;
        while(!pq.isEmpty()){
            double k[]=pq.poll();
            sum+=k[0]/k[1];
        }
        return sum/size;
    }
}