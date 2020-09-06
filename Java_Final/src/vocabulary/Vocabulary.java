package vocabulary;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import mainGame.MainGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Vocabulary implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton btn = new JButton("Start");
	JButton bnt = new JButton("Back");
	JButton btn1 = new JButton("");
	JButton btn2 = new JButton("");
	JLabel lb2 = new JLabel();
	public int time;
	public int num;
	public int h;
	public Vocabulary(){
		
		frame.setSize(500,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Button start = new Button(btn,btn1,btn2,bnt,lb2);
		btn.addActionListener(start);
		btn.setLocation(100,150);
		btn.setSize(100,50);
		bnt.setLocation(300,150);
		bnt.setSize(100,50);
		bnt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				frame.dispose();
				new MainGame();
			}
		});
		btn1.setLocation(0,150);
		btn1.addActionListener(start);
		btn1.setSize(240,100);
		btn2.setLocation(240,150);
		btn2.addActionListener(start);
		btn.addActionListener(start);
		btn2.setSize(240,100);
		btn1.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		btn2.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		btn.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		bnt.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		
		lb2.setLocation(200, 80);
		lb2.setSize(200,50);
		lb2.setFont(new Font("標楷體", Font.BOLD, 24));
		
		
		frame.setLayout(null);
		frame.add(btn);
		frame.add(bnt);
		frame.add(btn1);
		frame.add(btn2);
		frame.add(lb2);
		
		btn1.setVisible(false);
		btn2.setVisible(false);
		lb2.setVisible(false);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}


