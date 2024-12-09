import java.io.*;
import java.util.*;

public class Main {
    static int V, E, id;
    static int[] p;
    static boolean[] finished;
    static List<Integer>[] graph;
    static List<List<Integer>> scc;
    static Deque<Integer> stack;

    static int dfs(int cur) {
        p[cur] = ++id;
        stack.push(cur);

        int parent = p[cur];
        for(int nxt : graph[cur]) {
            if(p[nxt] == 0) {
                parent = Math.min(parent, dfs(nxt));
            } else if(!finished[nxt]) {
                parent = Math.min(parent, p[nxt]);
            }
        }

        if(parent == p[cur]) {
            List<Integer> ele = new ArrayList<>();
            while(!stack.isEmpty()) {
                int nxt = stack.pop();
                finished[nxt] = true;
                ele.add(nxt);
                if(cur == nxt) break;
            }
            Collections.sort(ele);
            scc.add(ele);
        }

        return parent;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        id = 0;
        p = new int[V + 1];
        finished = new boolean[V + 1];
        graph = new List[V + 1];
        scc = new ArrayList<>();
        stack = new ArrayDeque<>();
        for(int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        for(int i = 1; i <= V; i++) {
            if(p[i] == 0) dfs(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(scc.size()).append('\n');
        Collections.sort(scc, (o1, o2) -> o1.get(0) - o2.get(0));
        for(List<Integer> ele : scc) {
            for(int i : ele) {
                sb.append(i).append(' ');
            }
            sb.append(-1).append('\n');
        }

        System.out.print(sb);
        br.close();
    }
}