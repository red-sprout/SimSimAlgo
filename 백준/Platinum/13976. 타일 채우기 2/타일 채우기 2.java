import java.io.*;

public class Main {
	static final long CONST = 1_000_000_007l;
	
	static long[][] multiply(long[][] m1, long[][] m2) {
		long[][] result = new long[2][2];
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				result[i][j] = (m1[i][0] * m2[0][j] + m1[i][1] * m2[1][j] + CONST) % CONST;
			}
		}
		return result;
	}
	
	static long[][] pow(long k) {
		if(k == 0) return new long[][] {{1l, 0l}, {0l, 1l}};
		if(k == 1) return new long[][] {{4l, -1l}, {1l, 0l}};
		long[][] half = pow(k / 2);
		long[][] d = multiply(half, half);
		return k % 2 == 1 ? multiply(pow(1), d) : d;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		if(N % 2 == 1) {
			System.out.println(0);
		} else {
			long k = (N / 2) - 1;
			long[][] M = pow(k);
			System.out.println((M[0][0] * 3 + M[0][1]) % CONST);
		}
		br.close();
	}
}