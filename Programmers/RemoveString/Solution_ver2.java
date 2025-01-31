package CodingTestStudy.RemoveString;

import java.util.ArrayDeque;

public class Solution_ver2
{
	public int solution(String s)
	{
		ArrayDeque<Character> deque = new ArrayDeque<>();

		for(int i=0; i<s.length(); i++){
			if(deque.isEmpty() || !deque.getLast().equals(s.charAt(i))){
				deque.add(s.charAt(i));
			}
			else {
				deque.removeLast();
			}
		}

		return (deque.isEmpty())?1: 0;
	}
}