package CodingTestStudy.TwoDimensionCoinFlip;
// 비슷하게는 나오지만, 최저 카운트를 보장하지 못해서 일부 테스트케이스 실패


public class FailedCode {

    public int solution(int[][] beginning, int[][] target) {
        boolean[][] discriminants = comparison(beginning, target);

        int answer = entireFlip(discriminants);
        if(answer == -1) return -1;

        return recount(discriminants);
    }

    boolean[][] comparison(int[][] mat1, int[][] mat2){
        int row = mat1.length;
        int col = mat1[0].length;
        boolean[][] result = new boolean[row][col];

        for(int r = 0; r < row; r++) for(int c = 0; c < col; c++) {
            result[r][c] = mat1[r][c] == mat2[r][c];
        }

        return result;
    }

    public int entireFlip(boolean[][] mat){

        int count = 0;

        int row = mat.length;
        int col = mat[0].length;
        // boolean[][] copyMat = mat.clone(); *** 2차원 배열은 깊은복사가 안됨. 수동으로 해야함
        boolean[][] copyMat = new boolean[row][col];
        for(int r=0; r<row; r++) for(int c=0; c<col; c++)copyMat[r][c] = mat[r][c];


        for(int r=0; r<row; r++){
            if(!copyMat[r][0]){
                count++;
                for(int c = 0; c<col; c++){
                    copyMat[r][c] = !copyMat[r][c];
                }
            }
        }
        for(int c=0; c<col; c++){
            if(!copyMat[0][c]){
                count++;
                for(int r = 0; r<row; r++){
                    copyMat[r][c] = !copyMat[r][c];
                    if(!copyMat[r][c]) return -1;
                }
            }
        }
        return count;
    }

    public int recount(boolean[][] mat){
        int row = mat.length;
        int col = mat[0].length;

        // mat[0][0]이 false면 0행 / 0열 중 한번만 건드려야 함.
        // 0행(가로)을 먼저 건드렸을때 이득은 0행의 false의 개수 - 0행의 true의 개수.
        // 0열(세로)을 먼저 건드렸을 때 이득은 0열의 false의 개수 - 0열의 true의 개수.
        // 두 개를 비교해서 이득이 큰쪽(둘다 음수일수도 있음)을 먼저 뒤집는다.

        // mat[0][0]이 true 라면 아예 안건드리거나 두 번 다 건드려야 함.
        // 마찬가지로 이득을 계산해서 가로이득+세로이득-2(두번 건드리는 비용)이 양수면 두번 건드리면 될듯.
        // 음수면 안건드리는게 낫고 0이면 하나 안하나 똑같음.
        int horBenefit = 0, verBenefit = 0;
        for(int c=1; c<col; c++){
            if(mat[0][c]) horBenefit--; // 기존에 참이면 건드릴경우 거짓이 되니까 이익 감소
            else horBenefit++; // 기존에 거짓이면 참이되니까 이익 증가
        }
        for(int r=1; r<row; r++){
            if(mat[r][0]) verBenefit--;
            else verBenefit++;
        }
        if(mat[0][0]) {
            if(horBenefit+verBenefit-2 > 0){ // mat[0][0]이 참이고 두번건드는게 이득이면
                return 2 + (col-horBenefit)/2 + (row-verBenefit)/2;
            }
            else return (col+horBenefit)/2 + (row+verBenefit)/2;
        }
        else{
            if(horBenefit >= verBenefit){
                return 1 + (col - horBenefit)/2 + (row + verBenefit)/2;
            }
            else return 1 + (col + horBenefit)/2 + (row - verBenefit)/2;
        }
    }

}
