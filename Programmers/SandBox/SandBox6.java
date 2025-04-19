package CodingTestStudy.SandBox;

class Parent {
	protected int x = 3;
	int getX(){
		return x*2;
	}
}

class Child extends Parent{
	int y=7;
	int getX(){
		return this.x;
	}
}

public class SandBox6 {
	public static void main(String[] args) {
		Child obj = new Child();

		System.out.println("obj.getX(): " + obj.getX());

	}
}
