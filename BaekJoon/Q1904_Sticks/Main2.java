package CodingTestStudy.Sticks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
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
