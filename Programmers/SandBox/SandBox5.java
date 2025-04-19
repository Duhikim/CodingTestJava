package CodingTestStudy.SandBox;

class Alpha {
	int x = 10;
	Alpha() {
		System.out.print("A");
		this.show();
	}
	void show() {
		System.out.print(x);
	}
}
class Beta extends Alpha {
	int x = 20;
	Beta() {
		System.out.print("D");
		super.show();
		this.show();
	}
	@Override
	void show() {
		System.out.print(x);
	}
}

public class SandBox5 {
	public static void main(String[] args) {
		Alpha a = new Beta();
	}
}
