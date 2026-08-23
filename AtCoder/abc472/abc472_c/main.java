import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n, m;
		long k;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Long.parseLong(st.nextToken());
		
		int l = 0;
		long[] arr = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		long val = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			if(i - l >= m) {
				val -= arr[l++];
			}
			if(val + arr[i] > k) {
				sb.append("No\n");
				arr[i] = 0;
			} else {
				sb.append("Yes\n");
				val += arr[i];
			}
		}

		System.out.print(sb);
		br.close();
	}
}
