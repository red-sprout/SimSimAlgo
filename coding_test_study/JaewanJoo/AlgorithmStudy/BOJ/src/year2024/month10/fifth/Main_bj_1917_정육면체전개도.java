package year2024.month10.fifth;

import java.io.*;
import java.util.*;

public class Main_bj_1917_정육면체전개도 {
	static int[][] map = new int[6][6];
	static int[][][] threeByFour = {
			{
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{1, 0, 0, 0}
			},
			{
				{0, 1, 0, 0},
				{1, 1, 1, 1},
				{0, 1, 0, 0}
			},
			{
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 1, 0, 0}
			},
			{
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 1, 0}
			},
			{
				{1, 0, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 0, 1}
			},
			{
				{0, 1, 0, 0},
				{1, 1, 1, 1},
				{0, 0, 1, 0}
			},
			{
				{1, 1, 0, 0},
				{0, 1, 1, 1},
				{0, 1, 0, 0}
			},
			{
				{1, 1, 0, 0},
				{0, 1, 1, 1},
				{0, 0, 1, 0}
			},
			{
				{1, 1, 0, 0},
				{0, 1, 1, 1},
				{0, 0, 0, 1}
			},
			{
				{1, 1, 0, 0},
				{0, 1, 1, 0},
				{0, 0, 1, 1}
			}
	};
	static int[][] twoByFive = {
			{1, 1, 1, 0, 0},
			{0, 0, 1, 1, 1}
	};
	static int[][] turn(int[][] origin) {
		int row = origin.length;
		int col = origin[0].length;
		int[][] result = new int[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				result[i][j] = origin[i][col - j - 1];
			}
		}
		return result;
	}
	static int[][] flip(int[][] origin) {
		int row = origin.length;
		int col = origin[0].length;
		int[][] result = new int[row][col];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				result[i][j] = origin[row - i - 1][j];
			}
		}
		return result;
	}
	static int[][] rotation(int[][] origin) {
		int row = origin.length;
		int col = origin[0].length;
		int[][] result = new int[col][row];
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				result[j][row - i - 1] = origin[i][j];
			}
		}
		return result;
	}
	static void updatePaper(int r, int c, int[][] paper) {
		for(int i = 0; i < paper.length; i++) {
			for(int j = 0; j < paper[i].length; j++) {
				paper[i][j] = map[i + r][j + c];
			}
		}
	}
	static boolean isEqual(int[][] arr1, int[][] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				if(arr1[i][j] != arr2[i][j]) return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		outer : for(int test = 0; test < 3; test++) {
			for(int i = 0; i < 6; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < 6; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 3 x 4
			int[][] paper = new int[3][4];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 3; j++) {
					updatePaper(i, j, paper);
					for(int k = 0; k < threeByFour.length; k++) {
						int[][] cur = threeByFour[k];
						if(isEqual(cur, paper) || isEqual(cur, turn(paper)) || isEqual(cur, flip(paper)) || isEqual(cur, turn(flip(paper)))) {
							System.out.println("yes");
							continue outer;
						}
					}
				}
			}
			// 4 x 3
			paper = new int[4][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 4; j++) {
					updatePaper(i, j, paper);
					for(int k = 0; k < threeByFour.length; k++) {
						int[][] cur = rotation(threeByFour[k]);
						if(isEqual(cur, paper) || isEqual(cur, turn(paper)) || isEqual(cur, flip(paper)) || isEqual(cur, turn(flip(paper)))) {
							System.out.println("yes");
							continue outer;
						}
					}
				}
			}
			// 2 x 5
			paper = new int[2][5];
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 2; j++) {
					updatePaper(i, j, paper);
					int[][] cur = twoByFive;
					if(isEqual(cur, paper) || isEqual(cur, turn(paper)) || isEqual(cur, flip(paper)) || isEqual(cur, turn(flip(paper)))) {
						System.out.println("yes");
						continue outer;
					}
				}
			}
			// 5 x 2
			paper = new int[5][2];
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 5; j++) {
					updatePaper(i, j, paper);
					int[][] cur = rotation(twoByFive);
					if(isEqual(cur, paper) || isEqual(cur, turn(paper)) || isEqual(cur, flip(paper)) || isEqual(cur, turn(flip(paper)))) {
						System.out.println("yes");
						continue outer;
					}
				}
			}
			System.out.println("no");
		}
		br.close();
	}
}
