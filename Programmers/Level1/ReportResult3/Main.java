package CodingTestStudy.Level1.ReportResult3;

public class Main {

	public static void main(String[] args) {
		
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo",
				"muzi neo","apeach muzi"};
		int k = 2;
		int[] expected = {2,1,1,0};
		for(int i: expected) {System.out.print(i + " ");}
		System.out.println(" ");
		int[] calculated = Solution.solution(id_list, report, k);		
		for(int i: calculated) {System.out.print(i + " ");}
		System.out.println(" ");
		
		
		

	}

}
