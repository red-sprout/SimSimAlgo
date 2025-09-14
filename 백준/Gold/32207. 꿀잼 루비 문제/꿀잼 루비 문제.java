import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int val, r, c;

        Node(int val, int r, int c) {
            this.val = val;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Node n) {
            return n.val - this.val;
        }
    }

    static int n, m, k;
    static List<Node> list;
    static Set<Integer> set;

    static int dfs(int cur) {
        if(set.size() == k || cur == list.size()) return 0;
        if(cur > 20) return 0;
        int res = 0;
        res = Math.max(res, dfs(cur + 1));
        if(!isConnected(list.get(cur))) {
            set.add(cur);
            res = Math.max(res, dfs(cur + 1) + list.get(cur).val);
            set.remove(cur);
        }
        return res;
    }

    static boolean isConnected(Node n2) {
        for(int idx : set) {
            Node n1 = list.get(idx);
            if(n1.r == n2.r - 1 && n1.c == n2.c) return true;
            if(n1.r == n2.r + 1 && n1.c == n2.c) return true;
            if(n1.r == n2.r && n1.c == n2.c - 1) return true;
            if(n1.r == n2.r && n1.c == n2.c + 1) return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for(int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; ++j) {
                int val = Integer.parseInt(st.nextToken());
                list.add(new Node(val, i, j));
            }
        }

        Collections.sort(list);
        set = new HashSet<>();
        System.out.println(dfs(0));
        br.close();
    }
}
