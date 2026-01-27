class Solution {
    public int[] decimalRepresentation(int n) {
        ArrayDeque<Integer> al = new ArrayDeque<>();
        int arrSize = 0;
        while(n > 0) {
            al.push(n % 10);
            if(n % 10 != 0) {
                arrSize++;
            }
            n /= 10;
        }
        int[] res = new int[arrSize];
        int p = 1;
        for(int i = res.length-1; i >= 0; i--) {
            while(al.peekLast() == 0) {
                al.removeLast();
                p *= 10;
            }
            res[i] = al.removeLast() * p;
            p *= 10;
        }
        return res;
    }
}