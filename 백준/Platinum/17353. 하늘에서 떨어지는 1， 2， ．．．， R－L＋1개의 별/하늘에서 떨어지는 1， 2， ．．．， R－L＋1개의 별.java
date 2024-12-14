import java.io.*;
import java.util.*;

public class Main {
  static int N, Q, size;
  static long[] arr, tree, lazy;

  static void update(int L, int R) {
    updateTree(1, 1, N + 1, L, R, 1);
    updateTree(1, 1, N + 1, R + 1, R + 1, -(R - L + 1));
  }

  static void updateLazy(int node, int s, int e) {
    if(lazy[node] == 0) return;
    tree[node] += (e - s + 1) * lazy[node];
    if(s != e) {
      lazy[2 * node] += lazy[node];
      lazy[2 * node + 1] += lazy[node];
    }
    lazy[node] = 0;
  }

  static void updateTree(int node, int s, int e, int ts, int te, long val) {
    updateLazy(node, s, e);
    if(e < ts || te < s) return;
    if(ts <= s && e <= te) {
      tree[node] += (e - s + 1) * val;
      if(s != e) {
        lazy[2 * node] += val;
        lazy[2 * node + 1] += val;
      }
      return;
    }
    int mid = (s + e) / 2;
    updateTree(2 * node, s, mid, ts, te, val);
    updateTree(2 * node + 1, mid + 1, e, ts, te, val);
    tree[node] = tree[2 * node] + tree[2 * node + 1];
  }

  static long get(int X) {
    return get(1, 1, N + 1, 1, X) + arr[X];
  }

  static long get(int node, int s, int e, int ts, int te) {
    updateLazy(node, s, e);
    if(e < ts || te < s) return 0;
    if(ts <= s && e <= te) return tree[node];
    int mid = (s + e) / 2;
    long left = get(2 * node, s, mid, ts, te);
    long right = get(2 * node + 1, mid + 1, e, ts, te);
    return left + right;
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    
    N = Integer.parseInt(br.readLine());
    size = 1 << ((int)(Math.ceil(Math.log(N + 1) / Math.log(2))) + 1);
    arr = new long[N + 1];
    tree = new long[size];
    lazy = new long[size];

    st = new StringTokenizer(br.readLine(), " ");
    for(int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Q = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < Q; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int q = Integer.parseInt(st.nextToken());
      if(q == 1) {
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        update(L, R);
      } else {
        int X = Integer.parseInt(st.nextToken());
        sb.append(get(X)).append('\n');
      }
    }

    System.out.print(sb);
    br.close();
  }
}