package CodingTestStudy.SortFilesByName;

import java.util.Arrays;

public class Solution {
	public static class FileName implements Comparable<FileName> {
		public String fileName;
		public String head;
		public String number;

		public FileName(String fileName){
			this.fileName = fileName;
			int idx = 0;
			while(fileName.charAt(idx) < '0' || fileName.charAt(idx) > '9'){idx++;}
			head = fileName.substring(0, idx).toUpperCase();
			while(idx < fileName.length() && fileName.charAt(idx) <= '9' && fileName.charAt(idx) >= '0'){idx++;}
			number = fileName.substring(head.length(), idx);
		}

		@Override
		public int compareTo(FileName o) {
			if(this.head.compareTo(o.head) != 0) return this.head.compareTo(o.head);
			return Integer.parseInt(this.number) - Integer.parseInt(o.number);
		}
		public String toString(){
			return fileName;
		}
	}

	public String[] solution(String[] files) {
		FileName[] fileNames = new FileName[files.length];
		for(int i = 0; i < files.length; i++){
			fileNames[i] = new FileName(files[i]);
		}
		Arrays.sort(fileNames);
		String[] answer = new String[files.length];
		for(int i = 0; i < files.length; i++){
			answer[i] = fileNames[i].fileName;
		}
		return answer;
	}
}
