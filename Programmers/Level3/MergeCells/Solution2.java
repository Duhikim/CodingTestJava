package CodingTestStudy.Level3.MergeCells;

import java.util.*;

class Solution2 {
    Map<String, Set<Node>> dictionary = new HashMap<>();
    Node[][] nodes = new Node[51][51];

    class Node {
        String value;
        Set<Integer> groupIds;

        public Node(int r, int c){
            this.value = "";
            this.groupIds = new HashSet<>();
            groupIds.add(51*r + c);
        }

    }

    public String[] solution(String[] commands) {
        List<String> print = new ArrayList<>();

        for(int i=1; i<=50; i++)for(int j=1; j<=50; j++){
            nodes[i][j] = new Node(i, j);
        }

        for(String cmd: commands){
            String msg = execute(cmd);
            if(msg != null) print.add(msg);
        }

        return print.toArray(new String[0]);
    }

    public String execute(String cmd){
        String result = new String();

        String[] str = cmd.split(" ");
        if(str[0].equals("UPDATE") && str.length==4){
            int r = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            String val = str[3];
            update(r, c, val);

        } else if(str[0].equals("UPDATE") && str.length==3){
            String val1 = str[1];
            String val2 = str[2];
            update(val1, val2);

        } else if(str[0].equals("MERGE")){
            int r1 = Integer.parseInt(str[1]);
            int c1 = Integer.parseInt(str[2]);
            int r2 = Integer.parseInt(str[3]);
            int c2 = Integer.parseInt(str[4]);
            merge(r1, c1, r2, c2);

        } else if(str[0].equals("UNMERGE")){
            int r = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            unmerge(r, c);

        } else if(str[0].equals("PRINT")){
            int r = Integer.parseInt(str[1]);
            int c = Integer.parseInt(str[2]);
            String val = nodes[r][c].value;
            return (val.isEmpty())? "EMPTY": val;

        } else{
            result = "Not Available Command";
            System.out.println(result);
            return result;
        }

        return null;
    }

    public void update(int r, int c, String val){
        Node node = nodes[r][c];
        String oVal = node.value;
        if(dictionary.containsKey(oVal)) dictionary.get(oVal).remove(node);

        node.value = val;
        if(val.isEmpty()) return;
        if(!dictionary.containsKey(val)) dictionary.put(val, new HashSet<>());
        dictionary.get(val).add(node);
    }

    public void update(String val1, String val2){
        if(!dictionary.containsKey(val1)) return;
        if(val1.equals(val2)) return;
        Set<Node> set = dictionary.get(val1);
        Iterator<Node> it = set.iterator();
        while(it.hasNext()){
            Node node = it.next();
            node.value = val2;
        }

        if(!dictionary.containsKey(val2)) dictionary.put(val2, new HashSet<>());
        dictionary.get(val2).addAll(set);
        dictionary.remove(val1);
    }

    public void merge (int r1, int c1, int r2, int c2){
        Node node1 = nodes[r1][c1];
        Node node2 = nodes[r2][c2];
        if(node1 == node2) return;

        String val1 = node1.value;
        String val2 = node2.value;

        if(dictionary.containsKey(val1)) dictionary.get(val1).remove(node1);
        if(dictionary.containsKey(val2)) dictionary.get(val2).remove(node2);

        if(val1.isEmpty()) val1 = val2;
        node1.value = val1;
        if(!val1.isEmpty()){
            if(!dictionary.containsKey(val1)) dictionary.put(val1, new HashSet<>());
            dictionary.get(val1).add(node1);
        }

        Set<Integer> groupset = node2.groupIds;
        node1.groupIds.addAll(groupset);

        Iterator<Integer> it = groupset.iterator();
        while(it.hasNext()){
            int n = it.next();
            int r = n/51;
            int c = n%51;
            nodes[r][c] = node1;
        }
    }

    public void unmerge(int row, int col){
        Node node = nodes[row][col];
        String val = node.value;
        if(dictionary.containsKey(val)) dictionary.get(val).remove(node);

        Set<Integer> groupset = node.groupIds;
        Iterator<Integer> it = groupset.iterator();

        while(it.hasNext()){
            int n = it.next();
            int r = n / 51;
            int c = n % 51;
            nodes[r][c] = new Node(r, c);
        }

        if(!val.isEmpty()){
            node = nodes[row][col];
            node.value = val;
            if(!dictionary.containsKey(val)) dictionary.put(val, new HashSet<>());
            dictionary.get(val).add(node);
        }
    }
}