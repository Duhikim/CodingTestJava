package CodingTestStudy.Level3.BestAlbum;

import java.util.*;

public class Solution {

    class Song implements Comparable<Song>{
        int id;
        int plays;

        public Song(int id, int plays){
            this.id = id;
            this.plays = plays;
        }
        @Override
        public int compareTo(Song o) {
            return o.plays - this.plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        // 장르 순서 정하기
        Map<String, Integer> genrePlays = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            genrePlays.put(genres[i], genrePlays.getOrDefault(genres[i], 0) + plays[i]);
        }
        List<Map.Entry<String, Integer>> listOfGenre_Plays = new ArrayList<>(genrePlays.entrySet());
        listOfGenre_Plays.sort((el1, el2) -> el2.getValue().compareTo(el1.getValue()));
        // 이제 entryList는 플레이 횟수순으로 정렬이 되었고 getKey를 통해 횟수가 높은 장르부터 접근할 수 있음.

        Map<String, List<Song>> mapOfGenre_Songs = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            Song song = new Song(i, plays[i]);
            if(!mapOfGenre_Songs.containsKey(genres[i])){
                mapOfGenre_Songs.put(genres[i], new ArrayList<>());
            }
            mapOfGenre_Songs.get(genres[i]).add(song);
        }
        for(Map.Entry<String, List<Song>> es: mapOfGenre_Songs.entrySet()){
            Collections.sort(es.getValue());
        }

        List<Integer> answer = new ArrayList<>();
        for(Map.Entry<String, Integer> el: listOfGenre_Plays){
            String genre = el.getKey();
            for(int i=0; i<Math.min(2, mapOfGenre_Songs.get(genre).size()); i++){
                int id = mapOfGenre_Songs.get(genre).get(i).id;
                answer.add(id);
            }
        }
        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}