package test;
import java.util.Scanner;
import java.lang.Math;

public class Lab3 {

	public static void main(String[] args) {
		/*Scanner Keyboard = new Scanner(System.in);
		System.out.println("Please enter two floats");
		float num1 = Keyboard.nextFloat();
		float num2 = Keyboard.nextFloat();
		float sum = num1+num2;
		float difference = num1-num2;
		float product = num1*num2;
		System.out.println("sum: "+sum);
		System.out.println("difference: "+difference);
		System.out.println("product "+product);
		System.out.println();
		System.out.println("enter an radius to calculate volume");
		double r = Keyboard.nextDouble();
		double pi = Math.PI;
		double cubic = Math.pow(r, 3);
		double constant = 4.0/3;
		//System.out.println(constant);
		double volume = constant*pi*cubic;
		System.out.println("volume is "+volume);
		System.out.println("enter an radius to calculate surface area");
		double r2 = Keyboard.nextDouble();
		double rsquare = Math.pow(r2, 2);
		double SA = 4*pi*rsquare;
		System.out.println("surface area is "+SA);*/
		System.out.println("enter the hour you worked");
		int hour = Integer.parseInt(args[0]);
		double wage;
		if(hour<40){
			wage = 8.25*hour;
		}
		else{
			wage = 40*8.25+(hour-40)*8.25*1.5;
		}
		System.out.println("ur wage is "+wage);
				

	}

}
