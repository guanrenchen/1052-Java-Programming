package sudoku;

import java.awt.*;
import javax.swing.*;

public class CustomDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomDialog(JFrame frame, String title, String content){
		super(frame, title, true);

		JLabel label = new JLabel();
		label.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
	    this.getContentPane().add(label, BorderLayout.CENTER);
		label.setText(content);
	    this.pack();
	    
	    Dimension size = this.getSize();
	    this.setSize(size.width+50, size.height+50);
	    
	    this.setLocationRelativeTo(frame);
	    this.setVisible(true);
	}
	
}
