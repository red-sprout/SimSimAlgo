import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<String> pq;
	
	static int comp(String o1, String o2) {
		int size = Math.max(o1.length(), o2.length());
		for(int i = 0; i < size; i++) {
			char c1, c2;
			c1 = o1.length() - 1 < i ? 'Z' + 1 : o1.charAt(i);
			c2 = o2.length() - 1 < i ? 'Z' + 1 : o2.charAt(i);
			if(c1 < c2) return -1;
			if(c1 > c2) return 1;
		}
		return 0;
	}
	
	static void divide(String str) {
		char c = str.charAt(0);
		int before = 0, after = 0;
		for(int i = 1; i < str.length(); i++) {
			if(str.charAt(i) > c) {
				c = str.charAt(i);
				after = i;
				pq.offer(str.substring(before, after));
				before = i;
			} else if(str.charAt(i) == c) {
				String s1 = str.substring(before, i);
				String s2 = str.substring(i);
				if(comp(s1, s2) < 0) {
					before = i; after = i;
					pq.offer(s1);
				}
			}
		}
		after = str.length();
		if(before < after) pq.offer(str.substring(before, after));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>((o1, o2) -> comp(o1, o2));
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			divide(str);
		}
		StringBuilder sb = new StringBuilder();
		while(!pq.isEmpty()) {
			sb.append(pq.poll());
		}
		System.out.println(sb.toString());
		br.close();
	}
}
