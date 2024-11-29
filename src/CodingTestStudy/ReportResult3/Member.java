package CodingTestStudy.ReportResult3;

import java.util.HashSet;
import java.util.Set;

public class Member {
	private String ID;
	private int reportedCount;
	private boolean ban;
	private Set<String> reporters;
	private int mail;
	
	public Member(String ID){
		this.ID = ID;
		this.reportedCount=0;
		this.ban = false;
		this.reporters = new HashSet<>();
		this.mail = 0;
	}
	public void reportedBy(String reporter) {
		if(reporters.contains(reporter) == false) {
			this.reportedCount++;
			this.reporters.add(reporter);
		}
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getReportedCount() {
		return reportedCount;
	}
	public void setReportedCount(int reportedCount) {
		this.reportedCount = reportedCount;
	}
	public boolean isBan() {
		return ban;
	}
	public void setBan(boolean ban) {
		this.ban = ban;		
	}
	public Set<String> getReporters() {
		return reporters;
	}
	public void addReporters(String reporter) {
		reporters.add(reporter);
	}
	public void recieveMail() {
		this.mail++;
	}
	public int getMail() {
		return this.mail;
	}
}
