package CodingTestStudy.SearchRating;

import java.util.*;
import java.lang.Math;

class Solution {

    public int[] solution(String[] info, String[] query) {
        int n = query.length;
        int m = info.length;
        int[] answer = new int[n];

        List<Integer>[][][][] map = new List[4][3][3][3];

        for(int i=0; i<m; i++){
            String[] str = info[i].split(" ");
            int lang = (str[0].equals("cpp"))? 0: (str[0].equals("java"))? 1: 2;
            int part = (str[1].equals("backend"))? 0 : 1;
            int career = (str[2].equals("junior"))? 0 : 1;
            int food = (str[3].equals("chicken"))? 0 : 1;
            if(map[lang][part][career][food] == null) map[lang][part][career][food] = new ArrayList<>();
            map[lang][part][career][food].add(Integer.parseInt(str[4]));
        }

        for(int l=0; l<3; l++)for(int p=0; p<2; p++)for(int c=0; c<2; c++)for(int f=0; f<2; f++)
            if(map[l][p][c][f] != null) Collections.sort(map[l][p][c][f]);

        for(int i=0; i<n; i++){
            String[] str = query[i].split(" ");
            int lang = (str[0].equals("cpp"))? 0: (str[0].equals("java"))? 1: (str[0].equals("python"))?2 : 3;
            int part = (str[2].equals("backend"))? 0 : (str[2].equals("frontend"))?1 : 2;
            int career = (str[4].equals("junior"))? 0 : (str[4].equals("senior"))?1 : 2;
            int food = (str[6].equals("chicken"))? 0 : (str[6].equals("pizza"))?1 : 2;
            int score = Integer.parseInt(str[7]);

            for(int l=(lang==3)?0:lang; l<=((lang==3)?2:lang); l++){
                for(int p=(part==2)?0:part; p<=((part==2)?1:part); p++){
                    for(int c=(career==2)?0:career; c<=((career==2)?1:career); c++){
                        for(int f=(food==2)?0:food; f<=((food==2)?1:food); f++){
                            if(map[l][p][c][f] == null) continue;
                            answer[i] += findOverScore(score, map[l][p][c][f]);
                        }
                    }
                }
            }
        }

        return answer;
    }
    public int findOverScore(int score, List<Integer> scores){
        int s = 0;
        int e = scores.size()-1;
        if(score <= scores.get(0)) return scores.size();
        if(score > scores.get(e)) return 0;

        while(true){
            int mid = (s+e)/2;
            if(scores.get(mid) >= score){
                e = mid;
            } else s = mid;
            if(e-s < 2){ // s의 점수는 반드시 score보다 작은것이 보장된다.
                return scores.size() - e;
            }
        }
    }
}