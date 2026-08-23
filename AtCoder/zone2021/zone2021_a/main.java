import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int cnt = 0;
		for (int i = 0; i < s.length() - 3; i++) {
			if (s.startsWith("ZONe", i))
				++cnt;
		}
		System.out.println(cnt);
		br.close();
	}
}
