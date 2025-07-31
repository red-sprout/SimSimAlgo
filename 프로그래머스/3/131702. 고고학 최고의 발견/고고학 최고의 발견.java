import java.util.*;

class Solution {
    private int M, N;
    private int[] dr = {0, -1, 0, 1, 0};
    private int[] dc = {0, 0, -1, 0, 1};
    
    private void rotate(int[][] arr, int row, int col) {
        for(int i = 0; i < 5; ++i) {
            int r = row + dr[i];
            int c = col + dc[i];
            if(0 <= r && r < M && 0 <= c && c < N) {
                ++arr[r][c];
                if(arr[r][c] >= 4) arr[r][c] -= 4;
            }
        }
    }
    
    private int[][] copy(int[][] arr){
        int[][] res = new int[M][N];
        for(int r = 0; r < M; ++r) {
            for(int c = 0; c < N; ++c) {
                res[r][c] = arr[r][c];
            }
        }
        return res;
    }
    
    private int simul(int[][] clockHands) {
        int[][] arr = copy(clockHands);
        int cnt = 0;
        for(int i = 1; i < M; ++i) {
            for(int j = 0; j < N; ++j) {
                int diff = (4 - arr[i - 1][j]) % 4;
                cnt += diff;
                for(int k = 0; k < diff; ++k) rotate(arr, i, j);
            }
        }
        for(int j = 0; j < N; ++j) {
            if(arr[M - 1][j] != 0) return 1_000_000_000;
        }
        return cnt;
    }
    
    private int dfs(int cur, int[][] clockHands) {
        if(cur == N) return simul(clockHands);
        int res = 1_000_000_000;
        for(int i = 0; i < 4; ++i) {
            for(int j = 0; j < i; ++j) rotate(clockHands, 0, cur);
            res = Math.min(res, dfs(cur + 1, clockHands) + i);
            for(int j = 0; j < 4 - i; ++j) rotate(clockHands, 0, cur); 
        }
        return res;
    }
    
    public int solution(int[][] clockHands) {
        M = clockHands.length;
        N = clockHands[0].length;
        return dfs(0, copy(clockHands));
    }
}