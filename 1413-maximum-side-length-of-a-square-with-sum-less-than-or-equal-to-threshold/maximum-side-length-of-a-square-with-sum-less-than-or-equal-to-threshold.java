class Solution {
    private boolean helper(int side, int k, int [][] colSum){

        int n = colSum.length - 2, m = colSum[0].length - 2;
        for(int i = side; i<=n; i++){
            int currSum = 0;
            for(int j = 1; j<=m; j++){
                currSum+=(colSum[i][j] - colSum[i-side][j]);
                if(j<side)continue;
                currSum-=colSum[i][j-side] - colSum[i-side][j-side];
                if(currSum<=k){
                    return true;
                }
            }
        }

        return false;


    }

    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length, m = mat[0].length;

        int [][] colSum = new int[n+2][m+2];

        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=m; j++){
                colSum[i][j] = colSum[i-1][j] + mat[i-1][j-1];
            }
        }
        int ans = 0;
        // for(int side = 1; side<=Math.min(n, m); side++){
        //     int x = side + 1, y = side+1;
        //     boolean isValid = helper(side, threshold, colSum);
        //     if(isValid){
        //         ans = side;
        //     }
        // }
        int low = 1, high = Math.min(n, m);
        while(low<=high){
            int mid = (low+high)/2;

            boolean isValid = helper(mid, threshold, colSum);
            if(isValid){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        return high;
    }
}