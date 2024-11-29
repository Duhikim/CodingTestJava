package BaekJoon.Q2675;



import java.io.*;
public class Q2675_RepeatString {

	public static void main(String[] args) throws IOException {
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		int testCase = Integer.parseInt(str);
		
		
		while(testCase-- != 0) {			
			StringBuilder S = new StringBuilder();
			str = br.readLine();
			String regex = " ";
			String[] strArr = str.split(regex);
			int repeatTime = Integer.parseInt(strArr[0]);
			String word = strArr[1];
			int len = word.length();
			for(int i=0; i<len; i++) {
				for(int j=0; j<repeatTime; j++) {
					S.append(word.charAt(i));					
				}
			}
			System.out.println(S.toString());
		}
	}
}
