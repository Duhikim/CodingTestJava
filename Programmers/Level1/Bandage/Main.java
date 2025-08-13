package CodingTestStudy.Level1.Bandage;

public class Main {

	public static void main(String[] args) {
	
//	int[] bandage = {5,1,5}; 
//	int health = 30;
//	int[][] attacks	=  {{2, 10}, {9, 15}, {10, 5}, {11, 5}};
//	int expected = 5;
//	int calculated = Solution.solution(bandage, health, attacks);	
//	if(expected == calculated) {
//		System.out.println("Correct");
//	} else System.out.println("Wrong");
		
		
//		int[] bandage = {3, 2, 7}; 
//		int health = 20;
//		int[][] attacks	=  {{1, 15}, {5, 16}, {8, 6}};
//		int expected = -1;
//		int calculated = Solution.solution(bandage, health, attacks);	
//		if(expected == calculated) {
//			System.out.println("Correct");
//		} else System.out.println("Wrong");	
//	
		
//		int[] bandage = {4, 2, 7}; 
//		int health = 20;
//		int[][] attacks	=  {{1, 15}, {5, 16}, {8, 6}};
//		int expected = -1;
//		int calculated = Solution.solution(bandage, health, attacks);	
//		if(expected == calculated) {
//			System.out.println("Correct");
//		} else System.out.println("Wrong");	
//	
		
		int[] bandage = {1,1,1}; 
		int health = 5;
		int[][] attacks	=  {{1, 2}, {3,2}};
		int expected = 3;
		int calculated = Solution.solution(bandage, health, attacks);	
		if(expected == calculated) {
			System.out.println("Correct");
		} else System.out.println("Wrong");	
	
	}

}
