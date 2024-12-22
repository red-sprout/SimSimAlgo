import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a == 0 && b == 0 && c == 0) break;
			a *= a; b *= b; c *= c;
			if(a + b == c || b + c == a || a + c == b) {
				sb.append("right").append('\n');
			} else {
				sb.append("wrong").append('\n');
			}
		}
		System.out.print(sb);
		br.close();
	}
}