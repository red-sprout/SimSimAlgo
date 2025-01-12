import java.io.*;

public class Main {
	static final long X = 31;
	static final long P = 1_000_000_007;
	static long MAX, HASH;
	
	static long hash(String str) {
		long h = 0;
		for(int i = 0; i < str.length(); i++) {
			int a = str.charAt(i) - 'a';
			h = (h + a) % P;
			h = (h * X) % P;
		}
		return h;
	}
	
	static long rollingHash(long hash, char start, char end) {
		int a = start - 'a';
		int b = end - 'a';
		hash = X * ((hash + P - a * MAX % P + b) % P) % P;
		return hash;
	}
	
	static void init(String p) {
		MAX = 1;
		for(int i = 0; i < p.length(); i++) {
			MAX = MAX * X % P;
		}
		HASH = hash(p);
	}
	
	static int solution(String s, String p) {
		long curHash = 0;
		int sl = s.length(), pl = p.length();
		for(int i = 0; i <= sl - pl; i++) {
			if(i == 0) {
				curHash = hash(s.substring(0, pl));
			} else {
				curHash = rollingHash(curHash, s.charAt(i - 1), s.charAt(i + pl - 1));
			}
			if(curHash == HASH) return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		init(p);
		System.out.println(solution(s, p));
		br.close();
	}
}