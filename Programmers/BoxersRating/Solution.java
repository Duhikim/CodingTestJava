package CodingTestStudy.BoxersRating;

import java.util.*;
import java.io.*;
import java.lang.Math;

class Solution {
    Boxer[] boxers;
    int n;

    class Boxer{
        Set<Integer> winTo;
        Set<Integer> loseTo;

        public Boxer(){
            winTo = new HashSet<>();
            loseTo = new HashSet<>();
        }
    }

    public int solution(int n, int[][] results) {
        this.n = n;
        boxers = new Boxer[n+1];
        for(int i=1; i<=n; i++){
            boxers[i] = new Boxer();
        }

        for(int[] result:results){
            int winner = result[0];
            int loser = result[1];
            boxers[winner].winTo.add(loser);
            boxers[loser].loseTo.add(winner);
        }

        boolean changed = true;
        while(changed){
            changed = false;
            for(int i=1; i<=n; i++){
                int winsBefore = boxers[i].winTo.size();
                int losesBefore = boxers[i].loseTo.size();
                Set<Integer> newWin = new HashSet<>();
                Set<Integer> newLose = new HashSet<>();
                for(int loser: boxers[i].winTo){
                    newWin.addAll(boxers[loser].winTo);
                }
                for(int winner: boxers[i].loseTo){
                    newLose.addAll(boxers[winner].loseTo);
                }
                boxers[i].winTo.addAll(newWin);
                boxers[i].loseTo.addAll(newLose);
                if(winsBefore != boxers[i].winTo.size()
                        || losesBefore != boxers[i].loseTo.size()){
                    changed = true;
                }
            }
        }

        int answer = 0;

        for(int i=1; i<=n; i++){
            int min = n - boxers[i].winTo.size();
            int max = 1 + boxers[i].loseTo.size();
            if(min==max || min == 1 || max == n) answer++;
        }


        return answer;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] result = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};

        sol.solution(n, result);
    }
}

