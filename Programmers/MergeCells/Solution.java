package CodingTestStudy.MergeCells;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
	public class CellManager {
		Cell[][] cells;
		ArrayList<String> print;
		int maxX;
		int maxY;

		public CellManager(){
			cells = new Cell[50][50];
			for(int i=0; i<50; i++){
				for(int j=0; j<50; j++){
					cells[i][j] = new Cell();
				}
			}
			print = new ArrayList<>();
			maxX = 0;
			maxY = 0;
		}

		public void recieveCommand(String command){
			String[] str = command.split(" ");
			if(str[0].equals("UPDATE")){
				if(str.length==4) {
					if(maxX<Integer.parseInt(str[1])) maxX = Integer.parseInt(str[1]);
					if(maxY<Integer.parseInt(str[2])) maxY = Integer.parseInt(str[2]);
					Update(Integer.parseInt(str[1])-1, Integer.parseInt(str[2])-1, str[3]);
				}
				else if(str.length==3) Update(str[1], str[2]);
				else System.out.println("Input error");
			}
			else if(str[0].equals("MERGE")){
				if(maxX<Integer.parseInt(str[1])) maxX = Integer.parseInt(str[1]);
				if(maxY<Integer.parseInt(str[2])) maxY = Integer.parseInt(str[2]);
				if(maxX<Integer.parseInt(str[3])) maxX = Integer.parseInt(str[3]);
				if(maxY<Integer.parseInt(str[4])) maxY = Integer.parseInt(str[4]);
				Merge(Integer.parseInt(str[1])-1, Integer.parseInt(str[2])-1, Integer.parseInt(str[3])-1, Integer.parseInt(str[4])-1);
			}
			else if (str[0].equals("UNMERGE")){
				Unmerge(Integer.parseInt(str[1])-1, Integer.parseInt(str[2])-1);
			}
			else if (str[0].equals("PRINT")){
				Print(Integer.parseInt(str[1])-1, Integer.parseInt(str[2])-1);
			}
			else System.out.println("Input error");
		}

		public void Update(int r, int c, String value){
			cells[r][c].setValue(value);
		}
		public ArrayList<Cell> findByValue(String value){
			ArrayList<Cell> result = new ArrayList<>();
			for(int i=0; i<maxX; i++){
				for(int j=0; j<maxY; j++){
					if(cells[i][j].mergedTo == null && cells[i][j].getValue().equals(value))
						result.add(cells[i][j]);
				}
			}
			return result;
		}
		public void Update(String value1, String value2){
			ArrayList<Cell> foundCells = findByValue(value1);
			for(Cell cell: foundCells){
				cell.setValue(value2);
			}
		}
		public void Merge(int r1, int c1, int r2, int c2){ // r1, c1이 주인이 되고 r2, c2를 먹을 거임
			if(r1==r2 && c1==c2) return;
			cells[r2][c2].mergeTo(cells[r1][c1]);
		}
		public void Unmerge(int r1, int c1){
			String value = cells[r1][c1].getValue();
			cells[r1][c1].unMerge();
			cells[r1][c1].setValue(value);
		}
		public void Print(int r1, int c1){
			print.add(cells[r1][c1].toString());
		}
	}

	public class Cell{
		public String value;
		public boolean merged;
		public Cell mergedTo; // murgedTo의 끝단에 있는 Cell에 값이 저장됨.
		public HashSet<Cell> mergedFrom;

		public Cell(){
			this.value = "";
			this.merged = false;
			this.mergedTo = null;
			this.mergedFrom = new HashSet<>();
		}

		public void setValue(String value){
			Cell thisParent = this;
			if(thisParent.mergedTo != null) thisParent = thisParent.mergedTo;

			thisParent.value = value;
		}

		public void mergeTo(Cell cell){ // cell이 주인이 되고 this를 먹을 거임.

			Cell thisParent = this;
			Cell cellParent = cell;

			if(thisParent.merged && thisParent.mergedTo != null) thisParent = thisParent.mergedTo;
			if(cellParent.merged && cellParent.mergedTo != null) cellParent = cellParent.mergedTo;
			if(thisParent == cellParent) return;

			thisParent.merged = true;
			cellParent.merged = true;
			for(Cell c : thisParent.mergedFrom){
				c.mergedTo = cellParent;
			}
			thisParent.mergedTo = cellParent;
			cellParent.mergedFrom.addAll(thisParent.mergedFrom);
			cellParent.mergedFrom.add(thisParent);

			if(cellParent.getValue().isEmpty()) cellParent.setValue(thisParent.value);
			thisParent.value = "";

		}

		public void unMerge(){
			if(!this.merged) return; // 단일 셀이면 아무 작업도 안함.

			Cell thisParent = this;
			if(thisParent.mergedTo != null) thisParent = thisParent.mergedTo;
			for(Cell c : thisParent.mergedFrom){
				c.value = "";
				c.merged = false;
				c.mergedTo = null;
				c.mergedFrom.clear();
			}
			thisParent.value = "";
			thisParent.merged = false;
			thisParent.mergedTo = null;
			thisParent.mergedFrom.clear();
		}

		public String getValue(){
			Cell thisParent = this;
			if(thisParent.mergedTo != null) thisParent = thisParent.mergedTo;
			return thisParent.value;
		}

		public String toString() {
			return (this.getValue().isEmpty())? "EMPTY": this.getValue();
		}
	}

	public String[] solution(String[] commands) {
		CellManager cm = new CellManager();

		for (String command : commands) {
			cm.recieveCommand(command);
		}

		return cm.print.toArray(String[]::new);
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		/*
		String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean",
				"UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle",
				"UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle",
				"MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4",
				"PRINT 1 3", "PRINT 1 4"};
*/
		String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2",
				"MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};


/*
		String[] commands = {"MERGE 1 1 1 2","MERGE 1 2 1 1", "PRINT 1 1"};
*/
		String[] result = sol.solution(commands);
		for(String str: result) System.out.println(str);
	}
}