package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_2836_수상택시 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a > b) {
				pq.offer(new int[] {a, -1});
				pq.offer(new int[] {b, 1});
			}
		}
		int start = 0;
		long length = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(cnt == 0) start = cur[0];
			cnt += cur[1];
			if(cnt == 0) length += cur[0] - start;
		}
		System.out.println(M + 2 * length);
		br.close();
	}
}
