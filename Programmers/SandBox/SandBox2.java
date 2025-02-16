package CodingTestStudy.SandBox;

public class SandBox2 {
	public static void main(String[] args) {
		Integer zero = 0;
		new GenericTest<>(zero).print();
	}
}

class Printer{
	void print(Integer a){
		System.out.println("A" + a);
	}
	void print(Object a){
		System.out.println("B" + a);
	}
	void print(Number a){
		System.out.println("C" + a);
	}
}

class GenericTest<T> {
	T value;
	public GenericTest(T t){
		value =t;
	}
	public void print(){
		new Printer().print(value);
	}
}
