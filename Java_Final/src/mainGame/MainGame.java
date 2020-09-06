package mainGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import sudoku.SudokuWindow;
import vocabulary.Vocabulary;

public class MainGame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainGame(){
		
		this.setContentPane(new JLabel(new ImageIcon("src/image/scrabble.jpg")));
		this.setResizable(false);
		customize();
		this.setPreferredSize(new Dimension(1000, 800));
	    this.pack();
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
	    this.setTitle("MainGame");
	    this.setVisible(true);
	}
	
	private void customize(){
		Container container = this.getContentPane();
		container.setLayout(new GridBagLayout());
		
		Font fontLabel = new Font("Arial Black", Font.BOLD, 60);
		Font fontButton = new Font("Arial", Font.PLAIN, 40);
		Border border = BorderFactory.createStrokeBorder(new BasicStroke(7.0f));
		
		JLabel label;
		JButton button;
		GridBagConstraints gbc;
		
		label = new JLabel("Puzzle Game");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(fontLabel);
		label.setEnabled(false);
		label.setBorder(border);
		label.setForeground(Color.BLACK);
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.ipadx = 25;
		gbc.ipady = 25;
		container.add(label, gbc);

		button = new JButton("Vocabulary");
		button.setFont(fontButton);
		button.setBorder(border);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				frame.dispose();
				new Vocabulary();
			}
		});
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipadx = 40;
		gbc.ipady = 25;
		container.add(button,gbc);
		
		button = new JButton("Sudoku");
		button.setFont(fontButton);
		button.setBorder(border);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				new SudokuWindow().setLocationRelativeTo(frame);
				frame.dispose();
			}
		});
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.ipady = 25;
		container.add(button,gbc);
		
	}
	
	
	public static void main(String[] args){
		new MainGame();
	}
}
