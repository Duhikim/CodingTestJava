package CodingTestStudy.ReportResult2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
class Solution {		
    public static int[] solution(String[] id_list, String[] report, int k) {
        // 2회 신고해도 1회로 처리하기 때문에 report의 중복항을 삭제하는 작업부터 시작. report는 순서 꼬여도 상관 없음
    	HashSet<String> report_set = new HashSet<>(Arrays.asList(report));
    	String[] report_unique = report_set.toArray(new String[0]);
        
    	// report를 신고인, 피신고인으로 나눠서 새로운 ArrayList에 저장.    	
    	ArrayList<ArrayList<String>> report_split = new ArrayList<>();        
    	
        for(int i=0; i<report_unique.length; i++) {
        	String[] name = report_unique[i].split(" ");
        	ArrayList<String> temp = new ArrayList<>();
        	temp.add(name[0]);
        	temp.add(name[1]);
        	report_split.add(temp);        	        	
        }
        
        HashMap<String, Integer> idMap = new HashMap<>(); // ID, 결과 수
        for(String id: id_list) {
        	idMap.put(id,  0);
        }
        
        while(report_split.size() != 0) {        	 
        	ArrayList<Integer> idxs = new ArrayList<>();
        	String t_name = report_split.get(0).get(1);
        	        	
        	for(int i=0; i<report_split.size(); i++) {
        		if(t_name.equals(report_split.get(i).get(1))) {
        			idxs.add(i);        			
        		}
        	}
        	
        	if(idxs.size() >= k) {
        		// idx로 신고자 이름 찾아서 hashmap
        		for(int idx: idxs) {
        			String name = report_split.get(idx).get(0);
        			idMap.put(name,  idMap.get(name)+1);
        		}        		
        	}       	
        	
        	for(int i=idxs.size()-1; i>=0; i--) {
        		report_split.remove((int)idxs.get(i));
        		
    		}
        	idxs.clear();        	
        }
        
        
        int[] answer = new int[id_list.length];
        
        for(int i=0; i<id_list.length; i++) {
        	answer[i] = idMap.get(id_list[i]);
        }
        
        return answer;
    }
    
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