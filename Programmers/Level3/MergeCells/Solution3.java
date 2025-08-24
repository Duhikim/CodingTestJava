package CodingTestStudy.Level3.MergeCells;

import java.util.*;

class Solution3 {
    int[] parent = new int[51*51];
    String[] value = new String[51*51];

    public int getIdx(int r, int c){
        return 51*r + c;
    }

    public int find(int n){
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    public void union (int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA==rootB) return;

        parent[rootB] = rootA;

        String valA = value[rootA];
        String valB = value[rootB];
        if(valA.isEmpty() && !valB.isEmpty()){
            value[rootA] = valB;
        }
        value[rootB] = "";
    }




    public String[] solution(String[] commands) {
        for(int i=0; i<51*51; i++){
            parent[i] = i;
            value[i] = "";
        }

        List<String> print = new ArrayList<>();

        for(String cmd: commands){
            String[] str = cmd.split(" ");
            if(str[0].equals("UPDATE")){
                if(str.length == 4){
                    int r = Integer.parseInt(str[1]);
                    int c = Integer.parseInt(str[2]);
                    String nVal = str[3];
                    int idx = getIdx(r, c);
                    int pIdx = find(idx);

                    value[pIdx] = nVal;

                }else{
                    String val1 = str[1];
                    String val2 = str[2];
                    if(val1.equals(val2)) continue;

                    for(int i=52; i<51*51; i++){
                        int pIdx = find(i);
                        if(pIdx != i) continue;
                        if(value[pIdx].equals(val1)) value[pIdx] = val2;
                    }
                }

            } else if(str[0].equals("MERGE")){
                int r1 = Integer.parseInt(str[1]);
                int c1 = Integer.parseInt(str[2]);
                int r2 = Integer.parseInt(str[3]);
                int c2 = Integer.parseInt(str[4]);

                int idx1 = getIdx(r1, c1);
                int idx2 = getIdx(r2, c2);

                union(idx1, idx2);

            } else if(str[0].equals("UNMERGE")){
                int r = Integer.parseInt(str[1]);
                int c = Integer.parseInt(str[2]);
                int idx = getIdx(r, c);
                int pIdx = find(idx);

                String val = value[pIdx];
                List<Integer> group = new ArrayList<>();

                for(int i=52; i < 51*51; i++){
                    if(find(i) == pIdx) group.add(i);
                }

                for(int cell: group){
                    parent[cell] = cell;
                    value[cell] = "";
                }
                value[idx] = val;

            } else if(str[0].equals("PRINT")){
                int r = Integer.parseInt(str[1]);
                int c = Integer.parseInt(str[2]);
                int idx = getIdx(r, c);
                int pIdx = find(idx);
                String val = value[pIdx];
                if(val.isEmpty()) print.add("EMPTY");
                else print.add(val);
            } else{

            }


        }

        return print.toArray(new String[0]);
    }
}