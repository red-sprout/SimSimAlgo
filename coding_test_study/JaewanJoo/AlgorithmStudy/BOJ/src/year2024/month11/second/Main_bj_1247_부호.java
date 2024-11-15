package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_1247_부호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test = 0; test < 3; test++) {
			int n = Integer.parseInt(br.readLine());
			PriorityQueue<Long> plus = new PriorityQueue<>();
			PriorityQueue<Long> minus = new PriorityQueue<>();
			long s = 0;
			for(int i = 0; i < n; i++) {
				long x = Long.parseLong(br.readLine());
				if(x > 0) plus.offer(x);
				if(x < 0) minus.offer(x);
			}
			while(!plus.isEmpty() && !minus.isEmpty()) {
				if(s < 0) s += plus.poll();
				else s += minus.poll();
			}
			char ans = ' ';
			if(plus.isEmpty() && minus.isEmpty()) {
				if(s == 0) ans = '0';
				else ans = s > 0 ? '+' : '-';
			} else if(plus.isEmpty()) {
				while(s >= 0 && !minus.isEmpty()) {
					s += minus.poll();
				}
				if(s == 0) ans = '0';
				else ans = s > 0 ? '+' : '-';
			} else {
				while(s <= 0 && !plus.isEmpty()) {
					s += plus.poll();
				}
				if(s == 0) ans = '0';
				else ans = s > 0 ? '+' : '-';
			}
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
		br.close();
	}
}
