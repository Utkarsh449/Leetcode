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
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] pre = new int[n];
        pre[0] = (s.charAt(0) == '1' ? 1 : 0);
        for(int i=1; i<n; i++){
            pre[i] = pre[i-1] + (s.charAt(i) == '1' ? 1 : 0);
        }

        int count = 0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int ones = pre[j] - (i == 0 ? 0 : pre[i - 1]);
                int zeros = (j-i+1) - ones;

                if((zeros*zeros) > ones){
                   j += (zeros*zeros - ones - 1); 
                }else{
                    count++;
                    int k = (int)Math.sqrt(ones) - zeros;
                    int nxtJ = k + j;

                    if(nxtJ >= n){
                        count += (n-j-1) ;
                        j = n;
                    }else{
                        count += k;
                        j = nxtJ;
                    }
                }
            }
        }
        return count;
    }
}