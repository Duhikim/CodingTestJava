package CodingTestStudy.Level2.PhoneNumberList;

class Solution {
    class Node{
        Node[] children = new Node[10];
        boolean isEnd;
    }

    public boolean solution(String[] phone_book) {
        Node root = new Node();

        for(String num: phone_book){
            Node cur = root;
            for(int i=0; i<num.length(); i++){
                int idx = num.charAt(i) - '0';
                if(cur.children[idx]==null) cur.children[idx] = new Node();
                cur = cur.children[idx];

                if(cur.isEnd) return false;
            }
            for(Node child: cur.children){
                if(child != null) return false;
            }
            cur.isEnd = true;
        }
        return true;
    }

}