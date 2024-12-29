import java.io.*;
import java.util.*;

public class Main {
	static int solution(int n, int[] a, int[] b) {
		Integer[] index = new Integer[n];
		for(int i = 0; i < n; i++) {
			index[i] = i;
		}
		
		Arrays.sort(a);
		Arrays.sort(index, (o1, o2) -> b[o2] - b[o1]);
		int res = 0;
		for(int i = 0; i < n; i++) {
			res += a[i] * b[index[i]];
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];
		int[] b = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solution(n, a, b));
		br.close();
	}
}