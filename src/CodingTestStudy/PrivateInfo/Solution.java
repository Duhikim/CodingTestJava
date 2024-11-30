package CodingTestStudy.PrivateInfo;

import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public static int[] solution(String today, String[] terms, String[] privacies) {
        
        Privacies.termsData(terms);
        DataManager manager = new DataManager();
                
        int dataNum = privacies.length;        
        for(int i=0; i<dataNum; i++) {
        	String[] date_kind = privacies[i].split(" ");        	
        	String[] date = date_kind[0].split("\\.");
        	Privacies prv = new Privacies(i+1, Integer.parseInt(date[0]), 
        			Integer.parseInt(date[1]), Integer.parseInt(date[2]), date_kind[1], false);
        	manager.addInfo(i+1, prv);
        }
        
        for(int i=0; i<dataNum; i++) {
        	manager.findWithIdx(i+1).updateExpire(today);
        }
        
        ArrayList<Integer> arr = manager.getExpiredIdx();
        
        int[] answer = new int[arr.size()];
        for(int i=0; i<arr.size(); i++) {
        	answer[i] = arr.get(i);
        }
        return answer;
    }
    
    public static class Privacies{
    	private int infoIndex;
    	private int sYear, sMonth, sDay;
    	private String kind;
    	private int eYear, eMonth, eDay;
    	private boolean Expired;
    	
    	///// static 멤버
    	private static HashMap<String,Integer> expiredPeriod = new HashMap<>();
    	
    	public static void termsData(String[] terms) {
    		for(int i=0; i<terms.length; i++) {
    			String[] str = terms[i].split(" ");
    			expiredPeriod.put(str[0], Integer.parseInt(str[1]));
    		}    		
    	}    
    	
    	public static int getTerm(String C) {
    		return (int)expiredPeriod.get(C);
    	}
    	
    	
    	// 생성자
    	public Privacies(int infoIndex, int sYear, int sMonth, int sDay, String kind, boolean expired) {			
			this.infoIndex = infoIndex;
			this.sYear = sYear;
			this.sMonth = sMonth;
			this.sDay = sDay;
			this.kind = kind;
			this.Expired = expired;
			
			this.eDay = sDay;
			this.eMonth = sMonth + getTerm(kind);
			this.eYear = sYear;
			while(eMonth>12) {
				eYear++;
				eMonth -= 12;
			}
		} 	

		public void updateExpire(String today) {
						
			String[] todayDate = today.split("\\.");
			int todayYear = Integer.parseInt( todayDate[0]);
			int todayMonth = Integer.parseInt( todayDate[1]);
			int todayDay = Integer.parseInt( todayDate[2]);
			
			if(todayYear > this.eYear) { this.Expired = true; return;}
			if(todayYear < this.eYear) { this.Expired = false; return;}
			if(todayMonth > this.eMonth) { this.Expired = true; return; }
			if(todayMonth < this.eMonth) { this.Expired = false; return; }
			if(todayDay >= this.eDay) { this.Expired = true; return; }
			this.Expired = false;
    	}
		
		public boolean isExpired() {
			return this.Expired;
		}
    }
    
    public static class DataManager{    	
    	private HashMap<Integer, Privacies> informations;    	
    	
    	public DataManager(){    		
    		this.informations = new HashMap<>();
    	}
    	public void addInfo(int idx, Privacies privacy) {    		
    		this.informations.put(idx, privacy);
    	}
    	
    	public Privacies findWithIdx(int idx) {
    		return this.informations.get(idx);
    	}
    	public ArrayList<Integer> getExpiredIdx(){
    		ArrayList<Integer> ExpiredIdx = new ArrayList<>();
    		
    		for(int i=0; i<informations.size(); i++) {
    			if(this.informations.get(i+1).isExpired()) {
    				ExpiredIdx.add(i+1);
    			}
    		}    		
    		return ExpiredIdx;
    	}
    }
}