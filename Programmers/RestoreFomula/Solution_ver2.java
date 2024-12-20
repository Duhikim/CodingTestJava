package CodingTestStudy.RestoreFomula;

import java.util.ArrayList;

class Solution_ver2 {
	public String[] solution(String[] expressions) {

		ArrayList<Integer> ukIdx = new ArrayList<>(); // X가 포함된 수식의 인덱스

		int base = getBase(expressions, ukIdx);
		int baseMin = 2;
		if(base!=0){
			baseMin = getBaseMin(expressions);
		}
		String[] answer = new String[ukIdx.size()];
		for(int i=0; i<ukIdx.size(); i++){
			answer[i] = expressions[ukIdx.get(i)];
		}

		ConvertText(answer,base, baseMin);

		return answer;
	}

	public int getBase(String[] expressions, ArrayList<Integer> ukIdx) {
		// 1. 진수는 수식에 나오는 가장 큰 숫자보다 크다.
		// 2. 덧셈 혹은 뺄셈에서 자리수 넘어 영향을 주는 연산의 경우, 예를들면 6+5 = 13, 33-6 = 24, 22+20 = 102, 13+17 = 32, 진수가 확정된다.
		// 3. 진수가 확정되지 않는 경우도 있음. 그땐 0을 Return하도록 한다.
		int a, b, c;

		for (int idx = 0; idx< expressions.length; idx++) {
			String[] str = expressions[idx].split(" ");

			if (str[4].equals("X")) {
				ukIdx.add(idx);
				continue;
			}

			boolean plus = str[1].equals("+");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[2]);
			c = Integer.parseInt(str[4]);

			int cDec = plus ? a + b : a - b;
			int def = Math.abs(c - cDec);
			if (def == 0) continue;
			if (def < 9) return (10 - def); //여기까지 하면 대충 두자리 숫자 까지는 맞음. 합했을 때 세자리로 넘어가는 경우 체크해야함.

			if(cDec % 10 != c % 10) return (cDec % 10 != 0) ? cDec % 10 - c % 10 : 10 - c % 10;
			else {
				return (cDec / 10 + 10 - c / 10);
			}
		}
		//위 루프에서 못찾은 경우, 혹시 식에 8이 포함되어 있으면 9진수 확정.
		for (String formula : expressions) {
			if(formula.contains("8")) return 9;
		}
		return 0;
	}
	public int getBaseMin(String[] expressions) {
		int baseMin=2;
		for (String formula : expressions) {
			for (int i = 0; i < formula.length(); i++) {
				if (formula.charAt(i) >= '0' && formula.charAt(i) <= '9' &&
						(formula.charAt(i) - '0') >= baseMin) { baseMin = formula.charAt(i) - '0' + 1; }
			}
		}
		return baseMin;
	}
	public void ConvertText(String[] answer, int base, int baseMin) {

		int a, b;
		for (int i = 0; i<answer.length; i++) {
			String[] str = answer[i].split(" ");
			boolean plus = str[1].equals("+");
			a = Integer.parseInt(str[0]);
			b = Integer.parseInt(str[2]);

			int aDec, bDec, cDec;
			StringBuilder cStr = new StringBuilder();
			if (base>0) {
				aDec = a % 10 + (a / 10) % 10 * base;
				bDec = b % 10 + (b / 10) % 10 * base;
				cDec = plus ? aDec + bDec : aDec - bDec;
				cStr.append((char)((cDec / (base * base)) % base + '0'));
				cStr.append((char)((cDec / base) % base + '0'));
				cStr.append((char)(cDec % base + '0'));
			}
			else {
				// 1. 각 자리의 숫자가 a가 b보다 크거나 같은 경우 그대로 뺄셈 하면 됨. (ex 54 - 23은 진수와 관계 없이 31이다.)
				// 2. 더하기의 경우, 각 자리의 숫자끼리 더했을 때 최소진수보다 작으면 그대로 더하면 됨. (최소 진수가 7일 때 23+11 은 진수 관계없이 34이다.)
				if (plus) {
					if ( (a % 10 + b % 10 < baseMin) && ( ( (a / 10) % 10 + (b / 10) % 10) < baseMin) ) {
						cDec = a + b;
						cStr.append((char)((cDec / 10) % 10 + '0'));
						cStr.append((char)(cDec % 10 + '0'));
					}
					else { cStr.append('?'); }
				}
				else {
					if ((a % 10 >= b % 10) && ((a / 10) % 10 >= (b / 10) % 10)) {
						cDec = a - b;
						cStr.append((char)((cDec / 10) % 10 + '0'));
						cStr.append((char)(cDec % 10 + '0'));
					}
					else { cStr.append('?');  }
				}
			}
			while (cStr.charAt(0) == '0' && cStr.length()>1) { cStr.deleteCharAt(0); }
			char op = (plus)? '+': '-';
			answer[i] = a+" "+op+" "+b+" = "+cStr;
		}
	}
}
