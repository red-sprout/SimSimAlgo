import java.io.*;

public class Main {
	static int[] coin = {500, 100, 50, 10, 5, 1};
	
	static int solution(int money) {
		int res = 0;
		for(int c : coin) {
			int cnt = money / c;
			res += cnt;
			money %= c;
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int price = Integer.parseInt(br.readLine());
		System.out.print(solution(1000 - price));
		br.close();
	}
}