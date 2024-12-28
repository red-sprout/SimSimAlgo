import java.io.*;
import java.util.*;

public class Main {
	static class Team implements Comparable<Team> {
		int id, k, da, db;

		public Team(int id, int k, int da, int db) {
			this.id = id;
			this.k = k;
			this.da = da;
			this.db = db;
		}

		@Override
		public String toString() {
			return "Team [id=" + id + ", k=" + k + ", da=" + da + ", db=" + db + "]";
		}

		@Override
		public int compareTo(Team t) {
			return Integer.compare(Math.abs(t.da - t.db), Math.abs(this.da - this.db));
		}
	}
	
	static int n, a, b;
	static List<Team> list;
	
	static int solution() {
		int res = 0;
		for(Team t : list) {
			int need = t.k;
			if(t.da <= t.db) {
				if(a >= need) {
					a -= need;
					res += t.da * need;
				} else {
					need -= a;
					res += t.da * a;
					a = 0;
					b -= need;
					res += t.db * need;
				}
			} else {
				if(b >= need) {
					b -= need;
					res += t.db * need;
				} else {
					need -= b;
					res += t.db * b;
					b = 0;
					a -= need;
					res += t.da * need;
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {			
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			if(n == 0 && a == 0 && b == 0) break;
			
			list = new ArrayList<>();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int k = Integer.parseInt(st.nextToken());
				int da = Integer.parseInt(st.nextToken());
				int db = Integer.parseInt(st.nextToken());
				list.add(new Team(i, k, da, db));
			}
			
			Collections.sort(list);
			sb.append(solution()).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}