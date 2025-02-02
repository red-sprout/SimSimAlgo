#include <iostream>

using namespace std;

int n, m;
int dr[] = { -1, 0, 1, 0 };
int dc[] = { 0, -1, 0, 1 };
char map[51][51];
int dp[51][51];
bool v[51][51];

int dfs(int r, int c) {
    if (v[r][c]) {
        cout << -1 << '\n';
        exit(0);
    }

    if (dp[r][c] != -1) return dp[r][c];

    dp[r][c] = 0;
    v[r][c] = true;
    int val = map[r][c] - '0';
    for (int d = 0; d < 4; ++d) {
        int nr = r + dr[d] * val;
        int nc = c + dc[d] * val;

        if (0 <= nr && nr < n && 0 <= nc && nc < m && map[nr][nc] != '0') {
            dp[r][c] = max(dp[r][c], dfs(nr, nc) + 1);
        }
    }

    v[r][c] = false;
    return dp[r][c];
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    
    cin >> n >> m;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> map[i][j];
            if (map[i][j] == 'H') map[i][j] = '0';
            dp[i][j] = -1;
            v[i][j] = false;
        }
    }

    cout << dfs(0, 0) + 1 << '\n';
    return 0;
}
