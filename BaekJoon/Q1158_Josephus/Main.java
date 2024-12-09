package BaekJoon.Q1158_Josephus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = br.readLine();
        String[] str = inputStr.split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<Integer> copyN =  new LinkedList<>();
        for(int i=1; i<=N; i++){
            copyN.add(i);
        }


        int idxToRemove = K;
        int size = copyN.size();
        while(size != 0){
            while(idxToRemove > size){ idxToRemove -= size; }
            result.add(copyN.get(idxToRemove-1));
            copyN.remove(idxToRemove-1);
            size--;
            idxToRemove += K - 1;
        }


        System.out.print("<");
        for(int i=0; i<result.size()-1; i++){
            System.out.print(result.get(i)+", ");
        }System.out.println(result.get(result.size()-1) + ">");

    }
//    public ArrayList<Integer> Josephus(int N, int K){
//        ArrayList<Integer> result = new ArrayList<>();
//        LinkedList<Integer> copyN =  new LinkedList<>();
//        for(int i=1; i<=N; i++){
//            copyN.add(i);
//        }
//
//
//        int idxToRemove = K;
//        int size = copyN.size();
//        while(size != 0){
//            while(idxToRemove > size){ idxToRemove -= size; }
//            result.add(copyN.get(idxToRemove-1));
//            copyN.remove(idxToRemove-1);
//            size--;
//            idxToRemove += K - 1;
//        }
//        return result;
//    }
}
