package CodingTestStudy.RestoreFomula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ExpressionToken {
	String operand1;
	char operator;
	String operand2;
	String result;
	boolean isX;

	public ExpressionToken(String operand1, char operator, String operand2, boolean isX, String result) {
		this(operand1, operator, operand2, isX);
		this.result = result;
	}

	public ExpressionToken(String operand1, char operator, String operand2, boolean isX) {
		this.operand1 = operand1;
		this.operator = operator;
		this.operand2 = operand2;
		this.isX = isX;
	}

	public String NoBaseString() {
		return operand1 + " " + operator + " " + operand2 + " = ?";
	}

	public String BaseString(String result) {
		return operand1 + " " + operator + " " + operand2 + " = " + result;
	}
}

class Solution_HJ {
	public static void main(String[] args) {
		Solution_HJ solution = new Solution_HJ();
//        String[] expressions = {"14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"};
//        System.out.println(Arrays.toString(solution.solution(expressions)));
//
//        String[] expressions2 = {"1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"};
//        System.out.println(Arrays.toString(solution.solution(expressions2)));
//
//        String[] expressions3 = {"10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"};
//        System.out.println(Arrays.toString(solution.solution(expressions3)));
//
//        String[] expressions4 = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"};
//        System.out.println(Arrays.toString(solution.solution(expressions4)));
//
//        String[] expressions5 = {"2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"};
//        System.out.println(Arrays.toString(solution.solution(expressions5)));

//		String[] expressions6 = {"14 + 3 = X", "7 + 7 = X"};
//		System.out.println(Arrays.toString(solution.solution(expressions6)));
//
//		String[] expressions7 = {"2 - 1 = X", "3 + 3 = X"};
//		System.out.println(Arrays.toString(solution.solution(expressions7)));

//		String[] expressions8 = {"1 - 1 = X", "1 + 1 = X"};
//		System.out.println(Arrays.toString(solution.solution(expressions8)));
		String[] expressions9 = {"11 + 22 = 33", "6 + 2 = X"};
		System.out.println(Arrays.toString(solution.solution(expressions9)));


	}

	public String[] solution(String[] expressions) {
		List<String> answerList = new ArrayList<>();
		ArrayList<ExpressionToken> questions = new ArrayList<>();


		// 최소 진수 체크
		int minBase = findMinBaseAndQuestion(expressions, questions);
		// 진수 찾기
		int Base = findBase(questions, minBase);
		//진수 계산
		calculateBase(questions, Base, minBase, answerList);

		return answerList.toArray(new String[0]);
	}

	private int findMinBaseAndQuestion(String[] expressions, ArrayList<ExpressionToken> questions) {
		int minBase = 0;
		for (String expression : expressions) {
			String[] target = expression.split(" ");
			String operand1 = target[0];
			String operand2 = target[2];
			char operator = target[1].charAt(0);
			String result = target[4];

			// question 리스트 생성
			if (target[4].equals("X")) {
				questions.add(new ExpressionToken(operand1, operator, operand2, true));
			} else {
				questions.add(new ExpressionToken(operand1, operator, operand2, false, result));
			}

			// 최소 진수 찾기
			for (int index = 0; index < expression.length(); index++) {
				char charExp = expression.charAt(index);
				if (Character.isDigit(charExp) && minBase < charExp - '0') {
					minBase = charExp - '0';
				}
			}
		}
		return minBase + 1;
	}

	private int findBase(ArrayList<ExpressionToken> questions, int minBase) {
		int Base = 0;
		for (ExpressionToken question : questions) {
			if(question.isX){
				continue;
			}
			if (minBase == 9) {
				return 9;
			}
			// 결과가 X가 아니라면 minBase ~ 9진수 까지 계산 결과를 비교
			for (int index = minBase; index < 10; index++) {
				String baseIndexSum = calculate(index,question);

				if (baseIndexSum.length() > 1 && baseIndexSum.equals(question.result)) {
					Base = index;
					break;
				}
			}
		}
		return Base;
	}

	public void calculateBase(ArrayList<ExpressionToken> questions, int Base, int minBase, List<String> answerList) {
		for (ExpressionToken question : questions) {
			String result;
			if (question.isX) {
				if (Base == 0) { // Base를 찾지 못한 경우
					int base10Sum;
					if (question.operator == '+') {
						base10Sum = Integer.parseInt(question.operand1) + Integer.parseInt(question.operand2);
					} else {
						base10Sum = Integer.parseInt(question.operand1) - Integer.parseInt(question.operand2);
					}
					// 결과가 minBase보다 작을 때만 유효
					if (base10Sum < minBase) {
						result = Integer.toString(base10Sum); // 결과값 갱신
					} else {
						// 추가적으로 여러 진법에서 결과가 일치하는지 확인
						result = consistentCheck(minBase,question);
					}
				}
				else {
					result = calculate(Base, question);
				}
				answerList.add(question.BaseString(result));
			}
		}
	}

	public String calculate(int index, ExpressionToken question){
		if (question.operator == '+') {
			int base10Sum = Integer.parseInt(question.operand1, index) + Integer.parseInt(question.operand2, index);
			return Integer.toString(base10Sum, index);
		} else {
			int base10Sum = Integer.parseInt(question.operand1, index) - Integer.parseInt(question.operand2, index);
			return Integer.toString(base10Sum, index);
		}
	}

	public String consistentCheck(int minBase, ExpressionToken question){
		String consistentResult = "";
		boolean isConsistent = true;

		for (int index = minBase; index < 10; index++) {
			String currentResult = calculate(index, question);
			if (index == minBase) {
				consistentResult = currentResult;
			} else if (!consistentResult.equals(currentResult)) {
				isConsistent = false;
				break;
			}
		}
		if (isConsistent) {
			return consistentResult;
		}
		return "?";
	}
}

