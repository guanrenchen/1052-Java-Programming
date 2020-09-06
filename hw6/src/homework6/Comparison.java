package homework6;

public class Comparison implements IOperation{
	public String perform(String num1, String num2){
		char sign = new Subtraction().perform(num1, num2.substring(1)).charAt(0),
			 type = num2.charAt(0);
		
		switch(sign){
		case '0': return type=='='? "true": "false";
		case '-': return type=='<'? "true": "false";
		default:  return type=='>'? "true": "false";
		}
	}
}
