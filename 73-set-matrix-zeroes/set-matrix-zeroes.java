class Solution {
    public void setZeroes(int[][] matrix) {
        int m=matrix.length;
        int n=matrix[0].length;
        int col0=1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    if(j!=0){
                    matrix[0][j]=0;
                    }
                    else{
                        col0=0;
                    }
                }
            }
        }

        for(int i=m-1;i>0;i--){
            for(int j=n-1;j>0;j--){
                if ((matrix[i][0] == 0) || (matrix[0][j]==0)) {
                    matrix[i][j] = 0;
                }
            }
        }
        if(matrix[0][0]==0){
            for(int i=0;i<n;i++){
                matrix[0][i]=0;
            }
        }
        if(col0==0){
            for(int j=0;j<m;j++){
                matrix[j][0]=0;
            }
        }

    }
    
}

// class Solution {
//     public void setZeroes(int [][] matrix) {
//         for(int i=0;i<matrix.length;i++){
//             for(int j=0;j<matrix[0].length;j++){
//                 if(matrix[i][j]==0){
//                     int k=0;
//                     while(k<matrix.length){
//                         if(matrix[k][j]==0){
//                             k++;
//                             continue;
//                         }
//                         matrix[k][j]=1024;
//                         k++;
//                     }
//                     k=0;
//                     while(k<matrix[0].length){
//                         if(matrix[i][k]==0){
//                             k++;
//                             continue;
//                         }
//                         matrix[i][k]=1024;
//                         k++;
//                     }

//                 }
//             }
//         }

//         for(int i=0;i<matrix.length;i++){
//             for(int j=0;j<matrix[0].length;j++){
//                 if(matrix[i][j]==1024){
//                     matrix[i][j]=0;
//                 }
//             }
//         }
//     }
// } 