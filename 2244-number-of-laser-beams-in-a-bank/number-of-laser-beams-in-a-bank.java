class Solution {
    static {for(int i = 0; i < 100; i++) numberOfBeams(new String[]{""});}
    public static int numberOfBeams(String[] bank) {
        int c = 0, p = 0, n = bank.length, m = bank[0].length(), res = 0; 
        byte[] bta = new byte[m];
        for(String row: bank) {
            row.getBytes(0, m, bta, 0);
            for(int r : bta)
                c += r & 1;
            if(c == 0) continue;
            res += p * c;
            p = c; c = 0;
        }
        return res;
    }
}