package CodingTestStudy.Sticks;

import java.util.*;
import java.util.Scanner;

public class Main {
    /***********************
     * 입력
     * 첫째 줄에 X가 주어진다. X는 64보다 작거나 같은 자연수이다.
     *
     * 출력
     * 문제의 과정을 거친다면, 몇 개의 막대를 풀로 붙여서 Xcm를 만들 수 있는지 출력한다.     *
     ***********************/
    public static void main(String[] args) {
        int X;
        Scanner scanner = new Scanner(System.in);
        X = scanner.nextInt();

        LinkedList<Integer> sticks = new LinkedList<>();
        sticks.add(64);
        int amount = 1;
        int totalLength = 64;
        while (totalLength != X) {
            if (totalLength > X) {
                int shortest = chopShortest(sticks);
                amount++;
                if(totalLength - shortest >= X){
                    sticks.removeFirst();
                    amount--;
                    totalLength -= shortest;
                }
            }
        }
        System.out.println(amount);
    }

    public static int chopShortest(LinkedList<Integer> sticks){
        sticks.set(0, sticks.get(0)/2);
        int temp = sticks.get(0);
        sticks.add(0, temp);
        return sticks.get(0);
    }

}
