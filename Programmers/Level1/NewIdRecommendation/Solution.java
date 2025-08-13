package CodingTestStudy.Level1.NewIdRecommendation;

import java.util.Scanner;

public class Solution {
    
	public static void main(String[] args) {
		while(true) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("ID를 입력하세요. '.'을 입력하고 엔터를 누르면 프로그램이 종료됩니다.");
		String inputId = scanner.nextLine();
		if(inputId.equals(".")) break;
		
		String recommendedId = solution(inputId);
		if(recommendedId.equals(inputId)) {
			System.out.println("그대로 사용하셔도 됩니다.");
		}
		else {
			System.out.println("추천 ID : " + recommendedId);
		}
		}		
		System.out.println("프로그램을 종료합니다.");
	}
	
	public static String solution(String new_id) {
        /*************************
         * 아이디는 3자~15자
         * 알파벳 소문자, 숫자, -(뺴기) , _(밑줄) , .(온점) 문자만 사용 가능
         * 온점은 처음과 끝에 사용 안되고 연속으로 사용 안됨.
         * 
         * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
         * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
         * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
         * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
         * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
         * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
     		만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
         * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
         * 
         **************************/
		
		boolean debug = true;
						// false;
		
		// 1단계 대소문자 변환
		String temp = new_id.toLowerCase();		
		
		// 입력 받은 ID를 가공하기 위해 String builder에 담음
		StringBuilder sbID = new StringBuilder();
		for(int i=0; i<temp.length(); i++) {				
			sbID.append(temp.charAt(i));
		}
		if(debug) System.out.println(sbID);
		
		// 2단계 허가되는 글자 외에는 삭제
		for(int i=sbID.length()-1; i>=0; i--) {
			if(
					(sbID.charAt(i) < 'a' || sbID.charAt(i) > 'z')
					&& (sbID.charAt(i) <'A' || sbID.charAt(i) > 'Z')
					&& (sbID.charAt(i) < '0' || sbID.charAt(i) > '9')
					&& (sbID.charAt(i) != '-')
					&& (sbID.charAt(i) != '_')
					&& (sbID.charAt(i) != '.')
					){
						sbID.deleteCharAt(i);
					}
		}
		if(debug) System.out.println(sbID);
		
		// 3단계 마침표가 두번 이상 연속으로 나오는 경우 마침표 하나로 교체.
		int idx = sbID.indexOf("..");
		while(idx != -1) {
			sbID.deleteCharAt(idx);		
			idx = sbID.indexOf("..");
		} ;
		if(debug) System.out.println(sbID);
		
		// 4단계 마침표가 처음에 위치하면 마침표 제거.
		// 마지막에 위치한 마침표는 길이 조정후 어차피 다시 삭제할것이므로 이 단계에서는 생략.
		if(sbID.charAt(0) == '.') sbID.deleteCharAt(0);
		if(debug) System.out.println(sbID);
		
		// 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입.
		if(sbID.length() == 0) sbID.append('a');
		if(debug) System.out.println(sbID);
		
		// 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거.
 		// 만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		if(sbID.length() > 15) sbID.delete(15, sbID.length());		
		if(sbID.charAt(sbID.length()-1) == '.') sbID.deleteCharAt(sbID.length()-1);
		if(debug) System.out.println(sbID);
		
		// 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙인다
		while(sbID.length() < 3) {
			sbID.append(sbID.charAt(sbID.length()-1));
		}
		if(debug) System.out.println(sbID);
		
		String answer = sbID.toString();
        return answer;
    }
}