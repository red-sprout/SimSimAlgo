import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] str = br.readLine().toCharArray();
		for(int i = 1; i < N; i++) {
			char[] now = br.readLine().toCharArray();
			for(int j = 0; j < now.length; j++) {
				if(str[j] != now[j]) str[j] = '?';
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < str.length; i++) {
			sb.append(str[i]);
		}
		System.out.println(sb);
		br.close();
	}
}