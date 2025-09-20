import java.io.*;
import java.util.*;

public class Main {
    static boolean valid(int tx, int ty, int x, int y, int d) {
        boolean res = true;
        switch(d) {
            case 1:
                res = tx < x && ty == y;
                break;
            case 2:
                res = tx < x && ty > y;
                break;
            case 3:
                res = tx == x && ty > y;
                break;
            case 4:
                res = tx > x && ty > y;
                break;
            case 5:
                res = tx > x && ty == y;
                break;
            case 6:
                res = tx > x && ty < y;
                break;
            case 7:
                res = tx == x && ty < y;
                break;
            default:
                res = tx < x && ty < y;
                break;
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] info = new int[m][3];

        for(int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
            info[i][2] = Integer.parseInt(st.nextToken());
        }

        int x = 0, y = 0;
        outer : for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= n; ++j) {
                boolean flag = true;
                for(int[] ele : info) {
                    if(!valid(i, j, ele[0], ele[1], ele[2])) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }

        System.out.println(x + " " + y);
        br.close();
    }
}
