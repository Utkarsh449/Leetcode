class Solution {
    public int[][] generateMatrix(int n) {
        List<Integer> tmp = new ArrayList<Integer>();
        int[][] matrix = new int[n][n];
        int w = n;
        int h = n-1;

        do{
           tmp.add(w);
           if(w == 0) break;
           tmp.add(h);
           if(h == 0) break;
           w--;
           h--;
        }while(true);
    
        int x1=0, y1=0, x2=0, y2=0, cont=1;
        for(int i=0, shifts=1; i<tmp.size();i++, shifts++){
            switch(shifts){
                case 1:
                    x2 = x1 + tmp.get(i) - 1;
                    cont = getXPos(x1, y1, x2, cont, matrix);
                    y1++;
                break;
                case 2:
                    y2 = y1 + tmp.get(i) - 1; 
                    cont = getYPos(x2, y1, y2, cont, matrix);
                    x2--;
                break;
                case 3:
                    x1 = x2;
                    x2 = x1 - (tmp.get(i) - 1); 
                    cont = getXNeg(x1, y2, x2, cont, matrix);
                    y2--;
                break;
                case 4:
                    y1 = y2;
                    y2 = y1 - (tmp.get(i) - 1); 
                    cont = getYNeg(x2, y1, y2, cont, matrix);
                    x1 = x2+1;
                    y1 = y2;
                    shifts=0;
                break;
            }
        }
        return matrix;
    }

        private int getXPos(int x, int y, int run, int cont, int[][] matrix){
             for(int i=x; i<=run; i++){
                matrix[y][i] = cont++;
             }
             return cont;
        }

        private int getYPos(int x, int y, int run, int cont, int[][] matrix){
             for(int i=y; i<=run; i++){
                  matrix[i][x] = cont++;
             }
             return cont;
        }

        private int getXNeg(int x, int y, int run, int cont, int[][] matrix){
             for(int i=x; i>=run; i--){
                  matrix[y][i] = cont++;
             }
             return cont;
        }

        private int getYNeg(int x, int y, int run, int cont, int[][] matrix){
             for(int i=y; i>=run; i--){
                  matrix[i][x] = cont++;
             }
             return cont;
        }










}