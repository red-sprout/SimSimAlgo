import java.io.*;

public class Main {
	static String solution(String str) {
		int mid = str.length() >> 1;
		while(mid > 0) {			
			for(int i = mid - 1; i >= 0; i--) {
				if(str.charAt(i) == str.charAt((mid << 1) - i)) return "NO";
			}
			mid >>= 1;
		}
		return "YES";
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
			sb.append(solution(br.readLine())).append('\n');
		}
		System.out.print(sb);
		br.close();
	}
}