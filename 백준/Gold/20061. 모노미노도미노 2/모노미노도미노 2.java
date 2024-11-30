import java.io.*;
import java.util.*;

public class Main {
    static int totalScore;
    static boolean[][] green, blue;
    static int[] greenEmptyRow, blueEmptyRow;
    static int[][] d = {{0, -1}, {0, 0}, {0, 1}, {1, 0}};
    static int[] bt = {0, 1, 3, 0};
    static int[] rot = {3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12};

    static void init() {
        totalScore = 0;
        green = new boolean[6][4]; blue = new boolean[6][4];
        greenEmptyRow = new int[4]; blueEmptyRow = new int[4];
        Arrays.fill(greenEmptyRow, 5);
        Arrays.fill(blueEmptyRow, 5);
    }

    static void query(int t, int x, int y) {
        int pos = 4 * x + y;
        simul(green, greenEmptyRow, t, x, y);
        simul(blue, blueEmptyRow, bt[t], rot[pos] / 4, rot[pos] % 4);
    }

    static void simul(boolean[][] board, int[] emptyRow, int t, int x, int y) {
        int[][] pos = new int[2][2];
        pos[0][0] = x; pos[0][1] = y; pos[1][0] = x + d[t][0]; pos[1][1] = y + d[t][1];
        int dist = Integer.MAX_VALUE;
        for(int[] p : pos) {
            dist = Math.min(dist, emptyRow[p[1]] - p[0]);
        }
        for(int[] p : pos) {
            board[p[0] + dist][p[1]] = true;
        }
        updateEmptyRow(board, emptyRow);
        score(board, emptyRow);
        disappear(board, emptyRow);
    }

    static void disappear(boolean[][] board, int[] emptyRow) {
        int cnt = 0;
        for(int row : emptyRow) {
            cnt = Math.max(cnt, 1 - row);
        }
        fall(board, 5, cnt);
        updateEmptyRow(board, emptyRow);
    }

    static void score(boolean[][] board, int[] emptyRow) {
        outer : for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                if(!board[i][j]) continue outer;
            }
            fall(board, i, 1);
            updateEmptyRow(board, emptyRow);
            totalScore++;
        }
    }

    static void updateEmptyRow(boolean[][] board, int[] emptyRow) {
        for(int j = 0; j < 4; j++) {
            int tmp = 5;
            for(int i = 5; i >= 0; i--) {
                if(board[i][j]) tmp = i - 1;
            }
            emptyRow[j] = tmp;
        }
    }

    static void fall(boolean[][] board, int row, int cnt) {
        for(int i = row - cnt; i >= 0; i--) {
            for(int j = 0; j < 4; j++) {
                board[i + cnt][j] = board[i][j];
            }
        }
        for(int i = cnt - 1; i >= 0; i--) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = false;
            }
        }
    }

    static int totalCnt() {
        int cnt = 0;
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                cnt += green[i][j] ? 1 : 0;
                cnt += blue[i][j] ? 1 : 0;
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        init();
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            query(t, x, y);
        }
        System.out.println(totalScore);
        System.out.println(totalCnt());
        br.close();
    }
}