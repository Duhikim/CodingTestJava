package CodingTestStudy.TwoDimensionCoinFlip;

public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] beginning =
                {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
//                {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//                {{0,0,1,0,0},{1,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        int[][] target =
                {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
//                {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}};
//                {{0,1,0,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0},{1,0,0,0,0}};

        System.out.println(sol.solution(beginning, target));

    }

    public int solution(int[][] beginning, int[][] target) {
        boolean[][] discriminants = comparison(beginning, target);

        return flip(discriminants);
    }

    public boolean[][] comparison(int[][] mat1, int[][] mat2){
        int row = mat1.length;
        int col = mat1[0].length;
        boolean[][] result = new boolean[row][col];

        for(int r = 0; r < row; r++) for(int c = 0; c < col; c++) {
            result[r][c] = mat1[r][c] == mat2[r][c];
        }

        return result;
    }

    public int flip (boolean[][] mat){
        int row = mat.length;
        int col = mat[0].length;
        int answer = Integer.MAX_VALUE;

        for(int i=0; i<power(2,row); i++){
            boolean[][] copyMat = copyMatrix(mat);
            int counter = 0;
            int exp = 0;

            while(exp < row){
                if((i & power(2,exp)) != 0) {
                    flipOneLine(copyMat, exp, -1);
                    counter++;
                }
                exp++;
            }

            for (int c = 0; c < col; c++) {
                if (!copyMat[0][c]) {
                    flipOneLine(copyMat, -1, c);
                    counter++;
                }
            }
            if(counter<answer && allTrue(copyMat)) answer = counter;
        }
        return (answer==Integer.MAX_VALUE)? -1: answer;
    }

    public void flipOneLine(boolean[][] mat, int row, int col){

        if(row == -1){
            for(int r=0; r<mat.length; r++){
                mat[r][col] = !mat[r][col];
            }
        }
        else if (col == -1){
            for(int c=0; c<mat[0].length; c++){
                mat[row][c] = !mat[row][c];
            }
        }
        else System.out.println("Not available");
    }

    public boolean allTrue(boolean[][] mat){
        for(boolean[] arr: mat){
            for(boolean b: arr) if(!b) return false;
        } return true;
    }

    public boolean[][] copyMatrix(boolean[][] mat){
        boolean[][] result = new boolean[mat.length][mat[0].length];
        for(int i=0; i<mat.length; i++) for(int j=0; j<mat[0].length; j++){
            result[i][j] = mat[i][j];
        } return result;
    }

    public int power(int a, int b){
        int result = 1;
        int base = a;
        while(b>0){
            if((b&1) == 1)result *= base;
            base *= base;
            b >>= 1;
        }
        return result;
    }

}