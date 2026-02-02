class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxd = 0;
        int marea=0;
        for(int[] r:dimensions) {
            int l = r[0];
            int w = r[1];
            int d = l*l+w*w;
            int a = l*w;
            if(d>maxd) {
                maxd=d;
                marea=a;
            }
            else if(d==maxd) {
                marea=Math.max(marea,a);
            }
        }
        return marea;
    }
}