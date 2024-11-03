package year2024.month11.first;

import java.io.*;
import java.util.*;

public class Main_bj_10610_30 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		int sum = 0;
		int idx = -1;
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i] - '0';
			if(arr[i] == '0') idx = i;
		}
		if(sum % 3 != 0 || idx == -1) {
			System.out.println(-1);
		} else {
			Arrays.sort(arr);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < arr.length; i++) {
				sb.append(arr[i]);
			}
			System.out.println(sb.reverse().toString());
		}
		br.close();
	}
}
