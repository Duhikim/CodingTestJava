package CodingTestStudy.FineSquare;

class Solution {
    public long solution(int w, int h) {

        int gcd = getGCD(w, h);
        int wUnit = w/gcd;
        int hUnit = h/gcd;

        int removeUnits = hUnit + wUnit - 1;

        return (long)w*h - removeUnits*gcd;
    }

    public int getGCD(int a, int b){
        int r = a%b;
        while(r > 0){
            a = b;
            b = r;
            r = a%b;
        }
        return b;
    }

}