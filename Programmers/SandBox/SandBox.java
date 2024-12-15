package CodingTestStudy.SandBox;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class SandBox {
static int read() throws Exception {
    int c, n = System.in.read() & 15;
    boolean m = n == 13;
    if (m)n = System.in.read() & 15;
    while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
    return m ? ~n + 1 : n;
}
public static void main(String[] args) throws Exception {
        /****************************
         <샌드 박스에서 실험을 통해 알게 된 사실들 메모>
         * ArrayList, ArrayDeque 등의 컨테이너를 a = b 같이 복사하면 자동으로 얕은 복사(참조 복사)가 일어난다.
            이게 싫으면 clone을 통해 수동으로 깊은 복사를 해야 한다.
         
         * String str = "ABC";
                  str += 123;
                  System.out.println(str); // ABC123
            이와 같이 String과 int 값을 단순 덧셈 연산 하면 123은 "123"으로 자동 변환 되어 str 뒤에 붙여진다.
         
         *List<String> list = Arrays.asList("a", "b", "c");
                  Stream<String> stream = list.stream();
                  Stream<String> parallelStream = list.parallelStream();
                    List는 인터페이스지만 asList를 하면 ArrayList와 비슷한 클래스의 객체가 생성된다. 하지만 이 객체는 고정 객체로, remove나 add가 되지 않는다.
         ******************************/
        
      int a= read();
      int b = read();
      
      ArrayList<Integer> myList = new ArrayList<>();
      for(int i=0; i<10; i++)
          myList.add(i);
      Integer[] arr = myList.toArray(Integer[]::new);
      ArrayDeque<Integer> myDeque = new ArrayDeque<>();
      myDeque = new ArrayDeque<>(myList);
      //myDeque = myList.stream().collect(ArrayDeque::new, ArrayDeque::add, ArrayDeque::addAll);
    //myDeque = myList.stream().collect(Collectors.toCollection(ArrayDeque<Integer>::new));
      
      System.out.println(a + " " + b);
}
}
