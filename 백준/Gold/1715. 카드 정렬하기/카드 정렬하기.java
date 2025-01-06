import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Long> pq = new PriorityQueue<>();
		while(n-- > 0) {
			long v = Long.parseLong(br.readLine());
			pq.offer(v);
		}
		
		long ans = 0;
		while(pq.size() > 1) {
			long a = pq.poll();
			long b = pq.poll();
			ans += a + b;
			pq.offer(a + b);
		}
		
		System.out.println(ans);
		br.close();
	}
}