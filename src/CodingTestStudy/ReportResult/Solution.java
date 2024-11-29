package CodingTestStudy.ReportResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


class Solution {		
    public static int[] solution(String[] id_list, String[] report, int k) {
        // 2회 신고해도 1회로 처리하기 때문에 report의 중복항을 삭제하는 작업부터 시작. report는 순서 꼬여도 상관 없음
    	HashSet<String> report_set = new HashSet<>(Arrays.asList(report));
    	String[] report_unique = report_set.toArray(new String[0]);
    	
    	int[] answer = new int[id_list.length];
        
    	// report를 신고인, 피신고인으로 나눠서 새로운 array에 저장.
    	String[][] report_split = new String[report_unique.length][2];
    	// ID별로 신고 받은 횟수를 value로 갖는 HashMap 생성
        HashMap<String, Integer> reportedList = new HashMap<String, Integer>(id_list.length, 1.0f);        
        
        for(int i=0; i<report_unique.length; i++) {
        	String[] name = report_unique[i].split(" ");        	
        	report_split[i][0] = name[0];
        	report_split[i][1] = name[1];
        	reportedList.put(name[1], reportedList.getOrDefault(name[1], 0) +1); // name[1]이 map에 없으면 추가, 있으면 value 1 증가
        }

        // 정지 당하는 ID의 리스트
        ArrayList<String> banList = new ArrayList<String>(id_list.length);
        
        for(Map.Entry<String, Integer> entry : reportedList.entrySet()) {
        	if(entry.getValue() >= k) {
        		banList.add(entry.getKey());
        	}
        }
        
        // 정지 당한 ID를 신고한 ID의 리스트와 신고 횟수. 기 횟수가 answer에 담길 예정.
        HashMap<String, Integer> reporters = new HashMap<String, Integer>(id_list.length, 1.0f);
        
        for(String banName: banList) {
        	for(String[] name: report_split) {
        		if(name[1].equals(banName)) {
        			reporters.put(name[0],  reporters.getOrDefault(name[0],  0) +1);
        		}
        	}
        }
        
        for(int i=0; i<id_list.length; i++) {
        	answer[i] = reporters.getOrDefault(id_list[i], 0);
        }
        
        return answer;
    }
}