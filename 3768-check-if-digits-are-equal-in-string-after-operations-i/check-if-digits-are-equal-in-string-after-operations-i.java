class Solution {
    public boolean hasSameDigits(String s) {
        int k = s.length();
        int[] sa = new int[k];
        for(int i=0;i<k;i++)
        {
            sa[i] = s.charAt(i);
        }
       for(k=k-1;k>1;k--)
       {
         for(int i =0;i<k;i++)
        {
            sa[i] = (sa[i]+sa[i+1])%10;
            
        }
       }
       return (sa[0]==sa[1]);
    }
}