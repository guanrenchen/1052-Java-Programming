package homework4;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Babylonian{
	public static void main(String[] args){
		try(
			Scanner sc = new Scanner(System.in);
		){
			DecimalFormat df = new DecimalFormat("0.00");
			double n = Double.parseDouble(sc.nextLine()),
				   guess = n/2.0f,
				   lastGuess;
			
			do{
				lastGuess = guess;
				guess = (guess + n/guess) / 2.0f;
			}while(!isBelowRatio(guess, lastGuess, 0.0001f));
			
			System.out.println(df.format(guess));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static boolean isBelowRatio(double a, double b, double ratio){
		double diff = Math.abs(a-b);
		if(diff/a<ratio && diff/b<ratio) return true;
		return false;
	}
}
