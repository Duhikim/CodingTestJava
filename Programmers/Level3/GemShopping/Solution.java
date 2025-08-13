package CodingTestStudy.Level3.GemShopping;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {

    // 0. 모든 보석 종류를 Set에 담음
    // 1. 기본적으로 [1, N] 안에는 모든 보석 종류가 들어감.
    // 2. 윈도우 크기를 반으로 줄여서 gems 탐색
    // 3-1. 만약 반으로 줄여도 모든 보석 종류가 들어가는 윈도우가 있으면 또 반으로 줄임.
    // 3-2. 만약 반으로 줄이니 모든 보석 종류가 안들어가면 윈도우크기를 늘림.
    // 4. 그렇게 가장 작은 윈도우를 찾고나면, 그 크기로 index 작은쪽부터 탐색하며 만족하는 순간 그 값을 반환.

    // 갑자기 다른아이디어가 생각남.
    // gems 기준 0부터 n까지 보석종류들을 모두 카운트 해서 map에 넣음.
    // 만약 map중에 0이 있으면, 즉 포함 안된 보석이 있으면 n+1항을 추가. 그다음 n+2항 추가.. 계속
    // 그러다가 map에 0이 모두 없어졌으면, 일단 answer에 그 상태 저장.
    // 이제 시작인덱스(처음에 0)부터 map에서 하나씩 지워나감.
    // 지운 항목이 0이되면, 지우기 직전상황을 answer에 저장. (전 answer랑 비교해서 크기가 작아야 함)
    // 다시 뒤쪽을 늘려나감. map에 모든 항목이 1 이상이 되면 또 스탑. answer랑 비교
    // 다시 앞쪽을 줄여나감.
    // 이런식으로 늘려나가던 쪽이 끝까지 갔는데도 map에 0이 있으면 종료.

    int N; // gmes 배열의 크기
    int n; // typesOfGems 조합의 크기
    Map<String, Integer> gemsCount;
//    Set<String> typesOfGems;


    public int[] solution(String[] gems) {
        gemsCount = Arrays.stream(gems)
                .distinct()
                .collect(Collectors.toMap(gem -> gem, gem -> 0));

        N = gems.length;
        n = gemsCount.size();
        int[] answer = new int[]{1, N};

        int zerosInMap = n;
        int savedLength = N;
        int left = 0, right = 0;

        while(right < gems.length){
            if(gemsCount.get(gems[right]) == 0) zerosInMap--;
            gemsCount.put(gems[right], gemsCount.get(gems[right])+1);
            right++;

            while(zerosInMap==0){
                if(right-left < savedLength){
                    savedLength = right-left;
                    answer[0] = left+1;
                    answer[1] = right;
                }

                if(gemsCount.get(gems[left]) == 1) zerosInMap++;
                gemsCount.put(gems[left], gemsCount.get(gems[left])-1);

                left++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String[][] gems = {
                {"AA", "AB", "AC", "AA", "AC"},
                {"XYZ", "XYZ", "XYZ"},
                {"ZZZ", "YYY", "NNNN", "YYY", "BBB"},
                {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}
        };
        int tc = 0;
        int[] result = sol.solution(gems[tc]);
        System.out.println("["+result[0] + " , " + result[1] + "]");

    }




//    public int searchWithWindow(String[] gems, int lenOfWindow){
//        for(int i=0; i<= gems.length - lenOfWindow; i++){
//            Set<String> set = new HashSet<>();
////            set.addAll(Arrays.asList(Arrays.copyOfRange(gems, i, i+lenOfWindow))); // 중간에 List를 거치기때문에 비효율적일거같음. 테스트는 안해봄
//            for(int j=i; j<i+lenOfWindow; j++){
//                set.add(gems[j]);
//            }
//            if(set.size() == n) return i;
//        }
//        return -1;
//    }
}
