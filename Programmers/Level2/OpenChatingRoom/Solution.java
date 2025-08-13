package CodingTestStudy.Level2.OpenChatingRoom;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution {
	public String[] solution(String[] record) {


		HashMap<String, String> members = new HashMap<>(); // Id, Nickname
		ArrayDeque<String> logs = new ArrayDeque<>();

		for(String rec: record){
			String[] str = rec.split(" "); // str[0] : 행동, str[1] : ID, str[2] : 닉네임
			if(str[0].equals("Enter")){
				members.put(str[1], str[2]);
				logs.add(str[1]+" Enter");
			}
			else if(str[0].equals("Leave")){
				logs.add(str[1]+" Leave");
			}
			else if(str[0].equals("Change")){
				members.put(str[1], str[2]);
			}
			else{
				System.out.println("Input error");
			}
		}

		String[] answer = new String[logs.size()];
		int i=0;

		for(String log: logs){
			String[] str = log.split(" "); // str[0] : ID, str[1] : 행동
			if(str[1].equals("Enter")){
				answer[i] = members.get(str[0]) + "님이 들어왔습니다.";
			}
			else if(str[1].equals("Leave")){
				answer[i] = members.get(str[0]) + "님이 나갔습니다.";
			}
			else{
				System.out.println("Log error");
			}
			i++;
		}

		return answer;
	}
}