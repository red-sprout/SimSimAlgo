import java.io.*;
import java.util.*;

public class Main {
  static int N, M, size;
  static int[] depth;
  static int[][] parent;
  static List<Integer>[] tree;
  
  static void dfs(int cur, int d) {
	  depth[cur] = d;
	  for(int nxt : tree[cur]) {
		  if(depth[nxt] == 0) {
			  dfs(nxt, d + 1);
			  parent[0][nxt] = cur;
		  }
	  }
  }
  
  static int lca(int u, int v) {
	  int d, n1, n2; // 깊이 차이, 깊은 노드, 얕은 노드
	  if(depth[u] > depth[v]) {
		  d = depth[u] - depth[v];
		  n1 = u;
		  n2 = v;
	  } else {
		  d = depth[v] - depth[u];
		  n1 = v;
		  n2 = u;
	  }
	  
	  // 1. 깊이 맟추기 d -> 0
	  // d를 이진수 - 1인 경우 싹 다 올려주기
	  for(int i = size; i >= 0; i--) {
		  if((d & (1 << i)) != 0) {
			  n1 = parent[i][n1];
		  }
	  }
	  
	  if(n1 == n2) return n1;
	  // 2. 같이 올리기
	  // n1, n2 같지 않을 때까지
	  // lca - n2
	  //  \ n1
	  for(int i = size; i >= 0; i--) {
		  if(parent[i][n1] != parent[i][n2]) {
			  n1 = parent[i][n1];
			  n2 = parent[i][n2];
		  }
	  }
	  
	  return parent[0][n1];
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = null;
    N = Integer.parseInt(br.readLine());
    size = (int) Math.ceil(Math.log(N) / Math.log(2));
    depth = new int[N + 1];
    parent = new int[size + 1][N + 1];
    tree = new List[N + 1];
    for (int i = 1; i <= N; i++) {
      tree[i] = new ArrayList<>();
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      tree[a].add(b);
      tree[b].add(a);
    }
    
    dfs(1, 1);
    for(int i = 1; i <= size; i++) {
    	for(int j = 1; j <= N; j++) {
    		parent[i][j] = parent[i - 1][parent[i - 1][j]];
    	}
    }

    M = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      sb.append(lca(a, b)).append('\n');
    }

    System.out.print(sb);
    br.close();
  }
}
