import java.io.*;
import java.util.*;

public class Main {
	static int ccw(int[] v1, int[] v2) {
		long result = 1L * v1[0] * v2[1] - 1L * v1[1] * v2[0];
		if(result < 0) return -1;
		if(result > 0) return 1;
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		List<int[]> p = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int[] p1 = new int[2], p2 = new int[2];
			p1[0] = Integer.parseInt(st.nextToken());
			p1[1] = Integer.parseInt(st.nextToken());
			p2[0] = Integer.parseInt(st.nextToken());
			p2[1] = Integer.parseInt(st.nextToken());
			p.add(p1); p.add(p2);
		}
		
		boolean flag = true;
		int[] v1 = {p.get(1)[0] - p.get(0)[0], p.get(1)[1] - p.get(0)[1]};
		int[] v2 = {p.get(2)[0] - p.get(1)[0], p.get(2)[1] - p.get(1)[1]};
		int[] v3 = {p.get(3)[0] - p.get(1)[0], p.get(3)[1] - p.get(1)[1]};
		if(ccw(v1, v2) * ccw(v1, v3) > 0) flag = false;
		
		v1 = new int[] {p.get(3)[0] - p.get(2)[0], p.get(3)[1] - p.get(2)[1]};
		v2 = new int[] {p.get(0)[0] - p.get(3)[0], p.get(0)[1] - p.get(3)[1]};
		v3 = new int[] {p.get(1)[0] - p.get(3)[0], p.get(1)[1] - p.get(3)[1]};
		if(ccw(v1, v2) * ccw(v1, v3) > 0) flag = false;
		
		System.out.println(flag ? 1 : 0);
		br.close();
	}
}