package CodingTestStudy.Sticks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        int answer = 1;
        int[] arr = {64, 32, 16, 8, 4, 2, 1};
        int num=0;
        for(int bin: arr){
            if(num+bin == X) { System.out.println(answer); return; }
            if(num+bin < X){
                num += bin;
                answer++;
            }
        }
        System.out.println(answer);
    }
}
