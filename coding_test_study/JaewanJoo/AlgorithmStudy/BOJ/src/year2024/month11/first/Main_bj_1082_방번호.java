package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_1082_방번호 {
	static int N, M;
	static int[] P;
	static Map<String, Long>[] memo;
	static long dfs(int money, String str) {
		if(memo[money].containsKey(str)) return memo[money].get(str);
		long result = 0;
		for(int i = 0; i < N; i++) {
			if(money + P[i] > M) continue;
			result = Math.max(result, Long.parseLong(str + i));
			result = Math.max(result, dfs(money + P[i], str + i));
		}
		memo[money].put(str, result);
		return result;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		P = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		M = Integer.parseInt(br.readLine());
		memo = new Map[M + 1];
		for(int i = 0; i <= M; i++) {
			memo[i] = new HashMap<>();
		}
		System.out.println(dfs(0, ""));
		br.close();
	}
}
