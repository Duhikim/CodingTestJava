package CodingTestStudy.SandBox;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class SandBox {
    public static void main(String[] args) {
        
//        ArrayList<Integer> arrList = new ArrayList<>();
//        arrList.add(1);
//        arrList.add(2);
//        arrList.add(3);
//        arrList.add(4);
//        arrList.add(5);
//        System.out.println("arrList : ");
//        System.out.println(arrList);
//        
//        ArrayList<Integer> otherList = arrList;
//        System.out.println("otherList : ");
//        System.out.println(otherList);
//        
//        arrList.remove(4);
//        System.out.println("arrList : ");
//        System.out.println(arrList);
//        System.out.println("otherList : ");
//        System.out.println(otherList);
//        
//        System.out.println("otherList == arrList? ");
//        System.out.println(otherList == arrList);
//        
//        ArrayList<Integer> cloneList = (ArrayList<Integer>) arrList.clone();
//        arrList.remove(0);
//        System.out.println("arrList : ");
//        System.out.println(arrList);
//        System.out.println("cloneList : ");
//        System.out.println(cloneList);
//        
//        System.out.println("cloneList == arrList? ");
//        System.out.println(cloneList == arrList);
        
        ArrayDeque<Integer> arrDeque = new ArrayDeque<>();
        arrDeque.add(1);
        arrDeque.add(2);
        arrDeque.add(3);
        arrDeque.add(4);
        arrDeque.add(5);
        System.out.println("arrDeque : ");
        System.out.println(arrDeque);
        
        ArrayDeque<Integer> otherDeque = arrDeque;
        System.out.println("otherDeque : ");
        System.out.println(otherDeque);
        
        arrDeque.removeLast();
        System.out.println("arrDeque : ");
        System.out.println(arrDeque);
        System.out.println("otherDeque : ");
        System.out.println(otherDeque);
        
        System.out.println("otherDeque == arrDeque? ");
        System.out.println(otherDeque == arrDeque);
        
        ArrayDeque<Integer> cloneDeque = (ArrayDeque<Integer>) arrDeque.clone();
//        arrDeque.removeFirst();
        System.out.println("arrDeque : ");
        System.out.println(arrDeque);
        System.out.println("cloneDeque : ");
        System.out.println(cloneDeque);
        
        System.out.println("cloneDeque == arrDeque? ");
        System.out.println(cloneDeque == arrDeque);
        
    }



}
