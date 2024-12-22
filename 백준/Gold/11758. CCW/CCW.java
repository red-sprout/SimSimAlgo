import java.io.*;
import java.util.*;

public class Main {
	static int ccw(int[] v1, int[] v2) {
		int v = v1[0] * v2[1] - v2[0] * v1[1];
		if(v < 0) return -1;
		if(v > 0) return 1;
		return 0;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int[][] p = new int[3][2];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			p[i][0] = Integer.parseInt(st.nextToken());
			p[i][1] = Integer.parseInt(st.nextToken());
		}
		int[] v1 = {p[1][0] - p[0][0], p[1][1] - p[0][1]};
		int[] v2 = {p[2][0] - p[1][0], p[2][1] - p[1][1]};
		System.out.println(ccw(v1, v2));
		br.close();
	}
}