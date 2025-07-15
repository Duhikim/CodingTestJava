package CodingTestStudy.TwoDimensionCoinFlip;

public class Solution {
    public int solution(int[][] beginning, int[][] target) {
        boolean[][] matrix = comparison(beginning, target);
        boolean[][] copyMat = copyMatrix(matrix);

        // 먼저 되는지 안되는지 확인.
        int discriminant = flipAll(copyMat);
        if(discriminant == -1) return -1;

        int answer = 0;

        // 1열을 뒤집었을 때 이득인가?
        int firstColBenefit = 0; // (뒤집었을 때의 이득이므로 뒤집는 비용 -1로 시작)
        for(int i=1; i< matrix.length; i++){
            if(!matrix[i][0]) firstColBenefit++; // false -> true가 되면 이득 +1
            else firstColBenefit--; // true -> false가 되면 -1
        }

        // 1행을 뒤집었을 때 이득인가?
        int firstRowBenefit = 0;
        for(int j=1; j< matrix[0].length; j++){
            if(!matrix[0][j]) firstRowBenefit++;
            else firstRowBenefit--;
        }

        if(!matrix[0][0]){
            //0, 0이 false면, 가로 세로 중 이득이 더 큰 쪽으로 뒤집으면 됨. 이득이 음수여도 뒤집어야 함.
            if(firstColBenefit >= firstRowBenefit){
                flipOneLine(matrix, -1, 0);
            } else flipOneLine(matrix, 0, -1);
            answer += 1 + flipAll(matrix);
        }
        else{
            firstRowBenefit++; firstColBenefit++;
            if(firstColBenefit + firstRowBenefit -2 <= 0) return discriminant;
            else{
                flipOneLine(matrix, -1, 0);
                flipOneLine(matrix, 0, -1);
                answer += 2 + flipAll(matrix);
            }
        }

        return Math.min(answer, discriminant);
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

    public int flipAll(boolean[][] mat){
        int answer = 0;

        // 1열에 false가 있으면 그 행을 뒤집는다.
        for(int i=0; i<mat.length; i++){
            if(!mat[i][0]) {
                flipOneLine(mat, i, -1);
                answer++;
            }
        }
        for(int j=0; j<mat[0].length; j++){
            if(!mat[0][j]) {
                flipOneLine(mat, -1, j);
                answer++;
            }
        }
        for(int i=0; i<mat.length; i++) for(int j=0; j<mat[0].length; j++){
            if(!mat[i][j]) return -1;
        }
        return answer;
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

    boolean[][] copyMatrix(boolean[][] mat){
        boolean[][] copyMat = new boolean[mat.length][mat[0].length];

        for(int i=0; i< mat.length; i++)for(int j=0; j<mat[0].length; j++)
            copyMat[i][j] = mat[i][j];
        return copyMat;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] beginning =
                {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
//                {{1, 0, 0, 0, 0}, {0, 1, 1, 1, 1}, {0, 1, 1, 1, 1}};
        int[][] target =
                {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
//                {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        sol.solution(beginning, target);

    }

}
