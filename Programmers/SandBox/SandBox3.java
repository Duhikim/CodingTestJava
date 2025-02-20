package CodingTestStudy.SandBox;

public class SandBox3 {

public static void main(String[] args) {
    ExClass ex = new ExClass();
    ex.count++;
    System.out.println(ex.count);
    ex.count++;
    System.out.println(ex.count);
}
}

class ExClass {
    int count = 0;
}
