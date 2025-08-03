package BaekJoon.Q1002_Turret;

import java.io.*;
import java.util.*;
import java.lang.Math;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            String input = br.readLine();
            String[] str = input.split(" ");
            int x1, y1, r1, x2, y2, r2;
            x1 = Integer.parseInt(str[0]);
            y1 = Integer.parseInt(str[1]);
            r1 = Integer.parseInt(str[2]);
            x2 = Integer.parseInt(str[3]);
            y2 = Integer.parseInt(str[4]);
            r2 = Integer.parseInt(str[5]);
            if(x1==x2 && y1==y2 && r1==r2) {
                System.out.println("-1");
                continue;
            }
            double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
            if(r1+r2 < distance) System.out.println("0");
            else if(r1+r2 == distance) System.out.println("1");
            else if(Math.max(r1, r2) > Math.min(r1, r2)+ distance)System.out.println("0");
            else if(Math.max(r1, r2) == Math.min(r1, r2)+ distance)System.out.println("1");
            else System.out.println("2");
        }
    }
}