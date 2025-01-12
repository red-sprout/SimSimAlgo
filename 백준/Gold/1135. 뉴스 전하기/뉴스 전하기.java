import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] time;
	static List<Integer>[] tree;
	
	static int dfs(int cur) {
		if(tree[cur].isEmpty()) return 1;
		
		for(int nxt : tree[cur]) dfs(nxt);
		
		Collections.sort(tree[cur], (o1, o2) -> time[o2] - time[o1]);
		
		for(int i = 1; i <= tree[cur].size(); i++) {
			time[cur] = Math.max(time[cur], time[tree[cur].get(i - 1)] + i);
		}
		
		return time[cur];
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        time = new int[n];
        tree = new List[n];
        for(int i = 0; i < n; i++) {
        	tree[i] = new ArrayList<>();
        }
        
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
        	int p = Integer.parseInt(st.nextToken());
        	if(p == -1) continue;
        	tree[p].add(i);
        }
        
        System.out.println(n > 1 ? dfs(0) : 0);
        br.close();
    }
}
