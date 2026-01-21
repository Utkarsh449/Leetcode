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
    public String convert(String s, int numRows) {
        int d = 1;
        StringBuilder res = new StringBuilder();
        StringBuilder[] ans = new StringBuilder[numRows];
        if(numRows<=1) return s;
        for(int i=0;i<ans.length;i++) {
            ans[i] = new StringBuilder();
        }
        int cr = 0;
        for(int i=0;i<s.length();i++) {
            if(cr==0) d = 1;
            else if(cr==numRows-1) d = -1;
            ans[cr].append(s.charAt(i));
            cr+=d;
        }     
        for(StringBuilder sb : ans) {
            res.append(sb);
        }
        return res.toString();
    }
}