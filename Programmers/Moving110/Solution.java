package CodingTestStudy.Moving110;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int i=0; i<s.length; i++){
            answer[i] = change(s[i]);
        }

        return answer;
    }

    public String change(String str){
        // 문자열 안에 110이 몇개 있는지 세면서 다 없앤다. while문으로 110이 없을때까지 반복
        // 남은 문자열을 우측부터 확인하여 0이 나오면 그 뒤에 모든 110을 붙여넣는다.
        // 남은 문자열에 0이 없으면 맨 앞에 모든 110을 붙여넣는다.
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            sb.append(c);
            if(sb.length()>2
                    && sb.charAt(sb.length()-1)=='0'
                    && sb.charAt(sb.length()-2)=='1'
                    && sb.charAt(sb.length()-3)=='1'){
                count++;
                sb.setLength(sb.length()-3);
            }
        }

        int insertIdx = -1;
        for(int i=sb.length()-1; i>=0; i--){
            if(sb.charAt(i) == '0'){
                insertIdx = i;
                break;
            }
        }
        sb.insert(insertIdx + 1, "110".repeat(count));
        return sb.toString();
    }
}