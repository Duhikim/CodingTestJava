package CodingTestStudy.Level2.CorrectParenthesis;

class Solution_ver2 {

boolean solution(String s) {
    StringBuilder sb = new StringBuilder();
    char[] sArr = s.toCharArray();
    
    for(int i=0; i<sArr.length; i++) {
        if(sArr[i] == '(') {
            sb.append('(');
        }
        else if(sb.length()==0){ return false;}
        else if(sb.charAt(sb.length()-1) == '(') {
            sb.deleteCharAt(sb.length()-1);
        }
        else {
            return false;
        }
    }
    
    return (sb.length()==0);
}
}