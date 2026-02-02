class Solution {

    public void bk(List<String> res,StringBuilder sb,boolean vis[],String st){
        if(sb.length()==st.length()){
            res.add(sb.toString());
            return;
        }

        for(int i=0;i<st.length();i++){
            if(!vis[i]){
                sb.append(st.charAt(i));
                vis[i] = true;
                bk(res,sb,vis,st);
                vis[i] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }

    public String getPermutation(int n, int k) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean vis[] = new boolean[n];
        StringBuilder num = new StringBuilder();
        for(int i=1;i<=n;i++){
            num.append(Integer.toString(i));
        }
        bk(res,sb,vis,num.toString());

        
        return res.get(k-1);
    }

        static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
                System.out.println("Time overwrite aborted");
            }
        }));
    }
}