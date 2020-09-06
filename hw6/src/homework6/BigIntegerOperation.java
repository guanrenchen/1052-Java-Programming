package homework6;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BigIntegerOperation {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			System.out.println(operation(new StringTokenizer(sc.nextLine())));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String operation(StringTokenizer st){
		String str = st.nextToken(), op = null;
		while(st.hasMoreTokens()){
			op = st.nextToken();
			switch (op) {
			case "+":
				str = new Addition().perform(str, st.nextToken());
				break;
			case "-":
				str = new Subtraction().perform(str, st.nextToken());
				break;
			case "<": case ">": case "=":
				str = new Comparison().perform(str, op + operation(st));
				return str;
			default:
				System.out.println("ERROR");
				System.exit(0);
				break;
			}
		}
		return str;
	}
}
