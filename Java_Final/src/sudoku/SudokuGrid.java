package sudoku;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class SudokuGrid extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public boolean hint = false;
	
	public Cell[][] cells;
	private int[][] grid;
	String[][] psb;
	
	public SudokuGrid(){
		this.setLayout(new GridLayout(3, 3));
		this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
		
		grid = new int[9][9];
		psb = new String[9][9];
		cells = new Cell[9][9];
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j)
			cells[i][j] = new Cell(this, i*9+j);
		
		for(int i=0; i<9; ++i){
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3, 3));
			panel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
			int startX = i/3*3, startY = i%3*3;
			for(int j=0; j<9; ++j){
				panel.add(cells[startX+(j/3)][startY+(j%3)]);
			}
			this.add(panel);
		}
		
	}	
	
	public void moveFocus(int id){
		cells[id/9][id%9].requestFocus();
	}
	
	public void loadCellsIntoGrid(){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			grid[i][j] = cells[i][j].getValue();
		}
	}
	public void loadGridIntoCells(){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			if(cells[i][j].isLocked()) continue;
			cells[i][j].setValue(grid[i][j]);
		}
	}
	
	public void loadPsbIntoCells(){
		if(!hint) return;
		loadCellsIntoGrid();
		String[][] psb = new String[9][9];
		Sudoku.setPsb(psb, grid);
		
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			if(cells[i][j].getValue()!=0 || cells[i][j].isLocked())
				continue;
			cells[i][j].setHintText(psb[i][j]);
		}
	}
	
	public void removePsbFromCells(){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			if(cells[i][j].getValue()!=0) 
				continue;
			cells[i][j].setValue(0);
		}
	}
	
	public void generateQuestion(final int displayNum){
		// add new cells
		Random rand = new Random();
		ArrayList<Integer> removeList = new ArrayList<Integer>();
		for(int i=0; i<81; ++i) 
			removeList.add(i);
		for(int i=0; i<displayNum; ++i)
			removeList.remove(rand.nextInt(removeList.size()));
		
		int[][] answer = Sudoku.getAnswer();
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			cells[i][j].setLocked(false);
			cells[i][j].setValue(answer[i][j]);
		}
		
		for(int i=0; i<removeList.size(); ++i){
			int n = removeList.get(i);
			cells[n/9][n%9].setValue(0);
		}
		
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			if(cells[i][j].getValue()==0) continue;
			cells[i][j].setLocked(true);
		}
			
	}
	
	public void lockCells(){
		loadCellsIntoGrid();
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			if(cells[i][j].isLocked() || cells[i][j].getValue()==0)
				continue;
			cells[i][j].setLocked(true);
		}
	}
	public void clearCells(){
		boolean clearExecuted = false;
		for(int i=0; i<9 ; ++i)
		for(int j=0; j<9; ++j)
			if(!cells[i][j].isLocked() && cells[i][j].getValue()!=0){
				cells[i][j].setValue(0);;
				clearExecuted = true;
			}
		if(clearExecuted) return;
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			cells[i][j].setLocked(false);
			cells[i][j].setValue(0);
		}
	}
	
	public boolean solve(){
		loadCellsIntoGrid();
		if(Sudoku.backtrack(grid)){			
			loadGridIntoCells();
			return true;
		}else{
			return false;
		}
		
	}	
	
	public boolean isCellsValid(){
		loadCellsIntoGrid();
		return Sudoku.isGridValid(grid);
	}
	public boolean isCellsFull(){
		loadCellsIntoGrid();
		return Sudoku.isGridFull(grid);
	}
	public boolean isCellsPossible(){
		loadCellsIntoGrid();
		return Sudoku.isGridPossible(grid);
	}
}
