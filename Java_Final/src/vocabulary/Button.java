package vocabulary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;


public class Button implements ActionListener{
	JFrame parentframe;
	JButton parentButton,parentButton1,parentButton2,parentButton3;
	JLabel parentLabel,parentLabel1,parentLabel2;
	public int h = 0;
	public Button(JButton parent,JButton parent1,JButton parent2,JButton parent3,JLabel parent4){
		parentButton = parent;
		parentButton1 = parent1;
		parentButton2 = parent2;
		parentButton3 = parent3;
		parentLabel = parent4;
	}
	public void actionPerformed(ActionEvent e){
		parentButton1.setVisible(true);
		parentButton2.setVisible(true);
		parentButton.setVisible(false);
		parentButton3.setLocation(390,0);
		parentLabel.setVisible(true);
		Random ran = new Random();
		String[] a;
		a = new String[100];
		String[] b;
		b = new String[100];
		String[] c;
		c = new String[100];
		File f = new File();
		try {
			f.File("ans.txt",a);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			f.File("tru.txt",b);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			f.File("wor.txt",c);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String tru;
		String ans;
		String wor;
		int i,j,k;
			ans = a[ran.nextInt(100)];
			tru = b[ran.nextInt(100)];
			wor = c[ran.nextInt(100)];
		Vocabulary g = new Vocabulary();
			if(ran.nextInt(2) == 0){
				parentButton1.setText(ans);
				parentButton2.setText(wor);
			}
			else{
				parentButton2.setText(ans);
				parentButton1.setText(wor);
			}
			parentLabel.setText(tru);
	}
}
