package CodingTestStudy.SecretMap;

public class Main {

	public static void main(String[] args) {
		// 1번 Test Case
		int n = 5;
		int[] arr11 = {9, 20, 28, 18, 11};
		int[] arr12 = {30, 1, 21, 17, 28};
		String[] expected1 = {"#####","# # #", "### #", "#  ##", "#####"};		
		String[] calculated1 = Solution.solution(n, arr11, arr12);
		for(int i=0; i<n; i++) {
			if(expected1[i].equals(calculated1[i])) System.out.println("Correct!"); else System.out.println("Wrong!");
		}
		
		
		// 2번 Test Case
		n = 6;
		int[] arr21 = {46, 33, 33 ,22, 31, 50};
		int[] arr22 = {27 ,56, 19, 14, 14, 10};
		String[] expected2 = {"######", "###  #", "##  ##", " #### ", " #####", "### # "};		
		String[] calculated2 = Solution.solution(n, arr21, arr22);
		for(int i=0; i<n; i++) {
//			System.out.println(expected2[i]);
//			System.out.println(calculated2[i]);
			if(expected2[i].equals(calculated2[i])) System.out.println("Correct!"); else System.out.println("Wrong!");
				}

	}

}
