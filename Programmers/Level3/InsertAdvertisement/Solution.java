package CodingTestStudy.Level3.InsertAdvertisement;

/*
logs를 분해해서 시작시간과 끝시간으로 나누자. 그리고 그냥 초로 환산해서 넣는다.
시작인지 끝인지 구분하기위해 크기2 배열로 만들어서 두번째 칸에 1과 -1로 구분한다.
예제 1을 예로들면 01:20:15-01:45:14 이것을 [4815, 1], [6314, -1]으로 변환해서 저장한다.
모든 log를 저장하면 시간기준으로 오름차 정렬.
처음부터 탐색하면서 초단위 시청자수를 저장.
logs는 최대 30만가지로 여기까지는 시간문제가 없음.

그다음부터 슬라이딩윈도우같이 하면 될것같음.
*/
import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        long maxManWatch = 0;
        int maxWatchStart = 0;
        int len = logs.length;

        int fullPlayTime = toSec(play_time);
        int fullAdvTime = toSec(adv_time);
        if(fullPlayTime == fullAdvTime) return "00:00:00";

        int[] watchers = new int[fullPlayTime+1];
        int[][] logs_ = new int[len*2][2];

        // String 타입의 로그를 정수(초)와 시청시작(+1), 시청종료(-1)로 변환 후 시간순서로 정렬
        for(int i=0; i<len; i++){
            String[] time = logs[i].split("-");
            logs_[2*i][0] = toSec(time[0]);
            logs_[2*i][1] = 1; // 시청 시작
            logs_[2*i+1][0] = toSec(time[1]);
            logs_[2*i+1][1] = -1; // 시청 끝
        }
        Arrays.sort(logs_, (a, b)->a[0]-b[0]);

        // 초단위로 시청자수 기록
        int logIdx = 0; // logs_의 인덱스
        for(int s=0; s<=fullPlayTime; s++){
            watchers[s] = (s > 0 ? watchers[s-1] : 0);
            while (logIdx < len*2 && logs_[logIdx][0] == s) {
                watchers[s] += logs_[logIdx][1];
                logIdx++;
            }
        }

        // 슬라이딩 윈도우
        int L = 0;
        int R = 0;
        long manWatch = 0; // 곱하기로 누적되니까 오버플로우 확인
        // 최대 300,000명 시청하고 최대 100시간=360,000초 두 값 곱하면 1.08 * 10^11, long타입 써야함

        // 0초부터 fullAdvTime까지먼저
        for(int i=0; i<fullAdvTime; i++){
            manWatch += watchers[i];
        } maxManWatch = manWatch;

        for(int i=fullAdvTime; i<=fullPlayTime; i++){
            manWatch += (watchers[i]-watchers[i-fullAdvTime]);
            if(manWatch > maxManWatch){
                maxManWatch = manWatch;
                maxWatchStart = i-fullAdvTime+1;
            }
        }

        return toString(maxWatchStart);
    }

    public int toSec(String time){
        String[] str = time.split(":");
        int h = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int s = Integer.parseInt(str[2]);
        return 3600*h + 60*m + s;
    }

    public String toString(int time){
        String s = Integer.toString(time%60);
        if(s.length()==1) s = "0"+s;
        time /= 60;
        String m = Integer.toString(time%60);
        if(m.length()==1) m = "0"+m;
        time /= 60;
        String h = Integer.toString(time);
        if(h.length()==1) h = "0"+h;
        return h+":"+m+":"+s;
    }
    public String toString(long time){
        String s = Long.toString(time%60);
        if(s.length()==1) s = "0"+s;
        time /= 60;
        String m = Long.toString(time%60);
        if(m.length()==1) m = "0"+m;
        time /= 60;
        String h = Long.toString(time);
        if(h.length()==1) h = "0"+h;
        return h+":"+m+":"+s;
    }
}