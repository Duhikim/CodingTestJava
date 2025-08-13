package CodingTestStudy.Level2.GroupPhoto;


public class Main {

	public static void main(String[] args) {
	
		int n=2;
		String[] data = {"N~F=0", "R~T>2"};
		int Expected = 3648;
		int Calculated = Solution.solution(n,  data);
		if(Calculated==Expected) System.out.println("Correct");
		else {
			System.out.println(Expected);
			System.out.println(Calculated);
			System.out.println("Wrong");
		}
		
//		int n=100;
//		String[] data = {"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7",
//				"N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7", "N~F=0", "R~T>2", "M~C<2", "C~M>1", "A~C<7"				
//		};
//		int Expected = 0;
//		int Calculated = Solution.solution(n,  data);
//		if(Calculated==Expected) System.out.println("Correct");
//		else {
//			System.out.println(Expected);
//			System.out.println(Calculated);
//			System.out.println("Wrong");
//		}
		
		
	}

}
