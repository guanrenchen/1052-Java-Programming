package sudoku;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;

import mainGame.*;


public class SudokuWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int GRID_SIZE = 3;
	private static final int SUBGRID_SIZE = 3;
	private static final int CELL_SIZE = 100;
	private static final int WINDOW_HEIGHT = GRID_SIZE*SUBGRID_SIZE*CELL_SIZE;
	private static final int WINDOW_WIDTH = GRID_SIZE*SUBGRID_SIZE*CELL_SIZE+200;
	
	private SudokuGrid sudokuGrid;
	private JSpinner spinner;
	private long startTime;
	
	public SudokuWindow(){
	    
		Container container = getContentPane();
		container.setLayout(new BorderLayout(0, 0));
		
		sudokuGrid = new SudokuGrid();
		container.add(sudokuGrid, BorderLayout.CENTER);
		
		addControls();
		
		startTime = System.currentTimeMillis();
		
	    container.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
	    pack();
	    setResizable(false);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
	    setTitle("Sudoku");
	    setVisible(true);
	}
	
	private void addControls(){
		Font buttonFont = new Font("Arial", Font.BOLD, 40);
		
		JButton button;
		JPanel controls = new JPanel(new GridLayout(9, 0));
		// set spinner
		spinner = new JSpinner(new SpinnerNumberModel(30,0,81,1));
		spinner.setFont(buttonFont);
		((DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);;
		controls.add(spinner);
		// New Game button
		button = new JButton("New");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sudokuGrid.generateQuestion((int)spinner.getValue());
				startTime = System.currentTimeMillis();
				updateSudokuPsb();
				sudokuGrid.moveFocus(40);
			}
		});
		controls.add(button);
		// Lock button
		button = new JButton("Lock");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				if(sudokuGrid.isCellsValid()){
					if(!sudokuGrid.isCellsPossible()){
						new CustomDialog(frame, "ERROR", "No answer exists");
						return ;
					}
					sudokuGrid.lockCells();
					updateSudokuPsb();
					startTime = System.currentTimeMillis();
				}else{
					new CustomDialog(frame, "ERROR", "Grid is not valid.");
				}
				
				// else display error message
			}
		});
		controls.add(button);
		// Clear button
		button = new JButton("Clear");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sudokuGrid.clearCells();
				updateSudokuPsb();
			}
		});
		controls.add(button);
		// Check button
		button = new JButton("Check");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				if(sudokuGrid.isCellsValid()){
					if(sudokuGrid.isCellsFull()){
						new CustomDialog(frame, "RESULT", "Time : "+(float)((System.currentTimeMillis()-startTime)/1000.0f)+" seconds");
					}else if(!sudokuGrid.isCellsPossible()){
						new CustomDialog(frame, "ERROR", "No answer exists");
					}else{
						new CustomDialog(frame, "RESULT", "Grid is valid.");
					}
				}else{
					new CustomDialog(frame, "ERROR", "Grid is not valid.");
				}
			}
		});
		controls.add(button);
		// Hint button
		button = new JButton("Hint");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				sudokuGrid.hint = !sudokuGrid.hint;
				button.setBackground(sudokuGrid.hint? Color.YELLOW: null);
				updateSudokuPsb();
			}
		});
		controls.add(button);
		// Solve button
		button = new JButton("Solve");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				if(!sudokuGrid.isCellsValid()) {
					new CustomDialog(frame, "ERROR", "Grid is not valid.");
					return;
				}
				if(sudokuGrid.isCellsFull()) return;
				startTime = System.nanoTime();
				if(!sudokuGrid.solve()) {
					new CustomDialog(frame, "ERROR", "No answer exists.");
				}else{
					new CustomDialog(frame, "Result", "Time : "+ new DecimalFormat("#.###").format((float)((System.nanoTime()-startTime)/1000000.0f))+" milliseconds");
				}
				startTime = System.currentTimeMillis();
				// send no answer message
			}
		});
		controls.add(button);
		// Return button
		button = new JButton("Return");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton button = (JButton)e.getSource();
				JFrame frame = (JFrame)button.getTopLevelAncestor();
				MainGame mainGame = new MainGame();
				mainGame.setLocationRelativeTo(frame);
				dispose();
			}
		});
		controls.add(button);
		// Exit button
		button = new JButton("Exit");
		button.setFont(buttonFont);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		controls.add(button);
		
		// Add controls to the right of the frame
		getContentPane().add(controls, BorderLayout.EAST);
	}
	
	private void updateSudokuPsb(){
		if(sudokuGrid.hint)
			sudokuGrid.loadPsbIntoCells();
		else
			sudokuGrid.removePsbFromCells();
	}

	
	public static void main(String[] args){
		new SudokuWindow();
	}
}
