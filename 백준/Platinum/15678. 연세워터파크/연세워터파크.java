import java.io.*;
import java.util.*;

public class Main {
	static class Pair {
		int idx;
		long val;
		
		Pair(int idx, long val) {
			this.idx = idx;
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "[" + idx + ", " + val + "]";
		}
	}
	
	static int N, D;
	static long max;
	static Deque<Pair> deque;
	static final long MIN = -1_000_000_001;
	
	static void offer(Pair p) {
		while(!deque.isEmpty()) {
			if(deque.peekLast().val >= p.val) break;
			deque.pollLast();
		}
		deque.offerLast(p);
	}
	
	static void poll(int idx) {
		while(!deque.isEmpty()) {
			if(deque.peekFirst().idx > idx) break;
			deque.pollFirst();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		max = Long.MIN_VALUE;
		deque = new ArrayDeque<>();
		deque.offer(new Pair(-1, MIN));
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			long val = Long.parseLong(st.nextToken());
			if(i < D) {
				val += Math.max(0, deque.peekFirst().val);
				offer(new Pair(i, val));
				max = Math.max(max, deque.peekFirst().val);
			} else {
				val += Math.max(0, deque.peekFirst().val);
				poll(i - D);
				offer(new Pair(i, val));
				max = Math.max(max, deque.peekFirst().val);
			}
		}
		
		System.out.println(max);
		br.close();
	}
}
