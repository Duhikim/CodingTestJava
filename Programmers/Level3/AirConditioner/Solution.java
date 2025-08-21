package CodingTestStudy.Level3.AirConditioner;

/*
사실 희망온도는 의미가 없다.
단순히 운전 모드를 운전, 유지운전, 끄기 세가지로 나눠서 생각하면 된다.
10타임을 운전할 때 5타임 운전, 5타임 끄기 연속으로 하는것과 켰다껐다켰다껐다 5번 반복하는것에는 에너지 차이가 없다.
오히려 과냉되지 않는 후자가 이득. 즉 중간 과정은 굳이 고려하지 않아도 됨. (t1과 t2가 같을경우 고려해야하지만, 주어지지 않는다)

t1과 t2중 외기온도와 가까운 온도 하나만 쓸것이다.

아 근데 dp를 만들면 되지 않나
dp[m][t] = consumption을 저장해두면 전부 채운다고 해도 1000 x 50이 된다.

*/

import java.lang.Math;
/*
사실 희망온도는 의미가 없다.
단순히 운전 모드를 운전, 유지운전, 끄기 세가지로 나눠서 생각하면 된다.
10타임을 운전할 때 5타임 운전, 5타임 끄기 연속으로 하는것과 켰다껐다켰다껐다 5번 반복하는것에는 에너지 차이가 없다.
오히려 과냉되지 않는 후자가 이득. 즉 중간 과정은 굳이 고려하지 않아도 됨. (t1과 t2가 같을경우 고려해야하지만, 주어지지 않는다)

t1과 t2중 외기온도와 가까운 온도 하나만 쓸것이다.

아 근데 dp를 만들면 되지 않나
dp[m][t] = consumption을 저장해두면 전부 채운다고 해도 1000 x 50이 된다.

*/

import java.lang.Math;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int answer =  Integer.MAX_VALUE;
        int time = onboard.length;
        temperature += 10;
        t1 += 10;
        t2 += 10;
        int OAT = (temperature < t2)? t2+t1-temperature: temperature; // 이제 온도는 0~50도 사이이고 OAT는 t2보다 높다. 즉 모든 경우를 냉방으로 처리

        int[][] consumption = new int[time][100];
        for(int m = 0; m<time; m++) for(int t = 0; t<=99; t++){
            consumption[m][t] = Integer.MAX_VALUE;
        }
        consumption[0][OAT] = 0;

        for(int m = 0; m<time-1; m++){
            for(int t = 0; t<=99; t++){
                if(consumption[m][t] == Integer.MAX_VALUE) continue;
                int nextTemp = 0;
                int nextTime = m+1;
                // 끄기
                nextTemp = Math.min(OAT, t+1);
                if(
                        (onboard[nextTime] != 1) ||
                                (nextTemp >= t1 && nextTemp <= t2)){

                    consumption[nextTime][nextTemp]
                            = Math.min(consumption[nextTime][nextTemp], consumption[m][t]);

                }

                // 유지 운전
                nextTemp = t;
                if(
                        (onboard[nextTime] != 1) ||
                                (nextTemp >= t1 && nextTemp <= t2)){

                    consumption[nextTime][nextTemp]
                            = Math.min(consumption[nextTime][nextTemp], consumption[m][t]+b);

                }


                // 냉방 운전
                nextTemp = t-1;
                if(
                        nextTemp >= 0 &&
                                ((onboard[nextTime] != 1) ||
                                        (nextTemp >= t1 && nextTemp <= t2))){

                    consumption[nextTime][nextTemp]
                            = Math.min(consumption[nextTime][nextTemp], consumption[m][t]+a);

                }
            }
        }


        for(int t=0; t<=99; t++){
            if(consumption[consumption.length-1][t] == Integer.MAX_VALUE) continue;
            answer = Math.min(answer, consumption[consumption.length-1][t]);
        }



        return answer;
    }
}