import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int r = Integer.parseInt(br.readLine());
		System.out.println(Math.PI * r * r);
		System.out.println(2 * r * r);
		br.close();
	}
}