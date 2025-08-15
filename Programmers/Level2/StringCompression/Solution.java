package CodingTestStudy.Level2.StringCompression;

import java.lang.Math;
class Solution {
    public int solution(String s) {
        int originLen = s.length();
        int answer = originLen;
        for(int len=1; len<=originLen/2; len++){
            answer = Math.min(answer, countCompressedLength(s, len));
        }

        return answer;
    }

    public int countCompressedLength(String s, int len){
        StringBuilder sb = new StringBuilder();
        int originLen = s.length();
        int start = 0;

        while(start < originLen){ // start = 5
            int count = 0;
            if(start+len > originLen || start+len+len > originLen) { // 5+1 or 5+1+1 > 8? false
                sb.append(s.substring(start));
                break;
            }
            String str1 = s.substring(start, start+len); // str1 = c
            int idx = start + len; // idx = 6
            String str2 = null;

            do{
                count++; // count = 3
                if(idx+len > originLen) break; // 9
                str2 = s.substring(idx, idx+len); // str2 = c
                idx += len; // idx = 8
            }while(str1.equals(str2)); // true

            if(count > 1){
                sb.append(String.valueOf(count));
            }
            sb.append(str1);
            start += count*len;
        }
        // System.out.println(len+" 글자 단위로 잘랐을 때 압축 결과 : " + sb.toString() + " , 길이 : " + sb.length());
        return sb.length();
    }
}