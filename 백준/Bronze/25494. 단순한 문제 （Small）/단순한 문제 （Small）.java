import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			for(int i = 1; i <= a; i++) {
				for(int j = 1; j <= b; j++) {
					for(int k = 1; k <= c; k++) {
						if(i % j == j % k && j % k == k % i) cnt++;
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}