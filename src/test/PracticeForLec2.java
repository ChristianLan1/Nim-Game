package test;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class PracticeForLec2 {

	public static void main(String[] args) {
		double price = 1.4;
		//System.out.printf("%6.2f",price);
		System.out.printf("ha%6.2f", price);
		System.out.println();
		double value = 12.123;
		System.out.printf("Start%8.2fEnd", value);
		System.out.println();
		System.out.printf("Start%-8.2fEnd", value);
		System.out.println();
		
		double d = 12345.123456789;
		System.out.printf("START%12.4fEND %n",d);
	//The following codes are money format	
		System.out.println("Default location:");
		 NumberFormat moneyFormater =
		 NumberFormat.getCurrencyInstance();
		 System.out.println(moneyFormater.format(19.8));
		 System.out.println(moneyFormater.format(19.81111));
		 System.out.println(moneyFormater.format(19.89999));
		 System.out.println(moneyFormater.format(19));
		 System.out.println();
		 
		 System.out.println("CHN as location:");
		 NumberFormat moneyFormater2 = NumberFormat.getCurrencyInstance(Locale.CHINA);
		 System.out.println(moneyFormater2.format(19.8));
		 System.out.println(moneyFormater2.format(19.81111));
		 System.out.println(moneyFormater2.format(19.89999));
		 System.out.println(moneyFormater2.format(19));
	// Illustration for Scanner	 
		 Scanner keyboard = new Scanner(System.in);
		 System.out.println("Please type ur name");
		 String name = keyboard.nextLine();
		 System.out.println("Hello "+name);
		 keyboard.close();
	}

}
