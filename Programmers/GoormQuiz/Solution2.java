package CodingTestStudy.GoormQuiz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution2 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int discriminant = 0;
        int answer = -1;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == '(') discriminant++;
            else if(input.charAt(i) == ')') discriminant--;
            if(discriminant==0) answer++;
        }

        System.out.println(answer);
    }

}
