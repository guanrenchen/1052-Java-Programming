package homework6;

public class Addition implements IOperation{
	
	public String perform(String num1, String num2){
		
		//transform to equivalent operation with positive inputs
		char sign1 = num1.charAt(0),
			 sign2 = num2.charAt(0);
		if(sign1!=sign2){
			//(-a + b) => (b - a)
			if(sign1=='-') return new Subtraction().perform(num2, num1.substring(1));
			//(a + -b) => (a - b)
			if(sign2=='-') return new Subtraction().perform(num1, num2.substring(1));
		}else{
			//(-a + -b) => -(a + b), does not add (-) if result is 0
			if(sign1=='-'){
				String num = perform(num1.substring(1), num2.substring(1));
				return (num.equals("0")? "": "-") + num;
			}
		}
		
		//calculation
		StringBuilder sb = new StringBuilder();
		int carry=0;
		for(int i=num1.length()-1, j=num2.length()-1, temp=0;
			i>=0 || j>=0;
			--i, --j)
		{
			temp = getDigitAt(num1, i) + getDigitAt(num2, j) + carry;
			carry = (temp>=10)?  1: 0;
			sb.append(temp%10);
		}
		
		//adjust output format
		if(carry==1) sb.append(1);
		sb = sb.reverse();
		while(sb.charAt(0)=='0' && sb.length()>1) sb.deleteCharAt(0);
		return sb.toString();
	}
	
	private int getDigitAt(String num, int index){
		return (index>=0 && index<num.length())? num.charAt(index)-'0': 0;
	}
}
