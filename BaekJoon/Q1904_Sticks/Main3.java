package CodingTestStudy.Sticks;

import java.util.LinkedList;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        int X;
        Scanner scanner = new Scanner(System.in);
        X = scanner.nextInt();
        int Num = 64;
        int answer = 1;
        while(true) {
            while (Num > X) { Num = Num >> 1;}
            X -= Num;
            if(X==0) break;
            answer++;
        }

        System.out.println(answer);
    }
}
