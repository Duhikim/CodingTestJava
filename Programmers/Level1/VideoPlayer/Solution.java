package CodingTestStudy.Level1.VideoPlayer;

class Solution {
public class Time implements Comparable<Time>{
int minute;
int second;

public Time(String str)  {
    String[] time = str.split(":");
    this.minute = Integer.parseInt(time[0]);
    this.second = Integer.parseInt(time[1]);
}
public void next(){
    this.second += 10;
    if(this.second >= 60){
        this.minute += 1;
        this.second -= 60;
    }
}
public void setTo(Time t){
    this.minute = t.minute;
    this.second = t.second;
}
public void prev(){
    this.second -= 10;
    if(this.second < 0){
        this.minute -= 1;
        this.second += 60;
    }
    if(this.minute < 0){
        this.minute = 0;
        this.second = 0;
    }
}
public String toString(){
    return String.format("%02d:%02d", this.minute, this.second);
}
    
    @Override
    public int compareTo(Time o) {
        if(this.minute > o.minute){
            return 1;
        }else if(this.minute < o.minute){
            return -1;
        }
        else{
            if(this.second > o.second){
                return 1;
            }else if(this.second < o.second){
                return -1;
            }
        }
        return 0;
    }
}

public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

    Time videoLen = new Time(video_len);
    Time position = new Time(pos);
    Time opStart = new Time(op_start);
    Time opEnd = new Time(op_end);
    
    for(String cmd: commands){
        if(position.compareTo(opStart) >= 0 && position.compareTo(opEnd) <= 0){
            position.setTo(opEnd);
        }
        
        if(cmd.equals("next")) {
            position.next();
            if(position.compareTo(videoLen) > 0){
                position.setTo(videoLen);
            }
        }
        else{
            position.prev();
        }
    }
    if(position.compareTo(opStart) >= 0 && position.compareTo(opEnd) <= 0){
        position.setTo(opEnd);
    }
    
    return position.toString();
}
}