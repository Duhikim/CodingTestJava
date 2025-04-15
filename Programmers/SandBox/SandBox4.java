package CodingTestStudy.SandBox;

class Base{
	int x = 3;
	int getX(){
		return x*2;
	}
}

class Derivate extends Base{
	int x=7;
	int getX(){
		return x*3;
	}
}

public class SandBox4 {
	public static void main(String[] args) {
		Base b = new Derivate();
		Derivate d = new Derivate();
		System.out.println(b.getX()+b.x+d.getX()+d.x);
	}

}
