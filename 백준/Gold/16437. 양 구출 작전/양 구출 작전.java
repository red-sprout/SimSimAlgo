import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        char t;
        long a;

        Node(char t, long a) {
            this.t = t;
            this.a = a;
        }
    }

    static int n;
    static int[] dep;
    static long[] sheep, wolf;
    static Node[] nodes;
    static List<Integer>[] tree;

    static void dfs(int cur, int d) {
        dep[cur] = d;
        Node node = nodes[cur];
        sheep[cur] = node.t == 'S' ? node.a : 0;
        wolf[cur] = node.t == 'W' ? node.a : 0;
        for(int nxt : tree[cur]) {
            if(dep[nxt] == 0) {
                dfs(nxt, d + 1);
                long addSheep = Math.max(sheep[nxt] - wolf[cur], 0);
                wolf[cur] -= Math.min(sheep[nxt], wolf[cur]);
                sheep[cur] += addSheep;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dep = new int[n + 1];
        sheep = new long[n + 1];
        wolf = new long[n + 1];
        nodes = new Node[n + 1];
        tree = new List[n + 1];
        for(int i = 1; i <= n; ++i) tree[i] = new ArrayList<>();

        nodes[1] = new Node('N', 0);
        for(int i = 2; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            long a = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(t, a);
            tree[i].add(p);
            tree[p].add(i);
        }

        dfs(1, 1);
        System.out.println(sheep[1]);
        br.close();
    }
}
