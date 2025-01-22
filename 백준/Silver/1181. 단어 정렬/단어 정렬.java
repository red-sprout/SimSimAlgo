import java.io.*;
import java.util.*;

public class Main {
	static int n;
	
	static int comp(String s1, String s2) {
		return s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length();
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		TreeSet<String> set = new TreeSet<>((s1, s2) -> comp(s1, s2));
		for(int i = 0; i < n; i++) {
			set.add(br.readLine());
		}
		
		StringBuilder sb = new StringBuilder();
		for(String s : set) {
			sb.append(s).append('\n');
		}
		
		System.out.print(sb);
		br.close();
	}
}
