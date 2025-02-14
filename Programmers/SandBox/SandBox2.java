package CodingTestStudy.SandBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.text.ChoiceFormat.nextDouble;

public class SandBox2 {
	static class Parent {
		int x, y;

		Parent(int x, int y) {
			this.x=x;
			this.y=y;
		}

		int getT() {
			return x*y;
		}
	}
	static class Child extends Parent {
		int x;

		Child (int x) {
			super(x+1, x);
			this.x=x;
		}

		int getT(int n){
			return super.getT()+n;
		}
	}
	public static void main(String[] args) {
//		Parent parent = new Child(3);
//		System.out.println(parent.getT());
		Random random = new Random();

		int[] arr = new int[100];
		for(int i=0; i<100; i++){
			arr[i] = (i* (int)(random.nextDouble()*100.0));
		}
		Map<Integer, Integer> myMap = new HashMap<>();
		for(int i=0; i<100; i++){
			myMap.put(arr[i], myMap.getOrDefault(myMap.get(arr[i]), 0)+1);
		}
		for(Map.Entry<Integer, Integer> es: myMap.entrySet()){
			System.out.println(es.getKey() + " , " + es.getValue());
		}
	}

}
