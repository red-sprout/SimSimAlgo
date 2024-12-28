import java.io.*;
import java.util.*;

public class Main {
	static int toNum(int month, int day) {
		return month * 100 + day;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int s = toNum(3, 1);
		int e = toNum(11, 30);
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int m1 = Integer.parseInt(st.nextToken());
			int d1 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());
			int d2 = Integer.parseInt(st.nextToken());
			pq.offer(new int[] {toNum(m1, d1), toNum(m2, d2)});
		}
		
		int date = s;
		int cnt = 0;
		while(!pq.isEmpty()) {
			int[] cur = new int[] {0, 0};
			while(!pq.isEmpty()) {
				if(date < pq.peek()[0]) break;
				int[] part = pq.poll();
				if(cur[1] < part[1]) cur = part;
			}
			if(date > cur[1]) break;
			date = cur[1];
			cnt++;
			if(date > e) break;
		}
		
		System.out.println(date > e ? cnt : 0);
		br.close();
	}
}