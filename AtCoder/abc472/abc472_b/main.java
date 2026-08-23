import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int l = 0, r = 0, ans = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			r += arr[i];
		}

		ans = r;
		for(int i = 0; i < n; i++) {
			l += arr[i];
			r -= arr[i];
			ans = Math.min(ans, Math.abs(r - l));
		}

		System.out.println(ans);
		br.close();
	}
}
