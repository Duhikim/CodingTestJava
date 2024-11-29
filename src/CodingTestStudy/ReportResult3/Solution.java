package CodingTestStudy.ReportResult3;

class Solution {		
    public static int[] solution(String[] id_list, String[] report, int k) {
       int memNum = id_list.length;
       MemberManager manager = new MemberManager();
       
       for(int i=0; i<memNum; i++) {    	   
    	   manager.addMember(new Member(id_list[i]));
       }
       
       for(int i=0; i<report.length; i++) {
    	   String[] name = report[i].split(" "); // name[0]는 신고인, name[1]은 피신고인
    	   // 피신고인으로 검색해서 신고 누적 횟수를 1 올려주고 신고자 목록에 신고인 추가.
    	   Member reported_mem = manager.memberByID(name[1]);
    	   reported_mem.reportedBy(name[0]);
       }
       // 밴 처리
       for(int i=0; i<memNum; i++) {
    	   Member mem = manager.memberByID(id_list[i]);
    	   if(mem.getReportedCount() >= k) {
    		   mem.setBan(true);
    		   for(String reporter: mem.getReporters()) {
    			   manager.memberByID(reporter).recieveMail();
    		   }
    	   }
       }
       int[] answer = new int[memNum];
       for(int i=0; i<memNum; i++) {
    	   answer[i] = manager.memberByID(id_list[i]).getMail();
       }
       return answer;       
    }
}