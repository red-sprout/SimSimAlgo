package year2024.month10.fourth;

import java.io.*;
import java.util.*;

public class Main_bj_11004_K번째수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>(N);
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(list);
		System.out.println(list.get(K - 1));
		br.close();
	}
}
