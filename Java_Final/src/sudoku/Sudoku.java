package sudoku;

import java.util.Random;

public class Sudoku {
	private static int[][] base = {
			{1,2,3,4,5,6,7,8,9},
			{4,5,6,7,8,9,1,2,3},
			{7,8,9,1,2,3,4,5,6},
			{2,1,4,3,6,5,8,9,7},
			{3,6,5,8,9,7,2,1,4},
			{8,9,7,2,1,4,3,6,5},
			{5,3,1,6,4,2,9,7,8},
			{6,4,2,9,7,8,5,3,1},
			{9,7,8,5,3,1,6,4,2}};
	
	// GiveQuestion
	public static int[][] getAnswer(){
		Random rand = new Random();
		for(int i=0; i<100; ++i){
			swapBigRow(rand.nextInt(3), rand.nextInt(3));
			swapBigCol(rand.nextInt(3), rand.nextInt(3));
			rotate(rand.nextInt(4));
			flip(rand.nextInt(2));
			changeNum(rand.nextInt(9)+1, rand.nextInt(9)+1);
		}
		return base;
	}
	private static void swapCell(int x1, int y1, int x2, int y2){
		int temp = base[x1][y1];
		base[x1][y1] = base[x2][y2];
		base[x2][y2] = temp;
	}
	private static void swapBigRow(int x1, int x2){
		if(x1==x2) return;
		x1*=3; x2*=3;
		for(int i=0; i<3; ++i, ++x1, ++x2)
			for(int j=0; j<9; ++j)
				swapCell(x1, j, x2, j);
		
	}
	private static void swapBigCol(int y1, int y2){
		if(y1==y2) return;
		y1*=3; y2*=3;
		for(int i=0; i<3; ++i, ++y1, ++y2)
			for(int j=0; j<9; ++j)
				swapCell(j, y1, j, y2);
	}
	private static void rotate(int n){
		for(n%=4; n>0; --n){
			for(int i=0; i<4; ++i)
			for(int j=0; j<5; ++j){
				swapCell(i, j, 8-j, i);
				swapCell(8-j, i, 8-i, 8-j);
				swapCell(8-i, 8-j, j, 8-i);
			}		
		}
	}
	private static void flip(int n){
		for(int i=0; i<4; ++i)
		for(int j=0; j<9; ++j){
			if(n==0) swapCell(i, j, 8-i, j);
			else	 swapCell(j, i, j, 8-i);
		}
	}
	private static void changeNum(int n1, int n2){
		if(n1==n2) return;
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){ 
			if(base[i][j]==n1) 
				base[i][j]=n2;
			else if(base[i][j]==n2)
				base[i][j]=n1;
		}
	}
	
	// validation
	private static void removeSubPsb(String[][] psb, int x, int y, String str){
		int startX=x/3*3, startY=y/3*3;
		for(int i=startX; i<startX+3; ++i)
		for(int j=startY; j<startY+3; ++j){
			psb[i][j] = psb[i][j].replaceFirst(str, "");
		}
	}
	private static void removeColPsb(String[][] psb, int y, String str){
		for(int i=0; i<9; ++i)
			psb[i][y] = psb[i][y].replaceFirst(str, "");
	}
	private static void removeRowPsb(String[][] psb, int x, String str){
		for(int i=0; i<9; ++i)
			psb[x][i] = psb[x][i].replaceFirst(str, "");
	}
	private static boolean subContainsOnlyOne(String[][] psb, int x, int y, String str){
		int startIndex = x*9+y;
		for(int i=startIndex; i<startIndex+21; i+=(i%3==2? 7: 1))
			if(psb[i/9][i%9].contains(str))
				for(int j=(i%3==2?i+7:i+1); j<startIndex+21; j+=(j%3==2? 7: 1))
					if(psb[j/9][j%9].contains(str))
						return false;
		return true;
	}
	private static boolean colContainsOnlyOne(String[][] psb, int y, String str){
		for(int i=0; i<9; ++i)
			if(psb[i][y].contains(str))
				for(int j=i+1; j<9; ++j)
					if(psb[j][y].contains(str))
						return false;
		return true;
	}
	private static boolean rowContainsOnlyOne(String[][] psb, int x, String str){
		for(int i=0; i<9; ++i)
			if(psb[x][i].contains(str))
				for(int j=i+1; j<9; ++j)
					if(psb[x][j].contains(str))
						return false;
		return true;
	}
	private static void setOnlyPsb(String[][] psb){
		// set row only possibility
		// row loop
		for(int n=1; n<=9; ++n){
			String str = n+"";
			for(int i=0; i<9; ++i){
				if(!rowContainsOnlyOne(psb, i, str))
					continue;
				for(int j=0; j<9; ++j){
					if(!psb[i][j].contains(str))
						continue;
					psb[i][j]=str;
					break;
				}
			}
		}
		// col loop
		for(int n=1; n<=9; ++n){
			String str = n+"";
			for(int i=0; i<9; ++i){
				if(!colContainsOnlyOne(psb, i, str))
					continue;
				for(int j=0; j<9; ++j){
					if(!psb[j][i].contains(str))
						continue;
					psb[j][i] = str;
					break;
				}
			}
		}
		// subGrid loop
		for(int n=1; n<=9; ++n){
			String str = n+"";
			for(int i=0; i<81; i+=(i%9==6? 21:3)){
				if(!subContainsOnlyOne(psb, i/9, i%9, str))
					continue;
				for(int j=i; j<i+21; j+=(j%3==2? 7: 1)){
					if(!psb[j/9][j%9].contains(str))
						continue;
					psb[j/9][j%9]=str;
					break;
				}
			}
		}
		
	}
	public static void setPsb(String[][] psb, int[][] grid){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			psb[i][j] = "123456789";
		}
		
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			String str = grid[i][j]+"";
			if(str.equals("0")) continue;
			removeRowPsb(psb, i, str);
			removeColPsb(psb, j, str);
			removeSubPsb(psb, i, j, str);
			psb[i][j] = str;
		}
		for(int i=0; i<9; ++i)
			setOnlyPsb(psb);
		
	}
	public static boolean isGridPossible(int[][] grid){
		
		String[][] psb = new String[9][9];
		setPsb(psb, grid);
		
		String[] rowTotal = new String[9];
		String[] colTotal = new String[9];
		String[] subTotal = new String[9];
		
		for(int i=0; i<9; ++i){
			rowTotal[i] = "";
			colTotal[i] = "";
			subTotal[i] = "";
		}
		
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j){
			rowTotal[i] += psb[i][j];
			colTotal[i] += psb[j][i];
			subTotal[(i/3*3)+(j/3)] += psb[i][j];
		}
		
		for(int n=1; n<=9; ++n){
			String nStr = n+"";
			for(int i=0; i<9; ++i){
				if(!rowTotal[i].contains(nStr) ||
				   !colTotal[i].contains(nStr) ||
				   !subTotal[i].contains(nStr)){
					return false;
				}
			}
		}
		return true;
	}
	
	// Check
	public static boolean isGridFull(int[][] grid){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j)
			if(grid[i][j]==0)
				return false;
		return true;
	}
	public static boolean isGridValid(int[][] grid){
		for(int i=0; i<9; ++i)
			if(!isRowValid(grid, i) || !isColValid(grid, i))
				return false;
		for(int i=0; i<9; i+=3)
		for(int j=0; j<9; j+=3)
			if(!isSubGridValid(grid, i, j))
				return false;
		return true;
	}
	private static boolean isRowValid(int[][] grid, int x){
		for(int i=0; i<9; ++i){
			if(grid[x][i]==0) 
				continue;
			for(int j=i+1; j<9; ++j)
				if(grid[x][i]==grid[x][j])
					return false;
		}
		return true;
	}
	private static boolean isColValid(int[][] grid, int y){
		for(int i=0; i<9; ++i){
			if(grid[i][y]==0) 
				continue;
			for(int j=i+1; j<9; ++j)
				if(grid[i][y]==grid[j][y])
					return false;
		}
		return true;
	}
	private static boolean isSubGridValid(int[][] grid, int x, int y){
		for(int i=x; i<x+3; ++i)
		for(int j=y; j<y+3; ++j){
			if(grid[i][j]==0) 
				continue;
			for(int k=x; k<x+3; ++k)
			for(int l=y; l<y+3; ++l)
				if(grid[i][j]==grid[k][l] && (i!=k || j!=l))
					return false;
		}
		return true;
	}
	
	// Solve
	private static boolean equivalentMatrix(int[][] grid1, int[][] grid2){
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j)
			if(grid1[i][j]!=grid2[i][j])
				return false;
		return true;
	}
	public static boolean backtrack(int[][] grid){
		
		if(!isGridValid(grid)) return false;
		if(isGridFull(grid)) return true;
		if(!isGridPossible(grid)) return false;
		
		// possibility
		int[][] previous = new int[9][9];
		String[][] psb = new String[9][9];
		do{
			for(int i=0; i<9; ++i)
			for(int j=0; j<9; ++j)
				previous[i][j] = grid[i][j];
					
			setPsb(psb, grid);
			
			for(int i=0; i<9; ++i)
			for(int j=0; j<9; ++j)
				if(grid[i][j]==0 && psb[i][j].length()==1)
					grid[i][j] = Integer.parseInt(psb[i][j]);
			
		}while(!equivalentMatrix(previous, grid));
		
		
	
		// brute force (backtrack)
		boolean[][] locked = new boolean[9][9];
		for(int i=0; i<9; ++i)
		for(int j=0; j<9; ++j)
			locked[i][j] = (grid[i][j]!=0);
		
		int loc, dir, x, y;
		for(loc=0, dir=1; loc<81 && loc>=0; loc+=dir){
			x=loc/9; y=loc%9;
			if(locked[x][y]) continue;
			dir = 1;
			do{++grid[x][y];}while(!isValidAtLocation(grid, x, y));
			if(grid[x][y]>9) {grid[x][y]=0; dir=-1;}
		}
		
		if(loc==-1) 
			return false;
		else 
			return true;
	}
	private static boolean isValidAtLocation(int[][] grid, int x, int y){
		return isValidInRow(grid, x, y) && 
			   isValidInColumn(grid, x, y) && 
			   isValidInSubGrid(grid, x, y);
	}
	private static boolean isValidInRow(int[][] grid, int x, int y){
		for(int i=0; i<9; ++i)
			if(grid[x][i]==grid[x][y] && i!=y)
				return false;
		return true;
	}
	private static boolean isValidInColumn(int[][] grid, int x, int y){
		for(int i=0; i<9; ++i){
			if(grid[i][y]==0)
				continue;
			if(grid[i][y]==grid[x][y] && i!=x)
				return false;
		}
		return true;
	}
	private static boolean isValidInSubGrid(int[][] grid, int x, int y){
		int startX = x/3*3, startY = y/3*3;
		for(int i=startX; i<startX+3; ++i)
		for(int j=startY; j<startY+3; ++j){
			if(grid[i][j]==0)
				continue;
			if(grid[i][j]==grid[x][y] && !(i==x && j==y))
				return false;
		}
		return true;
	}
}
