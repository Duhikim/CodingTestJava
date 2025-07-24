package CodingTestStudy.DivideNumberCard;
import java.util.*;

public class Solution2 {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];

        for (int i = 1; i < arrayA.length; i++) gcdA = gcd(gcdA, arrayA[i]);
        for (int i = 1; i < arrayB.length; i++) gcdB = gcd(gcdB, arrayB[i]);

        int candidateA = validDivisor(gcdA, arrayB);
        int candidateB = validDivisor(gcdB, arrayA);

        return Math.max(candidateA, candidateB);
    }

    private int gcd(int a, int b) {
        while(b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // divisor가 상대편 배열을 하나도 나누지 못할 경우에만 반환
    private int validDivisor(int divisor, int[] otherArray) {
        if (divisor == 1) return 0;
        for (int num : otherArray) {
            if (num % divisor == 0) return 0;
        }
        return divisor;
    }
}
