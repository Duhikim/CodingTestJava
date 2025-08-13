package CodingTestStudy.Level1.ReportResult3;

import java.util.HashMap;

public class MemberManager {
	private HashMap<String, Member> members;

	public MemberManager() {
		members = new HashMap<>();
	}
	
	public void addMember(Member member) {
		members.put(member.getID(), member);
	}
	
	public Member memberByID(String ID) {
		return members.get(ID);
	}
	public HashMap<String, Member> getMembers(){
		return members;
	}
}
