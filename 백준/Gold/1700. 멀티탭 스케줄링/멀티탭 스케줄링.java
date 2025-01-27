import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int id, cnt;

        public Node(int id) {
            this.id = id;
            this.cnt = 0;
        }

        @Override
        public String toString() {
            return "[" + id + ", " + cnt + "]";
        }

        @Override
        public int compareTo(Node node) {
            int i1 = idxQ[this.id].peek() == null ? 1_000_000 : idxQ[this.id].peek();
            int i2 = idxQ[node.id].peek() == null ? 1_000_000 : idxQ[node.id].peek();
            return i2 - i1;
        }
    }

    static int n, k;
    static Node[] node;
    static Queue<Integer>[] idxQ;
    static int[] order;

    static int solution() {
        int res = 0;
        boolean[] v = new boolean[k + 1];
        Queue<Node> q = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(int id : order) {
            node[id].cnt--;
            idxQ[id].poll();
            
            if(v[id]) continue;
            
            if(q.size() == n) {
                while(!q.isEmpty()) pq.offer(q.poll());
                
                Node rm = pq.poll();
                v[rm.id] = false;
                res++;
                
                while(!pq.isEmpty()) q.offer(pq.poll());
            }
            
            q.offer(node[id]);
            v[id] = true;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        node = new Node[k + 1];
        idxQ = new Queue[k + 1];
        order = new int[k];
        for(int i = 1; i <= k; i++) {
            node[i] = new Node(i);
            idxQ[i] = new ArrayDeque<>();
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < k; i++) {
            int id = Integer.parseInt(st.nextToken());
            order[i] = id;
            node[id].cnt++;
            idxQ[id].offer(i);
        }

        System.out.println(solution());
        br.close();
    }
}
