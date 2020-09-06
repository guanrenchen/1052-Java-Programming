package sudoku;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Cell extends JTextPane{
	
	public static final Color COLOR_FOCUS = Color.YELLOW; 
	public static final Color COLOR_NORMAL= Color.WHITE;
	public static final Color COLOR_LOCKED= Color.LIGHT_GRAY;
	public static final Color COLOR_LOCKED_FOCUS = Color.RED;
	
	public static final Font FONT_NORMAL = new Font("Arial Black", Font.PLAIN, 60);
	public static final Font FONT_HINT = new Font("Arial", Font.PLAIN, 25);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean locked = false;
	private int value;
	private SudokuGrid sudokuGrid;
	private int id;
	
	public Cell(SudokuGrid sudokuGrid, int id){
		this.sudokuGrid = sudokuGrid;
		this.id = id;
		
		setBackground(COLOR_NORMAL);
		setEditable(false);
		setFont(FONT_NORMAL);
		setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
		//setText("1 2 3\n4 5 6\n7 8 9");
		
		StyledDocument doc = getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		addFocusListener(new FocusListener() {	
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				Cell cell = (Cell)e.getSource();
				cell.setBackground(locked? COLOR_LOCKED: COLOR_NORMAL);
			}		
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				Cell cell = (Cell)e.getSource();
				cell.setBackground(locked? COLOR_LOCKED_FOCUS: COLOR_FOCUS);
			}
		});
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				char ch = e.getKeyChar();
				Cell cell = (Cell)e.getSource();
				// backup
				int previousValue = cell.getValue();
				
				if(ch=='d'){
					cell.setLocked(false);
					cell.setValue(0);
					cell.setBackground(COLOR_FOCUS);
					sudokuGrid.loadPsbIntoCells();
				}
				
				if(locked || ch<'0' || ch>'9') return;
				cell.setValue((int)(ch-48));
				
				if(!sudokuGrid.isCellsValid())
					cell.setValue(previousValue);

				sudokuGrid.loadPsbIntoCells();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					sudokuGrid.moveFocus(id>8? id-9: id+72);
					break;
				case KeyEvent.VK_DOWN:
					sudokuGrid.moveFocus(id<72? id+9: id-72);
					break;
				case KeyEvent.VK_RIGHT:
					sudokuGrid.moveFocus(id%9!=8? id+1: id-8);
					break;
				case KeyEvent.VK_LEFT:
					sudokuGrid.moveFocus(id%9!=0? id-1: id+8);
					break;

				default:
					break;
				}
			}
		});
	}
	
	public void setValue(int n){
		if(locked) return;
		value = n;
		setFont(value==0? FONT_HINT: FONT_NORMAL);
		setText(value==0? "": value+"");
	}
	
	public int getValue(){
		return value;
	}

	public boolean isLocked(){
		return locked;
	}
	public void setLocked(boolean flag){
		locked = flag;
		setBackground(locked? COLOR_LOCKED: COLOR_NORMAL);
	}
	
	public void setHintText(String str){
		if(locked || value!=0) return;
		String hintStr = "";
		for(int i=0; i<str.length(); ++i)
			hintStr += str.charAt(i) + (i%3==2? "\n": " ");
		setFont(FONT_HINT);
		setText(hintStr);
	}
}
