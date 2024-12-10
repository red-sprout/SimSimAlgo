import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static int[] w, depth;
  static int[][] dp;
  static List<Integer>[] tree;

  static void dfs1(int cur, int d) {
    depth[cur] = d;
    for(int nxt : tree[cur]) {
      if(depth[nxt] == 0) {
        dfs1(nxt, d + 1);
      }
    }
  }

  static int dfs2(int cur, int selected) {
    if(dp[cur][selected] != 0) return dp[cur][selected];
    if(selected == 1) dp[cur][selected] = w[cur];
    for(int nxt : tree[cur]) {
      if(depth[cur] > depth[nxt]) continue;
      if(selected == 0) {
        dp[cur][selected] += Math.max(dfs2(nxt, 0), dfs2(nxt, 1));
      } else {
        dp[cur][selected] += dfs2(nxt, 0);
      }
    }
    return dp[cur][selected];
  }

  static void dfs3(int cur, boolean selectable, List<Integer> list) {
    if(selectable && dp[cur][0] < dp[cur][1]) list.add(cur);
    for(int nxt : tree[cur]) {
      if (depth[cur] > depth[nxt]) continue;
      if(selectable && dp[cur][0] < dp[cur][1]) dfs3(nxt, false, list);
      else dfs3(nxt, true, list);
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;

    n = Integer.parseInt(br.readLine());
    w = new int[n + 1];
    depth = new int[n + 1];
    dp = new int[n + 1][2];
    tree = new List[n + 1];
    for(int i = 1; i <= n; i++) {
      tree[i] = new ArrayList<>();
    }

    st = new StringTokenizer(br.readLine(), " ");
    for(int i = 1; i <= n; i++) {
      w[i] = Integer.parseInt(st.nextToken());
    }

    for(int i = 0; i < n - 1; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      tree[u].add(v);
      tree[v].add(u);
    }
    
    dfs1(1, 1);

    int value = 0;
    value = Math.max(value, dfs2(1, 0));
    value = Math.max(value, dfs2(1, 1));

    List<Integer> list = new ArrayList<>();
    dfs3(1, true, list);
    Collections.sort(list);

    StringBuilder sb = new StringBuilder();
    sb.append(value).append('\n');
    for(int ele : list) {
      sb.append(ele).append(' ');
    }

    System.out.println(sb);
    br.close();
  }
}