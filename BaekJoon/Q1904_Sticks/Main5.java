package CodingTestStudy.Sticks;

import java.io.*;
import java.util.StringTokenizer;


public class Main5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());

        int X = Integer.parseInt(br.readLine());
        int answer = 0;
        while(X>0){ answer += X&1; X>>=1;}

        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
