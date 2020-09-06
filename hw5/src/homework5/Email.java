package homework5;

import java.util.Scanner;

public class Email extends Document{
	
	private String sender, recipient, title;
	
	@Override
	public String toString(){
		return "From: " + getSender() + "\n" +
				"To: " + getRecipient() + "\n" +
				"Title: " + getTitle() + "\n" +
				getText();
	}
	
	@Override
	protected void init(Scanner sc){
		setSender(sc.nextLine());
		setRecipient(sc.nextLine());
		setTitle(sc.nextLine());
		setText(sc.nextLine());
	}
	
	public void setSender(String str){ sender = str;}
	public String getSender(){ return sender;}
	public void setRecipient(String str){ recipient = str;}	
	public String getRecipient(){ return recipient;}	
	public void setTitle(String str){ title = str;}	
	public String getTitle(){ return title;}

}
