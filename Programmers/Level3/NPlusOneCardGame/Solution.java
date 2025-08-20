package CodingTestStudy.Level3.NPlusOneCardGame;

/*
손패를 Set에 저장.
킵패를 Set에 저장
새로운 카드 두 장이 들어오면, 목표값(n+1) - 새카드값 = 필요한카드값 에서 필요한카드값이 손패 혹은 킵패에 있는지 확인
손패에 있으면 코인 하나 주고 가져와서 라운드 넘긴다.
킵패에있으면 코인 두개를 주고 둘다 가져와서 라운드를 넘긴다.

게임 종료 조건: 바닥패가 없거나, 손패로 메이드를 못하는데 동전이 없거나

손패에 목표값이 있으면 life로 카운트해보자.
*/

import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length; // n이 6의 배수이기 때문에
        int t = n+1; // t는 항상 홀수. 즉 같은카드 두장으로 목표를 만드는건 불가능
        int life = 0;
        Set<Integer> hand = new HashSet<>();
        Set<Integer> keep = new HashSet<>();
        Deque<Integer> cardsDq = new ArrayDeque<>();

        for(int i=0; i<n/3; i++){
            hand.add(cards[i]);
        } for(int i=n/3; i<n; i++){
            cardsDq.add(cards[i]);
        }

        Iterator<Integer> it = hand.iterator();
        Set<Integer> toBeDel = new HashSet<>();
        while(it.hasNext()){
            int num = it.next();
            if(hand.contains(t-num)){
                toBeDel.add(num);
                toBeDel.add(t-num);
            }
        }
        life += toBeDel.size()/2;
        it = toBeDel.iterator();
        while(it.hasNext()) hand.remove(it.next());

        while(true){
            answer++;
            if(cardsDq.isEmpty()) break;

            int pick1 = cardsDq.poll();
            int pick2 = cardsDq.poll();

            // 핸드덱 비교를 먼저 하고 킵덱을 봐야함. 킵덱은 메이드시 코스트가 크기때문.
            // 어차피 중복원소는 없기에 메이드 안되면 버리는패임. 코스트 작은게 무조건 이득
            if(hand.contains(t - pick1) && coin>0){
                coin--;
                life++;
                hand.remove(t - pick1);
            } else keep.add(pick1);

            if(hand.contains(t - pick2) && coin>0){
                coin--;
                life++;
                hand.remove(t - pick2);
            } else keep.add(pick2);

            if(life > 0) {
                life--;
                continue;
            }
            if(coin < 2) break;

            it = keep.iterator();
            while(it.hasNext()){
                int num = it.next();
                if(keep.contains(t-num)){
                    life++;
                    coin -= 2;
                    keep.remove(num);
                    keep.remove(t-num);
                    break;
                }
            }
            if(life > 0) {
                life--;
                continue;
            }
            break;
        }

        return answer;
    }
}