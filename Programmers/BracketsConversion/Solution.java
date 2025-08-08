package CodingTestStudy.BracketsConversion;

class Solution {
    public String solution(String p) {
        if(p.equals("")) return "";

        int stack = 0;
        int divIdx = 0;
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '(') stack++;
            else stack--;
            if(stack==0) {
                divIdx = i+1;
                break;
            }
        }
        String u = p.substring(0, divIdx);
        String v = p.substring(divIdx);

        if(isProper(u)){
            return u + solution(v);
        }

        String answer = "(";
        answer = answer + solution(v) + ")";

        u = u.substring(1, u.length()-1);
        char[] arr = new char[u.length()];

        for(int i=0; i<u.length(); i++){
            if(u.charAt(i) == '(') arr[i] = ')';
            else arr[i] = '(';
        }

        return answer + new String(arr);
    }

    public boolean isProper(String p){
        int stack = 0;
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '(') stack++;
            else stack--;
            if(stack < 0) return false;
        }
        return stack==0;
    }
}