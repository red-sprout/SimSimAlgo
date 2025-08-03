#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

constexpr int dr[] = {-1, 0, 1, 0};
constexpr int dc[] = {0, 1, 0, -1};

int m, n, idx = 0;
char mp[50][50];

int bfs(int sr, int se) {
    queue<tuple<int, int, int, int>> q;
    bool v[50][50][1 << 5];
    q.emplace(sr, se, 0, 0);
    v[sr][se][0] = true;
    while (!q.empty()) {
        auto [r, c, t, k] = q.front(); q.pop();
        if (mp[r][c] == 'E' && k == (1 << idx) - 1) return t;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            int nk = k;
            if (0 <= nr && nr < m && 0 <= nc && nc < n) {
                if ('0' <= mp[nr][nc] && mp[nr][nc] <= '9') nk |= (1 << (mp[nr][nc] - '0'));
                if (mp[nr][nc] != '#' && !v[nr][nc][nk]) {
                    v[nr][nc][nk] = true;
                    q.emplace(nr, nc, t + 1, nk);
                }
            }
        }
    }
    return -1;
}

int main() {
    FASTIO;
    cin >> n >> m;
    int sr, se;
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> mp[i][j];
            if (mp[i][j] == 'X') mp[i][j] = '0' + (idx++);
            if (mp[i][j] == 'S') {
                sr = i; se = j;
            }
        }
    }
    cout << bfs(sr, se) << '\n';
    return 0;
}
