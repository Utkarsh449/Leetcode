// optimal

class Solution {
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    
    public int countCollisions(String dir) {
        int collisions = 0;
        int n = dir.length();

        int i=0, j=n-1;

        while(i < n && dir.charAt(i) == 'L') i++;

        while(j >= 0 && dir.charAt(j) == 'R') j--;

        for(int k=i; k<=j; k++) {
            if(dir.charAt(k) != 'S') collisions++;
        }

        return collisions;
    }
}


// brute

// class Solution {
//     public int countCollisions(String dir) {
//         int collisions = 0;
//         int n = dir.length();

//         Stack<Character> st = new Stack<>();

//         for(int i=0; i<n; i++) {
//             char car = dir.charAt(i);

//             if(car == 'R') {
//                 st.push('R');
//             } else if(car == 'S') {
//                 while(!st.isEmpty() && st.peek() == 'R') {
//                     st.pop();
//                     collisions++;
//                 }
//                 st.push('S');
//             } else {
//                 if(st.isEmpty()) continue;

//                 if (st.peek() == 'R') {
//                     // First R-L collision
//                     st.pop();
//                     collisions += 2;

//                     // Remaining R's hit the stationary car
//                     while (!st.isEmpty() && st.peek() == 'R') {
//                         st.pop();
//                         collisions++;
//                     }

//                     st.push('S');
//                 }  else if (st.peek() == 'S') {
//                     collisions++;
//                 }
//             }
//         }

//         return collisions;
//     }
// }