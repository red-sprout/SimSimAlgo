import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t-- > 0) {
			String str = br.readLine();
			sb.append(str.charAt(0)).append(str.charAt(str.length() - 1)).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}