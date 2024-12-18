import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static int[][] map, ld, rd;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		ld = new int[R][C];
		rd = new int[R][C];
		for(int i = 0; i < R; i++) {
			String row = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j) - '0';
				ld[i][j] = rd[i][j] = map[i][j];
			}
		}
		
		for(int i = R - 2; i >= 0; i--) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 0) continue;
				if(j != 0) ld[i][j] += ld[i + 1][j - 1];
				if(j != C - 1) rd[i][j] += rd[i + 1][j + 1];
			}
		}
		
		int answer = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if (map[i][j] == 0) continue;
				int d = Math.min(ld[i][j], rd[i][j]);
				while (d > answer) {
					if (rd[i + d - 1][j - d + 1] >= d && ld[i + d - 1][j + d - 1] >= d) 
						answer = d;
					d--;
				}
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}