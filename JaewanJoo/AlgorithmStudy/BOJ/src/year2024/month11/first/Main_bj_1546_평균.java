package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_1546_평균 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[] arr = new double[N];
		double M = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Double.parseDouble(st.nextToken());
			M = Math.max(M, arr[i]);
		}
		double avg = 0;
		for(int i = 0; i < N; i++) {
			avg += arr[i] * 100 / M;
		}
		System.out.println(avg / N);
		br.close();
	}
}
