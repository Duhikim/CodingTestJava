package CodingTestStudy.CorrectParenthesis;

class Solution_ver1 {
public static void main(String[] args) {
    StringBuilder sb = new StringBuilder("1234");
    System.out.println(sb.isEmpty());
}


boolean solution(String s) {
    while(s.contains("()")) {
        s=s.replace("()", "");
    }
    return s.isEmpty();
}
}