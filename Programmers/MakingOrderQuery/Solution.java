/*
어떤 음식점에 1부터 N까지 N-1개의 메뉴가 있다. (N의 최대값 10만)
모든 메뉴의 하위 옵션역시 1부터 N까지 N-1개가 있다.

input으로 들어오는 2차원 배열 orders는 크기가 M이고 한 요소에는 다음과 같은 정보가 들어있다.
[메뉴 번호, 옵션 번호, 가격, 이 조합의 주문 횟수]
(M의 최대값 10만)

그리고 쿼리가 들어오는데 쿼리의 크기는 L이며 한 요소에 다음과 같은 요구사항이 들어있다.
[메뉴 번호, 최대 가격]
(L의 최대값 10만)

메뉴 번호가 1~N이면 해당 메뉴를 고정해야 한다. 메뉴 번호가 0이면 메뉴는 상관 없다.
최대 가격을 초과하는 주문내역은 통계 자체에서 빠진다.
만약 최대가격이 0으로 들어오면 가격은 상관 없다는 의미이다.

이제 쿼리에 따라 추천 조합을 제공해야 한다. 전략은 다음과 같다.
1. 쿼리에 메인메뉴가 없다면 일단 메인메뉴를 정해야 한다.
    메인 메뉴는 옵션 상관없이 가장 주문수가 많은 메뉴로 정해진다.
    만약 가장 주문수가 많은 메뉴가 여러개면 숫자가 가장 작은 것으로 한다.
2. 메인메뉴가 정해지면 옵션을 정해야 한다.
    옵션은 정해진 메인메뉴를 시킨 주문내역 중 주문수가 가장 많은 옵션으로 정해진다.
    주문수가 많은 옵션이 여러개면 숫자가 가장 작은 것으로 한다.

이렇게 쿼리와 같은 크기에 요소는 각 쿼리에 대해 추천하는 [메뉴번호, 옵션번호]가 들어가는 2차원 배열을 반환한다.

 */


package CodingTestStudy.MakingOrderQuery;

import java.util.*;
public class Solution {
    class Order{
        int menuId;
        int optId;
        int price;
        int count;
        public Order(int m, int o, int p, int c){
            this.menuId = m;
            this.optId = o;
            this.price = p;
            this.count = c;
        }
    }

    Map<Integer, List<Order>> menuOrders = new HashMap<>(); // Key : 메뉴, Value : 그 메뉴를 시킨 주문내역 리스트
    Map<Integer, List<int[]>> menuPrefix = new HashMap<>(); // Key : 메뉴, Value :

    List<Order> globalOrders = new ArrayList<>();
    List<int[]> globalPrefix;

    public int[][] solution(int[][] orders, int[][] queries){
        for(int[] order: orders){
            Order o = new Order(order[0], order[1], order[2], order[3]);
            if(!menuOrders.containsKey(order[0]))
                menuOrders.put(order[0], new ArrayList<>());
            menuOrders.get(order[0]).add(o);
            globalOrders.add(o);
        }

        for(Map.Entry<Integer, List<Order>> e: menuOrders.entrySet()){
            List<Order> list = e.getValue();
            list.sort((a, b)->a.price - b.price);
            menuPrefix.put(e.getKey(), buildPrefix(list));
        }

        globalOrders.sort((a, b) -> a.price - b.price);
        globalPrefix = buildPrefix(globalOrders);

        int[][] ans = new int[queries.length][2];
        for(int i=0; i< queries.length; i++){
            int menu = queries[i][0];
            int price = queries[i][1];
            List<int[]> prefix;
            if(menu==0) prefix = globalPrefix;
            else prefix = menuPrefix.getOrDefault(menu, new ArrayList<>());

            int[] res = search(prefix, price);
            ans[i][0] = (res==null)? -1 : res[0];
            ans[i][1] = (res==null)? -1 : res[1];
        }

        return ans;
    }

    public List<int[]> buildPrefix(List<Order> list){
        List<int[]> prefix = new ArrayList<>();
        int maxCount = -1;
        int[] best = null;
        for(Order o: list){
            if(o.count > maxCount) {
                maxCount = o.count;
                best = new int[]{o.menuId, o.optId};
            }
            prefix.add(new int[]{o.price, best[0], best[1], maxCount});
        }
        return prefix;
    }
    public int[] search(List<int[]> prefix, int price){
        if(prefix.isEmpty()) return null;
        if(price==0)
            return new int[]{prefix.get(prefix.size()-1)[1], prefix.get(prefix.size()-1)[2]};
        int lo = 0, hi = prefix.size()-1, pos = -1;
        while(lo <= hi){
            int mid = (lo+hi)/2;
            if(prefix.get(mid)[0] <= price) { pos = mid; lo = mid+1; }
            else hi = mid - 1;
        }
        if(pos == -1) return null;
        return new int[]{prefix.get(pos)[1], prefix.get(pos)[2]};
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] orders = {
                {1,1,100,5},
                {1,2,200,7},
                {1,3,300,6},
                {2,1,150,10},
                {2,2,250,8}
        };

        int[][] queries = {
                {1,250}, // 메뉴1, 옵션2
                {1,150}, // 메뉴1, 옵션1
                {2,0}, // 메뉴2, 옵션1
                {0, 200}, // 메뉴1, 옵션2
                {0,0} // 메뉴1, 옵션2
        };
        int[][] result = sol.solution(orders, queries);
        for (int i = 0; i < result.length; i++) {
            System.out.printf("Query %d => [menu=%d, option=%d]\n",
                    i+1, result[i][0], result[i][1]);
        }
    }
}
