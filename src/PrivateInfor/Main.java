package PrivateInfor;

public class Main {

	public static void main(String[] args) {
		
//		String today = "2022.05.19";
//		String[] terms 	= {"A 6", "B 12", "C 3"};
//		String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
//		int[] Expected = {1, 3};
//		int[] calculated = Solution.solution(today, terms, privacies);
//		
//		for(int num: Expected) {
//			System.out.print(num + " ");
//		}
//		System.out.println(" ");
//		for(int num: calculated) {
//			System.out.print(num + " ");
//		}
		
		String today = "2020.01.01";
		String[] terms 	= {"Z 3", "D 5"};
		String[] privacies = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
		int[] Expected = {1, 4, 5};
		int[] calculated = Solution.solution(today, terms, privacies);
		
		for(int num: Expected) {
			System.out.print(num + " ");
		}
		System.out.println(" ");
		for(int num: calculated) {
			System.out.print(num + " ");
		}

	}

}
