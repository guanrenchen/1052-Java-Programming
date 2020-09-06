package homework6;

public class Subtraction implements IOperation{
	public String perform(String num1, String num2){

		//transform to equivalent operation with positive input
		char sign1 = num1.charAt(0),
			 sign2 = num2.charAt(0);
		if(sign1!=sign2){
			//(-a - b) => -(a + b), does not add (-) if result is 0
			if(sign1=='-'){
				String num = new Addition().perform(num1.substring(1), num2);
				return (num.equals("0")? "": "-") + num;
			}
			//(a - -b) => (a + b)
			if(sign2=='-') return new Addition().perform(num1, num2.substring(1));
		}else{
			//(-a - -b) => (b - a)
			if(sign1=='-') return perform(num2.substring(1), num1.substring(1));
		}
		
		//calculation
		StringBuilder sb = new StringBuilder();
		int carry=0;
		for(int i=num1.length()-1, j=num2.length()-1, temp=0;
			i>=0 || j>=0;
			--i, --j)
		{
			temp = getAsciiAt(num1, i) - getAsciiAt(num2, j) + carry;
			carry = (temp<0)? -1: 0;
			temp += (temp<0)? 10: 0;
			sb.append(temp);
		}

		//adjust output format
		sb = sb.reverse();
		if(carry==-1){
			for(int i=0; i<sb.length(); ++i) sb.setCharAt(i, (char)('9'-sb.charAt(i)+'0'));
			return "-" + new Addition().perform(sb.toString(), "1");
		}else{		
			while(sb.charAt(0)=='0' && sb.length()>1) sb.deleteCharAt(0);
			return sb.toString();
		}
	}
	
	private static int getAsciiAt(String num, int index){
		return (index>=0 && index<num.length())? num.charAt(index): '0';
	}
}
