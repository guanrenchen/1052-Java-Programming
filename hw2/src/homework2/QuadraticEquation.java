package homework2;

import java.lang.Math;
import java.text.DecimalFormat;
import java.util.Scanner;

public class QuadraticEquation {

	public static void main(String[] args) {
		try(
			Scanner sc = new Scanner(System.in);
		){
			double 	a = sc.nextDouble(), 
					b = sc.nextDouble(), 
				   	c = sc.nextDouble();
			DecimalFormat df = new DecimalFormat(sc.nextLine().substring(1));
			double	root1 = (-b+Math.sqrt(Math.pow(b, 2)-4*a*c)) / (2*a),
					root2 = (-b-Math.sqrt(Math.pow(b, 2)-4*a*c)) / (2*a);
			System.out.println(df.format(root1));
			System.out.println(df.format(root2));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
