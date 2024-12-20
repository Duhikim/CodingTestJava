package CodingTestStudy.RestoreFomula;

import java.util.ArrayList;
import java.util.Arrays;

// 진수가 결정되는 경우는 다음과 같다.
// 1. 똑같은 연산을 하였을 때 10진법의 연산과 결과가 달라지는 경우.
// 		일반적으로 10진법 연산과 실제 연산의 차이가 10 미만인 경우 10에서 그 차이를 빼주면 Base가 된다. 23+32 = 105 55
//		>>> 이 방법 보다 1의 자리수만 비교하는 것이 좋아 보인다. 1의 자리수가 다르면 그 차이를 10에서 빼주면 진수가 되고
//		>>> 1의 자리수가 같으면 10으로 나누고 남은 숫자를 비교하면 된다.
// 2. 2~9진법으로 제한되어 있으니 8이라는 숫자가 한 번이라도 나오면 무조건 9진법이다.
//
// 이 조건에 안 걸리면 Base를 알 수 없다. 하지만 나온 숫자들로 미루어 최소 Base는 알 수 있으므로 일부 식은 복원할 수 있다.

public class Solution {


//	public static void main(String[] args) {
//		Solution sol = new Solution();
//		String[] exp = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
//
//		System.out.println(Arrays.toString(sol.solution(exp)));
//
//	}

	public String[] solution(String[] expressions) {

		Formula[] formulas = getFormArray(expressions);
		ArrayList<Integer> unknownIndex = getUkIdx(formulas);
		int base = getBase(formulas);
		int baseMin = (base == 0) ? getBaseMin(formulas) : 0;

		return restoreX(formulas, unknownIndex, base, baseMin);
	}

	public static class Formula {
		String A;
		int a;
		boolean plus;
		String B;
		int b;
		String C;
		int max;

		public Formula(String expression) {
			String[] strDiv = expression.split(" ");
			this.max = 0;
			this.A = strDiv[0];
			for(int i=0; i<A.length(); i++){
				if(max<A.charAt(i)-'0') max = A.charAt(i)-'0';
			}
			this.a = Integer.parseInt(strDiv[0]);
			this.plus = strDiv[1].equals("+");
			this.B = (strDiv[2]);
			for(int i=0; i<B.length(); i++){
				if(max<B.charAt(i)-'0') max = B.charAt(i)-'0';
			}
			this.b = Integer.parseInt(strDiv[2]);
			this.C = strDiv[4];
			if(!C.equals("X")){
				for(int i=0; i<C.length(); i++){
					if(max<C.charAt(i)-'0') max = C.charAt(i)-'0';
				}
			}
		}
		@Override
		public String toString() {
			char op = (plus) ? '+' : '-';
			return (a + " " + op + " " + b + " = " + C);
		}
	}
	// String 배열을 Formula 배열로 바꾸는 함수
	public Formula[] getFormArray(String[] expressions) {
		Formula[] formulas = new Formula[expressions.length];
		for (int i = 0; i < expressions.length; i++) {
			formulas[i] = new Formula(expressions[i]);
		}
		return formulas;
	}
	// X가 포함되는 식의 인덱스를 List에 저장하는 함수
	public ArrayList<Integer> getUkIdx(Formula[] formulas) {
		ArrayList<Integer> answer = new ArrayList<>();
		for (int i = 0; i < formulas.length; i++) {
			if (formulas[i].C.equals("X")) answer.add(i);
		}
		return answer;
	}
	// 진수를 구하는 함수
	public int getBase(Formula[] formulas) {
		int A, B, C, decC;
		for (Formula formula : formulas) {
			if (formula.C.equals("X")) continue;
			A = formula.a;
			B = formula.b;
			C = Integer.parseInt(formula.C);
			decC = (formula.plus) ? (A + B) : (A - B);
			if (C == decC) continue;

			if (C % 10 != decC % 10) {
				return 10 - Math.abs(C % 10 - decC % 10);
			} else {
				return 10 - (C / 10 - decC / 10);
			}
		}
		return 0;
	}
	// 진수를 못 구하는 경우, 최소 진수를 구하는 함수
	public int getBaseMin(Formula[] formulas) {
		int baseMin = 2;
		for (Formula formula : formulas) {
			for(int i=0; i<formula.A.length(); i++){
				if(formula.A.charAt(i)-'0' >= baseMin) baseMin = formula.A.charAt(i)-'0'+1;
			}
			for(int i=0; i<formula.B.length(); i++){
				if(formula.B.charAt(i)-'0' >= baseMin) baseMin = formula.B.charAt(i)-'0'+1;
			}
			if(!formula.C.equals("X")){
				for(int i=0; i<formula.C.length(); i++){
					if(formula.C.charAt(i)-'0' >= baseMin) baseMin = formula.C.charAt(i)-'0'+1;
				}
			}
		}
		return baseMin;
	}
	// 할 수 있으면 X를 복원하여 String 배열로 반환하는 함수
	public String[] restoreX(Formula[] formulas, ArrayList<Integer> unknownIndex, int base, int baseMin) {
		if (baseMin == 9) base = 9;

		String[] answer = new String[unknownIndex.size()];

		for (int i=0; i<unknownIndex.size(); i++) {
			int idx = unknownIndex.get(i);
			if (base != 0){ // 진수가 확정된 경우
				formulas[idx].C = calculate(formulas[idx].a, formulas[idx].b, formulas[idx].plus, base);
			}
			else { // 진수를 모르는 경우,
				// 더하기의 경우 : 각 자리수의 합이 최소 진수보다 작은 경우 그대로 더하면 됨. 10진수랑 똑같은 결과
				// 빼기의 경우 : 각 자리수의 차가 0 이상이면 그냥 빼면 됨.
				if( formulas[idx].plus
						&& (formulas[idx].a%10 + formulas[idx].b%10 < baseMin)
						&& ( (formulas[idx].a/10 + formulas[idx].b/10) < baseMin)
				){
					formulas[idx].C = Integer.toString(formulas[idx].a + formulas[idx].b);
				}
				else if( !formulas[idx].plus
						&& formulas[idx].a%10 >= formulas[idx].b%10
						&& formulas[idx].a/10 >= formulas[idx].b/10
				){
					formulas[idx].C = Integer.toString(formulas[idx].a - formulas[idx].b);
				}
				// todo
				else{
					formulas[idx].C = "?";
				}
//
//				int cTemp = Integer.parseInt(calculate(formulas[idx].a, formulas[idx].b, formulas[idx].plus, baseMin));
//				int cDec = (formulas[idx].plus)? formulas[idx].a + formulas[idx].b: formulas[idx].a - formulas[idx].b;
//
//				formulas[idx].C = (cTemp == cDec)? Integer.toString(cTemp): "?";
			}
			answer[i] = formulas[idx].toString();
		}
		return answer;
	}

	public String calculate(int A, int B, boolean plus, int base){

		int aBaseN = 0, bBaseN = 0; // 정수 A와 B가 10진수로 몇 인지 저장하는 변수.
		aBaseN += A%10; // A의 마지막 자리 수(1의 자리) 더함
		A/=10; // A를 10으로 나눔. 한자리 수면 0이 됨. 두자리 수면 두번째 자리의 수만 남음.
		aBaseN += A*base; // 남은 수에 진수를 곱해서 더해줌.
		bBaseN += B%10; // A와 동일한 작업 B에도 적용
		B/=10;
		bBaseN += B*base;

		return (plus)?Integer.toString(aBaseN+bBaseN, base): Integer.toString(aBaseN-bBaseN, base);
	}
}