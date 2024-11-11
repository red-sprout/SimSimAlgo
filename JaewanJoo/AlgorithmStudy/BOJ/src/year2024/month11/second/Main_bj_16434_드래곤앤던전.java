package year2024.month11.second;

import java.io.*;
import java.util.*;

public class Main_bj_16434_드래곤앤던전 {
	static int n;
	static long atk;
	static long[][] info;
	static long getMaxHP() {
		long left = 0;
		long right = Long.MAX_VALUE;
		while(left + 1 < right) {
			long mid = left + (right - left) / 2;
			if(travel(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return right;
	}
	static boolean travel(long maxHP) {
		long curHP = maxHP;
		long curAtk = atk;
		for(int i = 0; i < n; i++) {
			long t = info[i][0];
			long a = info[i][1];
			long h = info[i][2];
			if(t == 1) {
				curHP = fight(a, h, curAtk, curHP);
			} else {
				curAtk += a;
				curHP = curHP + h < maxHP ? curHP + h : maxHP;
			}
			if(curHP <= 0) return false;
		}
		return curHP > 0;
	}
	static long fight(long a, long h, long curAtk, long curHP) {
		if(h % curAtk == 0) curHP -= ((h / curAtk) - 1) * a;
		else curHP -= (h / curAtk) * a;
		return curHP;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		atk = Long.parseLong(st.nextToken());
		info = new long[n][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			info[i][0] = Long.parseLong(st.nextToken());
			info[i][1] = Long.parseLong(st.nextToken());
			info[i][2] = Long.parseLong(st.nextToken());
		}
		System.out.println(getMaxHP());
		br.close();
	}
}
