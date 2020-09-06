package homework5;

import java.util.Scanner;

public class File extends Document{
	
	private String pathname;
	
	@Override
	public String toString() {
		return "Path: " + getPathname() + "\n" +
				getText();
	}
	
	@Override
	protected void init(Scanner sc){
		setPathname(sc.nextLine());
		setText(sc.nextLine());
	}
	
	public void setPathname(String str){ pathname = str;}	
	public String getPathname(){ return pathname;}
	
}
