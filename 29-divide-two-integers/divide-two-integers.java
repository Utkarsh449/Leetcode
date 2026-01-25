class Solution {
    public int divide(int dividend, int divisor) {
        // Handle divisor 0
        if(divisor == 0) return 0;

        // Handle overflow case
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        // Handle both negative
        if(dividend < 0 && divisor < 0){
            return dividend / divisor; // safe now
        }

        // Handle one negative
        if(dividend < 0 || divisor < 0){
            return dividend / divisor; // normal division works
        }

        return dividend / divisor; // both positive
    }
}