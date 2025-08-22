package CodingTestStudy.Level3.SealedOrder;

/*
온전한 주문서 기준으로 숫자를 주문으로 바꾸는 함수와 주문을 숫자로 바꾸는 함수를 만든다.
삭제된 주문을 기준에 맞게 정렬한다.
삭제된 주문의 번호가 n보다 작거나 같으면 n을 1 올린다.
삭제된 주문의 번호가 n보다 크면, n을 주문으로 바꿔서 반환한다.

*/
import java.util.*;
class Solution2 {
    public String solution(long n, String[] bans) {
        String answer = new String();

        long[] bansNum = new long[bans.length];

        for(int i=0; i<bans.length; i++){
            bansNum[i] = spellToNum(bans[i]);
        }

        Arrays.sort(bansNum);

        long N = binarySearch(n, bansNum);

        answer = numToSpell(N);

        return answer;
    }

    public long binarySearch(long n, long[] bans){
        int start = 0;
        int end = bans.length-1;


        while(true){
            if(bans[start] > n) return n;
            if(bans[end] <= n) return n + end-start+1;

            int mid = (start+end)/2;
            while(bans[mid] > n){
                mid = (start+mid)/2;
            }
            n += mid-start+1;
            start = mid+1;
        }
    }

    public long spellToNum(String spell){
        long result = 0;
        long base = 1;
        int len = spell.length();

        for(int i=len-1; i>=0; i--){
            char c = spell.charAt(i);
            result += (c-'a'+1) * base;
            base *= 26;
        }
        return result;
    }

    public String numToSpell(long num){

        StringBuilder sb = new StringBuilder();
        while(num > 0){
            char c = (char)((int)((num-1) % 26) + 'a');
            sb.append(c);
            num = (num-1)/26;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        long n =
//        30;
                7388;
        String[] bans =
//        {"d", "e", "bb", "aa", "ae"};
                {"gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc"};

        System.out.println(sol.solution(n, bans));

    }
}