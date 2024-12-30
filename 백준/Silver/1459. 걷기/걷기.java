import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		long x = Integer.parseInt(st.nextToken());
		long y = Integer.parseInt(st.nextToken());
		long w = Integer.parseInt(st.nextToken());
		long s = Integer.parseInt(st.nextToken());
		
		long ans1 = w * (x + y);
		long ans2 = s * Math.min(x, y);
		long dist = Math.abs(x - y);
		if(dist % 2 == 0) {
			ans2 += Math.min(dist * s, dist * w);
		} else {
			ans2 += Math.min((dist - 1) * s + w, dist * w); 
		}
		System.out.println(Math.min(ans1, ans2));
		
		br.close();
	}
}