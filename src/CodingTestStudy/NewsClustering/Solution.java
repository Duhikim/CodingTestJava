package CodingTestStudy.NewsClustering;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public static void main(String[] args) {
        String str1, str2;
        int expected, calculated;

        str1 = new String("FRANCE");
        str2 = new String("french");
        expected = 16384;
        calculated = solution(str1, str2);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);

        str1 = new String("handshake");
        str2 = new String("shake hands");
        expected = 65536;
        calculated = solution(str1, str2);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);

        str1 = new String("aa1+aa2");
        str2 = new String("AAAA12");
        expected = 	43690;
        calculated = solution(str1, str2);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);

        str1 = new String("E=M*C^2");
        str2 = new String("e=m*c^2");
        expected = 	65536;
        calculated = solution(str1, str2);
        if(expected == calculated) System.out.println("Correct! : " + calculated);
        else System.out.println("Wrong! : " + expected +" , " + calculated);
    }

    public static int solution(String str1, String str2) {
        /***********
         < 입력 >
         입력으로는 str1과 str2의 두 문자열이 들어온다. 각 문자열의 길이는 2 이상, 1,000 이하이다.
         입력으로 들어온 문자열은 두 글자씩 끊어서 다중집합의 원소로 만든다.
         이때 영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자 쌍을 버린다.
         예를 들어 "ab+"가 입력으로 들어오면, "ab"만 다중집합의 원소로 삼고, "b+"는 버린다.
         다중집합 원소 사이를 비교할 때, 대문자와 소문자의 차이는 무시한다.
         "AB"와 "Ab", "ab"는 같은 원소로 취급한다.

         < 출력 >
         입력으로 들어온 두 문자열의 자카드 유사도를 출력한다.
         유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에
         소수점 아래를 버리고 정수부만 출력한다.

         <풀이>
         str1, str2를 두 글자씩 잘라서 새로운 배열로 만든다. 그 배열의 크기는 (입력 문자열 길이)-1 이 될 듯. 즉 1000 이하.
         중복이 있는 경우를 잘 처리해야 한다. 112233 과 12223의 합집합은 1122233 교집합은 1223이 되는 것을 기억해야 함.
         즉 해당 원소가 몇 개 들어있는지 파악할 수 있게 배열이 아닌 HashMap으로 짜는게 용이해 보임.
         순서는 상관 없으니 더더욱.
         HashMap으로 두 세트가 나오면 비교를 시작하면 됨.
         한 맵을 기준으로 다른 맵을 탐색하면서 처리. (1000 x 1000번의 비교적 작은 연산인데다가 HashMap이라 훨씬 빠를 듯)
         1번 Map의 요소 A가 n개 있는데 2번 Map에 없으면 합집합의 크기는 +n 늘어남. 교집합의 크기는 +0.
         1번 Map의 요소 B가 m개 있는데 2번 Map에 l개 있으면 합집합의 크기는 m과 l중에 큰 값만큼 늘어나고 교집합의 크기는 작은 값 만큼 늘어남.
         ***********/

        HashMap<String, Integer> letterMap1 = makeMap(str1);
        HashMap<String, Integer> letterMap2 = makeMap(str2);

        double doubleAns = getSimilarity(letterMap1, letterMap2);
        return (int)(doubleAns * 65536);
    }

    public static HashMap<String, Integer> makeMap(String str) {
        str = str.toUpperCase(); // 문자열 정리부터 한다. 대소문자 구분 안 하므로 대문자로 일괄 전환 후 대문자 제외하고 필터링.
        // str = str.replaceAll("[^A-Z]", ""); // 문제를 잘못이해한듯. AB+CD가 있으면 AB BC CD가 아니라 AB CD 이렇게 들어가야 하나 보다.

        HashMap<String, Integer> letterMap = new HashMap<>();
        // str의 인덱스 0부터 마지막 전 글자까지 두 글자씩 묶어서 Map에 넣고 value는 1로 함.
        // 중복이 있는 경우 value를 +1 올림.
        for(int i=0; i<str.length()-1; i++) {
            String temp = str.substring(i, i+2);
            temp = temp.replaceAll("[^A-Z]", "");
            if(temp.length()==2)
                letterMap.put(temp, letterMap.getOrDefault(temp, 0) + 1);
        }
        return letterMap;
    }

    public static double getSimilarity(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
        double a = 0.0, b = 0.0;
        // a는 분자로, 두 Map에 중복되는 요소의 개수를 의미. 어떤 요소가 Map1에는 2개, Map2에는 4개 있으면 2 증가.
        // b는 분모로, 두 Map에 있는 모든 요소의 개수를 의미. 어떤 요소가 Map1에는 2개 Map2에는 4개 있으면 4 증가.

        // map1을 기준으로 탐색을 하기 때문에 map1에는 없고 map2에는 존재하는 요소들을 처리할 수 없음.
        // *따라서 map1 기준으로 탐색하면서 처리할때마다 map2에서 해당 요소를 삭제해버린 후 맨 마지막에 남아있는 것들을 일괄 처리 예정.
        for(String key1 : map1.keySet()) {
            a += Math.min(map1.get(key1), map2.getOrDefault(key1, 0));
            b += Math.max(map1.get(key1), map2.getOrDefault(key1, 0));
            map2.remove(key1); // HashMap에서 없는 key값을 remove 시도할 때 에러가 발생하지 않는지 확인 후 사용.
        }
        // map2에 남아있는 값들은 map1에 없는 값들 이므로 a에는 영향을 주지 않고 b에 일괄적으로 더해주면 된다.
        for(String key2 : map2.keySet()) {
            b += map2.get(key2);
        }

        return (b==0)? 1.0 : a/b; // b가 0인경우는 발생하지 않지만 문제 Narrative에 있으므로 처리를 해준다.
    }
}
