package CodingTestStudy.Level3.ExpressibleBinTree;


class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for(int i=0; i<numbers.length; i++){
            String binary = Long.toString(numbers[i], 2);
            binary = fillLength(binary);
            // System.out.println(binary);
            answer[i] = (isConvertable(binary))? 1 : 0;

        }

        return answer;
    }

    public String fillLength(String bin){
        // 길이를 3, 7, 15 등으로 맞춰야함. 안맞으면 앞에 0을 붙이면 됨.
        // 숫자의 최대값은 10^15. 2진수로 바꾸면 15 log2(10) < 60 자리 정도가 나온다.
        // 가질 수 있는 길이: 1, 3, 7, 15, 31

        StringBuilder sb = new StringBuilder();
        int len = bin.length();

        for(int l=1; l<64; l=l+l+1){
            if(len > l) continue;
            if(len < l){
                for(int i=0; i<l-len; i++) sb.append('0');
            }
            sb.append(bin);
            break;
        }

        return sb.toString();
    }

    public boolean isConvertable(String bin){
        int len = bin.length();
        if(len==1) return true;

        char ctr = bin.charAt(len/2);
        if(ctr=='0'){
            for(int i=0; i<len; i++){
                if(bin.charAt(i)=='1') return false;
            } return true;
        }
        return (isConvertable(bin.substring(0, len/2)) && isConvertable(bin.substring(len/2+1, len)) );
    }
}