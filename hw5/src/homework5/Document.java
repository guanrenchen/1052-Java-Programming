package homework5;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Document {
	
	protected String text;
	
	@Override
	public String toString(){
		return getText();
	}
	
	protected void init(Scanner sc){
		setText(sc.nextLine());
	}
	
	public void responde(Scanner sc, String mode){
		init(sc);
		switch(mode){
		case "A":
			System.out.print(toString());
			break;
		case "B":
			System.out.print(getText().contains(sc.nextLine())? "true": "false");
			break;
		case "C":
			respondeC(sc.nextLine(), sc.nextLine());
			System.out.print(toString());
			break;
		default:
			break;
		}
	}
	
	private void respondeC(String fieldName, String content){
		String methodName = "set" +
							fieldName.substring(0,1).toUpperCase() +
							fieldName.substring(1);
		try{
			Class<?> cls = getClass();
			Method[] methods = null;
			while(cls.getSuperclass()!=null){
				methods = cls.getDeclaredMethods();
				for(Method m: methods){
					if(m.getName().equals(methodName)){
						m.invoke(this, content);
						return;
					}
				}
				cls = cls.getSuperclass();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setText(String str){ text = str;}	
	public String getText(){ return text;}
	
	public static void main(String[] args){		
		try(
			Scanner sc = new Scanner(System.in);
		){
			String mode = sc.nextLine();
			((Document)Class.forName("homework5."+sc.nextLine()).newInstance()).responde(sc, mode);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
