import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] mp;
    static int[] par;
    static Map<Character, int[]> direction;

    static int find(int r, int c) {
        int p = par[r * m + c];
        if(p == -1) return r * m + c;
        return par[r * m + c] = find(p / m, p % m);
    }

    static boolean union(int r1, int c1, int r2, int c2) {
        int p1 = find(r1, c1);
        int p2 = find(r2, c2);
        if(p1 == p2) return false;
        par[p1] = p2;
        return true;
    }

    static void simul(int r, int c) {
        while(0 <= r && r < n && 0 <= c && c <= m) {
            int[] d = direction.get(mp[r][c]);
            if(union(r, c, r + d[0], c + d[1])) {
                r += d[0];
                c += d[1];
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        direction = new HashMap<>();
        direction.put('U', new int[] {-1, 0});
        direction.put('D', new int[] {1, 0});
        direction.put('L', new int[] {0, -1});
        direction.put('R', new int[] {0, 1});

        mp = new char[n][];
        par = new int[n * m];
        Arrays.fill(par, -1);

        for(int i = 0; i < n; ++i) {
            mp[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                simul(i, j);
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < m; ++j) {
                if(par[i * m + j] < 0) ++cnt;
            }
        }

        System.out.println(cnt);
        br.close();
    }
}
