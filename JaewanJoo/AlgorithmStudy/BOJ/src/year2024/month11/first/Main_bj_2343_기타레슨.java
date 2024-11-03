package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_2343_기타레슨 {
	static int N, M;
	static int[] arr;
	static boolean check(int target) {
		int cnt = 1;
		int group = 0;
		for(int i = 0; i < N; i++) {
			group += arr[i];
			if(cnt > M) return true;
			if(group > target) {
				cnt++;
				group = 0;
				i--;
			}
		}
		return cnt > M;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = 100000001;
		while(left + 1 < right) {
			int mid = (left + right) / 2;
			if(check(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}
		System.out.println(right);
		br.close();
	}
}
