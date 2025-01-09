import java.io.*;
import java.util.*;

public class Main {
    static List<int[]> list;
    static long[][][] dp;

    static long dfs(int idx, int w, int b) {
        if(idx == list.size()) return w == 15 && b == 15 ? 0 : Long.MIN_VALUE;
        if(dp[idx][w][b] != 0) return dp[idx][w][b];
        long res = 0;
        res = Math.max(res, dfs(idx + 1, w, b));
        if(w < 15) res = Math.max(res, dfs(idx + 1, w + 1, b) + list.get(idx)[0]);
        if(b < 15) res = Math.max(res, dfs(idx + 1, w, b + 1) + list.get(idx)[1]);
        return dp[idx][w][b] = res;
    }

    public static void main(String[] args) {
        StringTokenizer st = null;
        list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            while(true) {
                st = new StringTokenizer(br.readLine(), " ");
                int w = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                list.add(new int[]{w, b});
            }
        } catch (Exception e) { }
        dp = new long[list.size()][16][16];
        System.out.println(dfs(0, 0, 0));
    }
}
