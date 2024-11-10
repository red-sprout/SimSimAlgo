package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_2170_선긋기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		List<int[]> list = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list.add(new int[] {x, 1});
			list.add(new int[] {y, -1});
		}
		Collections.sort(list, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
		int start = Integer.MIN_VALUE;
		int length = 0;
		int cnt = 0;
		for(int[] cur : list) {
			if(cnt == 0) start = cur[0];
			cnt += cur[1];
			if(cnt == 0) length += cur[0] - start;
		}
		System.out.println(length);
		br.close();
	}
}
