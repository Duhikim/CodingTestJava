package CodingTestStudy.Level2.ParkingCost;

import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {


        Map<String, Integer> enter = new HashMap<>();
        Map<String, Integer> totalMinutes = new HashMap<>();

        for(String record: records){
            String[] str = record.split(" ");
            String[] str2 = str[0].split(":");
            int time = Integer.parseInt(str2[0]) * 60 + Integer.parseInt(str2[1]);
            String number = str[1];
            boolean in = str[2].equals("IN");

            if(in){
                enter.put(number, time);
            } else {
                int minutes = time - enter.get(number);
                totalMinutes.put(number, totalMinutes.getOrDefault(number, 0) + minutes);
                enter.remove(number);
            }
        }

        for(Map.Entry<String, Integer> es : enter.entrySet()){
            int out = 23*60 + 59;
            String number = es.getKey();
            int minutes = out - es.getValue();
            totalMinutes.put(number, totalMinutes.getOrDefault(number, 0)+minutes);

        }


        List<String> numbers = new ArrayList<>();
        for(Map.Entry<String, Integer> es : totalMinutes.entrySet()){
            numbers.add(es.getKey());
        }
        Collections.sort(numbers);

        int[] answer = new int[numbers.size()];

        for(int i=0; i<numbers.size(); i++){
            String number = numbers.get(i);
            int minutes = totalMinutes.get(number);
            int price = 0;
            if(minutes <= fees[0]){
                price = fees[1];
            } else{
                price = fees[1] + (((minutes - fees[0] - 1)/fees[2])+1) * fees[3];
            }
            answer[i] = price;
        }


        return answer;
    }
}