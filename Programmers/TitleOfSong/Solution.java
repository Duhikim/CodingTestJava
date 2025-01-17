package CodingTestStudy.TitleOfSong;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
	public class Songs implements Comparable<Songs> {
		String melody; // #은 소문자로 구분하려고 함
		int startTime; // 단위: 분
		int endTime;
		int runtime;
		String title;
		boolean thisSong;

		public Songs(String m, String info){
			String[] infos = info.split(",");
			this.startTime = (Integer.parseInt(infos[0].substring(0,2)))*60 + Integer.parseInt(infos[0].substring(3,5));
			this.endTime = (Integer.parseInt(infos[1].substring(0,2)))*60 + Integer.parseInt(infos[1].substring(3,5));
			this.runtime = endTime - startTime;
			this.title = infos[2];
			this.melody = "";
			infos[3] = convertMelodies(infos[3]);
			for(int i=0; i<runtime; i++){
				this.melody += infos[3].charAt(i%infos[3].length());
			}
			this.thisSong = melody.contains(m);
		}

		@Override
		public int compareTo(Songs o) {
			if(this.runtime != o.runtime) return o.runtime - this.runtime;
			return this.startTime - o.startTime;
		}
		public String toString(){return title;}
	}

	public String convertMelodies(String melody){
//		StringBuilder sb = new StringBuilder(melody);
//		for(int i=sb.length()-1; i>=0; i--){
//			if(sb.charAt(i) == '#') {
//				sb.setCharAt(i-1, (char)(sb.charAt(i-1)-'A'+'a'));
//				sb.deleteCharAt(i);
//				i--;
//			}
//		}
//		return sb.toString();

		return melody.replaceAll("C#", "c")
				.replaceAll("D#", "d")
				.replaceAll("F#", "f")
				.replaceAll("G#", "g")
				.replaceAll("A#", "a")
				.replaceAll("B#", "b"); // B#은 존재하지 않으나 프로그래머스 테스트케이스에 들어가있음
	}

	public String solution(String m, String[] musicinfos) {
		ArrayList<Songs> songs = new ArrayList<>();
		ArrayList<Songs> answers = new ArrayList<>();

		m = convertMelodies(m);


		for(int i=0; i<musicinfos.length; i++){
			songs.add(new Songs(m, musicinfos[i]));
			if(songs.get(i).thisSong) answers.add(songs.get(i));
		}
		if(answers.isEmpty()) return "(None)";

		Collections.sort(answers);
		return answers.get(0).toString();
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("CDCDCD", new String[]{"12:00,12:10,NAME,CDCDCDE", "12:30,12:50,NA,CD"}));
	}
}