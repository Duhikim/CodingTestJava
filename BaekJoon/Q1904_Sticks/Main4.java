package CodingTestStudy.Sticks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main4 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int answer = 0;
        while(X>0){ answer += X&1; X>>=1;}
        System.out.println(answer);
    }
}
