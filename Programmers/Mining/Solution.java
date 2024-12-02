package CodingTestStudy.Mining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

/**************
 1. 제한사항이 짧기 때문에 모든 상황을 다 해봐도 됨.
    깊이우선탐색을 이용해서 끝까지 도착 후 피로도를 계산.
    피로도의 최소값을 반환.
 2. 머리써서 더 빠르게 할 수 있을듯.
    5개씩 광맥을 묶어서 점수를 매김.
    점수가 높은 광맥부터 좋은 곡괭이를 소비하는 방식.
    맞을지 점검이 필요하긴 함.
 **************/

class Solution {
    public static void main(String[] args) {
        int[] picks = {1, 3, 2};
        String[] minerals = 	{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        int ans1 = (solution(picks, minerals));
        int ans2 = (solution2(picks, minerals));
        if(ans1 == ans2) System.out.println("{ Correct : " + ans1 + " }");
        else System.out.println("{ Wrong : " + ans1 + " , " + ans2 + " }");

        picks = new int[]{0,1,1};
        minerals = new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        ans1 = (solution(picks, minerals));
        ans2 = (solution2(picks, minerals));
        if(ans1 == ans2) System.out.println("{ Correct : " + ans1 + " }");
        else System.out.println("{ Wrong : " + ans1 + " , " + ans2 + " }");

        Random random = new Random();

        int count = 1;
        while(count-- > 0) {
            picks = new int[]{random.nextInt(6), random.nextInt(6), random.nextInt(6)};
            int size = random.nextInt(46) + 5;
            minerals = new String[size];
            for (String str : minerals) {
                int rand = random.nextInt(3);
                switch (rand) {
                    case 0:
                        str = "diamond";
                        break;
                    case 1:
                        str = "iron";
                        break;
                    case 2:
                        str = "stone";
                        break;
                }
            }
            ans1 = (solution(picks, minerals));
            ans2 = (solution2(picks, minerals));
            if(ans1 == ans2) System.out.println("{ Correct : " + ans1 + " }");
            else {
                System.out.println("{ Wrong : " + ans1 + " , " + ans2 + " }");
                ArrayList<Integer> new_picks
                        = Arrays.stream(picks)
                            .boxed()
                            .collect(Collectors.toCollection(ArrayList::new));
                ArrayList<String> new_minerals
                        = Arrays.stream(minerals)
                        .collect(Collectors.toCollection(ArrayList::new));
                System.out.println(new_picks);
                System.out.println(new_minerals);
                return;
            }
        }
    }

    public static int solution2(int[] picks, String[] minerals) {
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

    public static int solution(int[] picks, String[] minerals) {
        // 만약 곡괭이 개수가 필요보다 많으면 돌부터 버리는 작업을 함.
        int requiredAxe = (minerals.length % 5 == 0)? (minerals.length / 5) : ((minerals.length / 5) + 1);
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
            for(int i=0; i<numOfAxes*5; i++){
                minerals_resize[i] = minerals[i];
            }
            minerals = minerals_resize;
            requiredAxe = minerals.length /5;
        }
        // 광맥을 다섯 개씩 묶는 작업을 하겠음. 필요한 곡괭이 개수랑 같은 수가 될 듯.
        ArrayList<ArrayList<String>> mineralSets = new ArrayList<>();
        for(int i=0; i<requiredAxe; i++){
            ArrayList<String> mineralSet = new ArrayList<>();
            for(int j=i*5; j<(i+1)*5; j++){
                if(j >= minerals.length) mineralSet.add("");
                else mineralSet.add(minerals[j]);
            }
            mineralSets.add(mineralSet);
        }

        int[] tired_min = {0};
        DFSearch(picks, mineralSets, 0, 0, tired_min);

        return tired_min[0];
    }

    public static void DFSearch(int[] picks, ArrayList<ArrayList<String>> mineralSets, int lumps, int tired, int[] tired_min){
        if(lumps == mineralSets.size()){
            if(tired_min[0]==0 || tired< tired_min[0]) {
                tired_min[0] = tired;
                return;
            }
        }
        if(tired_min[0]!=0 && tired>tired_min[0]) return;

        for(int i=0; i<3; i++){
            //i=0이면 다이아, 1이면 철, 2면 돌 곡괭이 사용.
            if(picks[i] > 0){
                picks[i]--;
                // 피로도 처리
                int temp = getTired(mineralSets.get(lumps), i);
                // 탐색 한단계 더 들어가기
                DFSearch(picks, mineralSets, lumps+1, tired+temp, tired_min);
                picks[i]++;
            }
        }
    }
    public static int getTired(ArrayList<String> mineralSet, int Axe){
        int dia =0, iron =0, stone =0;
        for(String mineral : mineralSet){
            switch (mineral) {
                case "diamond" -> dia++;
                case "iron" -> iron++;
                case "stone" -> stone++;
            }
        }
        return switch (Axe) {
            case 0 -> dia + iron + stone;
            case 1 -> dia * 5 + iron + stone;
            case 2 -> dia * 25 + iron * 5 + stone;
            default -> -1;
        };
    }
}