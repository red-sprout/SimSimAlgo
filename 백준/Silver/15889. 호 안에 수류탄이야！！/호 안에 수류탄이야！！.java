import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean flag = true;
		int max = 0;
		if(n > 1) 
			st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n - 1; i++) {
			int length = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i] + length);
			if(max < arr[i + 1]) {
				flag = false;
			}
		}
		
		if(flag) {
			System.out.println("권병장님, 중대장님이 찾으십니다");
		} else {
			System.out.println("엄마 나 전역 늦어질 것 같아");
		}
		
		br.close();
	}
}