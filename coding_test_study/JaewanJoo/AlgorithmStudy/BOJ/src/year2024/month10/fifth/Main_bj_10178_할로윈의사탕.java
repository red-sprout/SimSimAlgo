package year2024.month10.fifth;

import java.io.*;
import java.util.*;

public class Main_bj_10178_할로윈의사탕 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append("You get " + c / v + " piece(s) and your dad gets " + c % v + " piece(s).").append('\n');
		}
		System.out.print(sb.toString());
		br.close();
	}
}
