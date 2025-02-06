package CodingTestStudy.BadUsers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Solution_ver2 {
	public int solution(String[] user_id, String[] banned_id) {
		return makingCombinationSet(makingBanCandidates(user_id, banned_id)).size();
	}

	public void DFS(int idx, int endIdx,
					ArrayList<ArrayList<String>> banIdCandidates,
					Set<Set<String>> banIdCombinationSet,
					Set<String> combSetCandidate)
	{
		if(idx == endIdx){
			banIdCombinationSet.add(new HashSet<>(combSetCandidate)); // 여기서 깊은복사를 해야 함.
			return;
		}

		for(int i=0; i< banIdCandidates.get(idx).size(); i++){
			if(combSetCandidate.contains(banIdCandidates.get(idx).get(i))) continue;
			combSetCandidate.add(banIdCandidates.get(idx).get(i));
			DFS(idx+1, endIdx, banIdCandidates, banIdCombinationSet, combSetCandidate);
			combSetCandidate.remove(banIdCandidates.get(idx).get(i));
		}
	}

	// 정지될 아이디 후보리스트들을 DFS 전체탐색하여 가능한 조합을 만들어내는 함수.
	public Set<Set<String>> makingCombinationSet(ArrayList<ArrayList<String>> banIdCandidates){
		Set<Set<String>> banIdCombinationSet = new HashSet<>();
		Set<String> combSetCandidate = new HashSet<>();

		DFS(0, banIdCandidates.size(), banIdCandidates, banIdCombinationSet, combSetCandidate);

		return banIdCombinationSet;
	}

	// banned_id 배열을 기준으로 정지될 아이디 후보 리스트를 2차원 배열로 생성.
	public ArrayList<ArrayList<String>> makingBanCandidates (String[] user_id, String[] banned_id){
		ArrayList<ArrayList<String>> banIdCandidates = new ArrayList<>();

		for(String banId: banned_id){
			ArrayList<String> banIdCandidate = new ArrayList<>();
			for(String userId: user_id){
				if(isTheseSame(userId, banId)) banIdCandidate.add(userId);
			}
			banIdCandidates.add(banIdCandidate);
		}
		return banIdCandidates;
	}

	// 두 아이디가 같은지 검사하여 맞으면 true, 다르면 false 반환하는 함수
	public boolean isTheseSame(String id1, String id2){
		if(id1.length() != id2.length()) return false;

		for(int i=0; i<id1.length(); i++){
			if(id1.charAt(i) != id2.charAt(i)
					&& id2.charAt(i) != '*'
					&& id1.charAt(i) != '*') return false;
		}
		return true;
	}
}