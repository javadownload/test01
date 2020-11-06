package day14;

import java.util.Arrays;

public class Test02 {
	public static void main(String[] args) throws CloneNotSupportedException {
		int[] num = {11,22,33,44,55};
		//int[] num2 = num;
		int[] num2 = num.clone();
		num2[2] = 0;
		System.out.println(num);
		System.out.println(num2);
		System.out.println(Arrays.toString(num));
		System.out.println(Arrays.toString(num2));
        System.out.println("-----------------------------");
        Point p = new Point(5, 5);
        Point p2 = p.clone() ;
        Object obj;
        p.setX(0);
        System.out.println(p);
        System.out.println(p2);
		
        System.out.println("================");
       
	}
}







