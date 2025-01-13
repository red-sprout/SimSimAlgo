import java.io.*;
import java.util.*;

public class Main {
	static HashMap<String, long[]> map;
	
	static void init() {
		map = new HashMap<>();
		map.put("black", new long[] {0, 1});
		map.put("brown", new long[] {1, 10});
		map.put("red", new long[] {2, 100});
		map.put("orange", new long[] {3, 1000});
		map.put("yellow", new long[] {4, 10000});
		map.put("green", new long[] {5, 100000});
		map.put("blue", new long[] {6, 1000000});
		map.put("violet", new long[] {7, 10000000});
		map.put("grey", new long[] {8, 100000000});
		map.put("white", new long[] {9, 1000000000});
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		init();
		long res = 0;
		String first = br.readLine();
		String second = br.readLine();
		String third = br.readLine();
		
		res = (map.get(first)[0] * 10 + map.get(second)[0]) * map.get(third)[1];
		System.out.println(res);
		br.close();
	}
}