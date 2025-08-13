package CodingTestStudy.Level3.MultiplicationTable;

import java.util.Arrays;

public class Solution_ver2 {
/************************
 * 억억단은 1억 x 1억 크기의 행렬입니다. 억억단을 외우던 영우는 친구 수연에게 퀴즈를 내달라고 부탁하였습니다.
 * 수연은 평범하게 문제를 내봐야 영우가 너무 쉽게 맞히기 때문에 좀 어렵게 퀴즈를 내보려고 합니다.
 * 적당한 수 e를 먼저 정하여 알려주고 e 이하의 임의의 수 s를 여러 개 얘기합니다.
 * 영우는 각 s에 대해서 s보다 크거나 같고 e 보다 작거나 같은 수 중에서 억억단에서 가장 많이 등장한 수를 답해야 합니다.
 * 만약 가장 많이 등장한 수가 여러 개라면 그 중 가장 작은 수를 답해야 합니다.
 * 수연은 영우가 정답을 말하는지 확인하기 위해 당신에게 프로그램 제작을 의뢰하였습니다.
 * e와 s의 목록 starts가 매개변수로 주어질 때 각 퀴즈의 답 목록을 return 하도록 solution 함수를 완성해주세요.

 <제한 사항>
 * 1 ≤ e ≤ 5,000,000
 * 1 ≤ starts의 길이 ≤ min {e,100,000}
 * 1 ≤ starts의 원소 ≤ e
 * starts에는 중복되는 원소가 존재하지 않습니다.
 
 <입출력 예>
 e	starts	result
 8	[1,3,7]	[6,6,8]
 
 
 1억 = e+8
 *************************/

public static void main(String[] args) {
    Solution_ver2 sol = new Solution_ver2();
    int e = 8;
    int[] starts = {1,3,7,4,6,8,9};
    int[] result = {6,6,8};
    int[] calculated = sol.solution(5000000, starts);
    for(int i: calculated) {
        System.out.print(i + " ");
    }
}

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        
        int[][] table = new int[e+1][2]; // e개의 배열, 기본적으로 0으로 초기화.
        
        int div = 1;
        int discriminant = (e/div - (div-1)); // 판별식
        while(discriminant > 0 ){
            table[div*div][0] = div*div;
            table[div*div][1]++;
            for(int i=div+1; i<= e/div; i++){
                table[i*div][0] = i*div;
                table[i*div][1] += 2;
            }
            div++;
            discriminant = (e/div - (div-1));
        }
        Arrays.sort(table, (o1, o2)->
        {if(o2[1]==o1[1]) { return o1[0] - o2[0]; }
                    else { return o2[1] - o1[1];}}); // table[idx][1] 기준 내림차순
        
        for(int i=0; i<starts.length; i++){
            for(int j=0; j<table.length; j++){
                if(table[j][0] >= starts[i]){
                    answer[i] = table[j][0];
                    break;
                }
            }
        }
        
        
        
        return answer;
    }
}


