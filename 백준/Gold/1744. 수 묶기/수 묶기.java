import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static PriorityQueue<Integer> minus, plus;
	static Queue<Integer> zero;
	
	static int solution() {
		int res = 0;
		
		while(plus.size() > 1) {
			int val1 = plus.poll();
			int val2 = plus.poll();
			if(val1 == 1 || val2 == 1) {
				plus.offer(val1);
				plus.offer(val2);
				break;
			}
			res += val1 * val2;
		}
		while(minus.size() > 1) {
			res += minus.poll() * minus.poll();
		}
		while(!minus.isEmpty() && !zero.isEmpty()) {
			minus.poll(); zero.poll();
		}
		while(!plus.isEmpty()) {
			res += plus.poll();
		}
		while(!minus.isEmpty()) {
			res += minus.poll();
		}
		
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		minus = new PriorityQueue<>();
		zero = new ArrayDeque<>();
		plus = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < n; i++) {
			int val = Integer.parseInt(br.readLine());
			if(val > 0) plus.offer(val);
			else if(val < 0) minus.offer(val);
			else zero.offer(val);
		}
		
		System.out.println(solution());
		br.close();
	}
}