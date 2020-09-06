package homework3;

import java.util.*;

public class SpecialNumber{
	
	public static void main(String[] args) {
		try(
			Scanner sc = new Scanner(System.in);
		){
			String str = sc.nextLine();
			switch(str.charAt(0))
			{
				case 'X':
					System.out.println(isSpecialNum(Long.parseLong(str.substring(2)))? "true": "false");
					break;
				case 'Y':
					System.out.println(findNthSpecialNum(Integer.parseInt(str.substring(2))));
					break;
				default:
					System.out.println("INVALID INPUT");
					break;
			}
		}catch(Exception e){
			System.out.println("INVALID INPUT");
		}
	}

	public static boolean isSpecialNum(long num){
		while(num%2==0) num/=2;
		while(num%3==0) num/=3;
		while(num%5==0) num/=5;
		return (num==1)? true: false;
	}
	
	public static long findNthSpecialNum(final int target){
		ArrayList<Long> longList = new ArrayList<Long>();
		double num, longMax = Long.MAX_VALUE;
		int maxPower2 = (int)(Math.log(longMax)/Math.log(2)+1),
			maxPower3 = (int)(Math.log(longMax)/Math.log(3)+1),
			maxPower5 = (int)(Math.log(longMax)/Math.log(5)+1);
		for(int i=0; i<=maxPower2; ++i)
		for(int j=0; j<=maxPower3; ++j)
		for(int k=0; k<=maxPower5; ++k){
			num = Math.pow(2, i)*Math.pow(3,j)*Math.pow(5,k);
			if(num<=longMax) longList.add(new Long((long)num));
		}
		Collections.sort(longList);
		return longList.get(target-1).longValue();
	}
	
}
