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

    Map<Integer, List<Order>> menuOrders = new HashMap<>();
    Map<Integer, List<int[]>> menuPrefix = new HashMap<>();

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
