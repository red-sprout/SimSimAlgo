package year2024.month10.fifth;

import java.io.*;
import java.util.*;

public class Main_bj_9076_점수집계 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int[] arr = new int[5];
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 5; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			sb.append(arr[3] - arr[1] < 4 ? arr[1] + arr[2] + arr[3] + "" : "KIN").append('\n');
		}
		System.out.println(sb.toString().trim());
		br.close();
	}
}
