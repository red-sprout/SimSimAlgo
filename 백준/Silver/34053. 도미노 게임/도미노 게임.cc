#include <iostream>
#include <queue>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;
typedef long long ll;

int main() {
    FASTIO;
    int n, m; cin >> n >> m;
    int mp[n][m];
    queue<pii> q;
    pii min_p = {0, 0};
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> mp[i][j];
            if (mp[min_p.first][min_p.second] > mp[i][j]) min_p = {i, j};
        }
    }

    int dr[] = {-1, 0, 1, 0};
    int dc[] = {0, 1, 0, -1};
    ll res = 0;
    q.emplace(min_p);
    if (mp[min_p.first][min_p.second]) {
        res += mp[min_p.first][min_p.second];
        for (int i = 0; i < 4; i++) {
            int nr = min_p.first + dr[i];
            int nc = min_p.second + dc[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m) {
                mp[nr][nc] -= mp[min_p.first][min_p.second];
                if (mp[nr][nc] == 0) q.emplace(nr, nc);
                break;
            }
        }
        mp[min_p.first][min_p.second] = 0;
    } else {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mp[min_p.first][min_p.second] == 0) q.emplace(i, j);
            }
        }
    }

    while (!q.empty()) {
        auto [r, c] = q.front(); q.pop();
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m && mp[nr][nc] != 0) {
                res += mp[nr][nc];
                mp[nr][nc] = 0;
                q.emplace(nr, nc);
            }
        }
    }

    cout << res << '\n';
    return 0;
}
