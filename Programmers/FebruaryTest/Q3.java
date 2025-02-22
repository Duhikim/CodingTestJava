package CodingTestStudy.FebruaryTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Q3 {
	static int numOfObj = 0;
	static int size = 0;
	static int maxSize = 0;
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String[] str = input.split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		char[][] field = new char[M][N];
		for(int i=0; i<M; i++){
			input = br.readLine();
			for(int j=0; j<N; j++){
				field[i][j] = input.charAt(j);
			}
		}
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++){
				if(field[i][j] == '#'){
					numOfObj++;
					size = 1;
					searchSameObject(field, i, j);
					if(size > maxSize) maxSize = size;
				}
			}
		}
		System.out.println(numOfObj);
		System.out.println(maxSize);
	}

	public static void searchSameObject(char[][] field, int row, int col){
		field[row][col] = '@';
		int[] newRow = {row-1, row+1, row, row};
		int[] newCol = {col, col, col-1, col+1};
		for(int i=0; i<4; i++){
			if(newRow[i] >=0 && newCol[i] >= 0 && newRow[i] <M && newCol[i] < N
					&& field[newRow[i]][newCol[i]] == '#'){
				size++;
				searchSameObject(field, newRow[i], newCol[i]);
			}
		}
	}
}