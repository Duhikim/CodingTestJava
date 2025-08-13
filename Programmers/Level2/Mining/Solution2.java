package CodingTestStudy.Level2.Mining;

import java.util.ArrayList;
import java.util.Collections;

class Solution2 {
    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = 	{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(solution(picks, minerals));

        picks = new int[]{0,1,1};
        minerals = new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(solution(picks, minerals));
    }

    public static int solution(int[] picks, String[] minerals) {
        // 만약 곡괭이 개수가 필요보다 많으면 돌부터 버리는 작업을 함.
        int requiredAxe = (((minerals.length-1) / 5) + 1);
        int numOfAxes = picks[0]+picks[1]+picks[2];
        if(numOfAxes > requiredAxe){
            int gap = numOfAxes - requiredAxe;
            while(gap-- > 0){
                if(picks[2]>0) picks[2]--;
                else if(picks[1]>0) picks[1]--;
                else if(picks[0]>0) picks[0]--;
            }
        }
        // 만약 곡괭이로 캘 수 있는 광맥보다 양이 많다면 뒤쪽 광맥은 삭제해도 됨.
        if(minerals.length > numOfAxes*5){
            String[] minerals_resize = new String[numOfAxes*5];
            //for(int i=0; i<numOfAxes*5; i++){ minerals_resize[i] = minerals[i]; } // 아랫줄이랑 똑같은 뜻이래
            if (numOfAxes * 5 >= 0) System.arraycopy(minerals, 0, minerals_resize, 0, numOfAxes * 5);
            minerals = minerals_resize;
            requiredAxe = minerals.length /5;
        }

        // 다이아, 아이언, 스톤 개수를 D, I, S라고 하면,
        // 돌 곡괭이를 쓸 경우 25D + 5I + S
        // 철 곡괭이를 쓸 경우 5D + I + S       위보다 20D + 4I만큼 피로도 세이브
        // 다 곡괭이를 쓸 경우 D + I + S        위보다 4D 만큼 피로도 세이브       돌보다 24D + 4I 만큼 피로도 세이브
        // 다이아가 한개라도 많은 덩어리는 다이아로 캐는게 이득.
        // 예를들어 DDSSS를 다이아로 캐는거보다 DIIII를 다이아로 캐는게 피로도가 더 세이브 된다.
        // 그리고 D가 한개라도 높으면 첫 피로도도 높을 수 밖에 없음.
        // 예를들어 다이아, 철, 돌의 개수가 A, B, (5-A-B)라고 했을 떄, 피로도는 25A + 5B + (5-A-B) (A는 1 이상)
        // 다이아 개수를 1개 줄인거 중에 가장 큰것은 나머지를 다 철로 바꾼 것이다.
        // 그것의 피로도는 25(A-1) + 5(B+1+5-A-B) = 20A + 5. A에 1이상의 몇이 들어가든 위보다 작다.
        // 피로도가 높을 수록 다이아를 쥐어주면 된다.

        // 광맥을 다섯 개씩 묶어서 클래스로 만들 것임.
        ArrayList<Mine> mineList = new ArrayList<Mine>();
        for(int i=0; i<requiredAxe; i++){
            Mine mine = new Mine();
            for(int j=i*5; j<(i+1)*5; j++){
                if(j >= minerals.length) break;
                switch(minerals[j]){
                    case "diamond": mine.D++; break;
                    case "iron": mine.I++; break;
                    case "stone": mine.S++; break;
                }
            }
            mine.Exhaustion = 25*mine.D + 5*mine.I + mine.S;
            mineList.add(mine);
        }
        Collections.sort(mineList);

        for(Mine mine : mineList){
            if(picks[0]>0 && mine.D!=0) {
                picks[0]--;
                mine.Exhaustion = mine.D + mine.I + mine.S;
            }
            else if(picks[1]>0 && mine.I!=0) {
                picks[1]--;
                mine.Exhaustion = 5*mine.D + mine.I + mine.S;
            }
            else {
                if(picks[0]>0){
                    picks[0]--;
                    mine.Exhaustion = mine.D + mine.I + mine.S;
                }
                else if(picks[1]>0){
                    picks[1]--;
                    mine.Exhaustion = 5*mine.D + mine.I + mine.S;
                }
                else picks[2]--;
            }
        }

        return mineList.stream()
                .mapToInt(mine -> mine.Exhaustion)
                .sum();
    }
}

class Mine implements Comparable<Mine>{

    public int D = 0;
    public int I = 0;
    public int S = 0;
    public int Exhaustion = 0;
    public String Axe = "";

    @Override
    public int compareTo(Mine mine){
        return (mine.Exhaustion - this.Exhaustion);
    }
}