package CodingTestStudy.BadUsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
		System.out.println(sol.solution(user_id, banned_id));
	}
	public int solution(String[] user_id, String[] banned_id) {

		return getCombinations(makingExpectedBannedId(user_id, banned_id));
	}

	public ArrayList<ArrayList<String>> makingExpectedBannedId(String[] user_id, String[] banned_id){

		ArrayList<ArrayList<String>> expectedId = new ArrayList<>();

		for(int i=0; i<banned_id.length; i++){
			ArrayList<String> temp = new ArrayList<>();
			for(int j=0; j<user_id.length; j++){
				if(user_id[j].length() != banned_id[i].length()) continue;
				int len = user_id[j].length();
				for(int k=0; k< len; k++){
					if(user_id[j].charAt(k) != banned_id[i].charAt(k) && banned_id[i].charAt(k) != '*') break;
					if(k==len-1){
						//통과
						temp.add(user_id[j]);
					}
				}
			}
			expectedId.add(temp);
		}

		return expectedId;
	}

	public int getCombinations(ArrayList<ArrayList<String>> expectedId){
		Set<Set<String>> combinationSet = new HashSet<>();

		//expectedId.get(0)에서 한개, expectedId.get(1)에서 한개, expectedId.get(2)에서 한개씩 뽑아서 조합을 만들거야.
		//DFS로 해야할듯
		int len = expectedId.size();
		Set<String> temp = new HashSet<>();
		DFS(0, len, temp, expectedId, combinationSet);

		return combinationSet.size();
	}
	public void DFS(int idx, int len,
					Set<String> temp,
					ArrayList<ArrayList<String>> expectedId,
					Set<Set<String>> combinationSet)
	{
		if(idx==len){
			if(temp.size()==len) combinationSet.add(new HashSet<>(temp));
			return;
		}

		for(int i=0; i<expectedId.get(idx).size(); i++){
			if(temp.contains(expectedId.get(idx).get(i))) continue;
			temp.add(expectedId.get(idx).get(i));
			DFS(idx+1, len, temp, expectedId, combinationSet);
			temp.remove(expectedId.get(idx).get(i));
		}
	}
}