package CodingTestStudy.Level2.MatrixMultiplication;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int r1 = arr1.length;
        int c1 = arr1[0].length;
        int r2 = arr2.length;
        int c2 = arr2[0].length;
        if(c1!=r2) {
            System.out.println("Incalculable expression");
            return null;
        }

        int[][] answer = new int[r1][c2];

        for(int i=0; i<r1; i++){ // 최대 100
            for(int j=0; j<c2; j++){ // 최대 100
                for(int k=0; k<c1; k++){ // 최대 100
                    answer[i][j] += arr1[i][k]*arr2[k][j];
                }
            }
        }

        return answer;
    }
}