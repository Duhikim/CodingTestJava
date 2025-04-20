package CodingTestStudy.SandBox;

class Parent_7 {
	int v = 0;
	static int total = 0;
	public Parent_7(){
		v++;
		total += ++v;
		calculate();
	}
	public void calculate(){
		total += total;
	}
}

class Child_7 extends Parent_7{
	int v = 10;

	public Child_7(){
		v+= 2;
		total += (v++);
		calculate();
	}
	@Override
	public void calculate(){
		total += total * 2;
	}
}


public class SandBox7 {
	public static void main(String[] args) {
		new Child_7();
		System.out.println(Parent_7.total);
	}
}
