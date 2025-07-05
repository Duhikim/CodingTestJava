package BaekJoon.Q18125_CatFood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        String[][] foodPicture = getInputs(C, R);
        String[][] givenFood = getInputs(R, C);

        print(compareRotatedMatrix(foodPicture, givenFood));

    }

    public static boolean compareRotatedMatrix(String[][] matA, String[][] matB){
        int R = matA[0].length; // 5
        int C = matA.length; // 15

        for(int i=0; i<C; i++){
            for(int j=0; j<R; j++){
                if(!matA[i][j].equals(matB[j][C-i-1])) return false;
            }
        }
        return true;
    }

    public static String[][] getInputs(int A, int B) throws IOException {
        String[][] result = new String[A][B];
        for(int a=0; a<A; a++){
            String[] inputs = br.readLine().split(" ");
            for(int b=0; b<B; b++){
                result[a][b] = inputs[b];
            }
        }
        return result;
    }

    public static void print(boolean result){
        String cat;
        if(result){
            cat =
                    "|>___/|        /}\n" +
                    "| O < |       / }\n" +
                    "(==0==)------/ }\n" +
                    "| ^  _____    |\n" +
                    "|_|_/     ||__|\n";


        }else{
            cat =
                    "|>___/|     /}\n" +
                    "| O O |    / }\n" +
                    "( =0= )\"\"\"\"  \\\n" +
                    "| ^  ____    |\n" +
                    "|_|_/    ||__|\n";
        }
        System.out.println(cat);
    }
}
