#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
constexpr int dr[] = {-1, 0, 1, 0};
constexpr int dc[] = {0, -1, 0, 1};

int n, m, id, tr, tc, fuel;
bool mp[21][21];
bool vis[21][21];

struct Info {
    int r, c, d;
    Info(int r, int c, int d) : r(r), c(c), d(d) {}
};

struct Path {
    int id, pr, pc, ar, ac;
    bool valid = true;
    Path(int id, int pr, int pc, int ar, int ac) : id(id), pr(pr), pc(pc), ar(ar), ac(ac) {}
};

vector<Path> paths;

bool cannot_find() {
    queue<Info> q;
    memset(vis, false, sizeof(vis));
    q.emplace(tr, tc, 0);
    vis[tr][tc] = true;

    int dist = 1000;
    while (!q.empty()) {
        auto[r, c, d] = q.front(); q.pop();
        if (d > fuel) continue;
        for (int i = 1; i <= m; ++i) {
            Path p = paths[i];
            if (p.valid && p.pr == r && p.pc == c) {
                if (d < dist ||
                   (d == dist && r < tr) ||
                   (d == dist && r == tr && c < tc)) {
                    dist = d; id = i; tr = r; tc = c;
                }
            }
        }
        for (int i = 0; i < 4; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (1 <= nr && nr <= n && 1 <= nc && nc <= n && !vis[nr][nc] && !mp[nr][nc]) {
                vis[nr][nc] = true;
                q.emplace(nr, nc, d + 1);
            }
        }
    }

    if (id == -1) return true;
    fuel -= dist;
    return false;
}

bool cannot_arrive() {
    queue<Info> q;
    Path *p = &paths[id];
    memset(vis, false, sizeof(vis));
    q.emplace(tr, tc, 0);
    vis[tr][tc] = true;

    while (!q.empty()) {
        auto [r, c, d] = q.front(); q.pop();
        if (d > fuel) continue;
        if (p->ar == r && p->ac == c) {
            tr = r; tc = c; fuel += d;
            p->valid = false;
            id = -1;
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (1 <= nr && nr <= n && 1 <= nc && nc <= n && !vis[nr][nc] && !mp[nr][nc]) {
                vis[nr][nc] = true;
                q.emplace(nr, nc, d + 1);
            }
        }
    }
    return true;
}

int main() {
    FASTIO;
    cin >> n >> m >> fuel;
    for (int i = 1; i <= n; ++i) {
        for (int j = 1; j <= n; ++j) {
            cin >> mp[i][j];
        }
    }
    cin >> tr >> tc;
    int pr, pc, ar, ac;
    paths.emplace_back(-1, -1, -1, -1, -1);
    for (int i = 1; i <= m; ++i) {
        cin >> pr >> pc >> ar >> ac;
        paths.emplace_back(i, pr, pc, ar, ac);
    }

    for (int i = 1; i <= m; ++i) {
        if (cannot_find()) {
            cout << -1 << '\n';
            return 0;
        }
        if (cannot_arrive()) {
            cout << -1 << '\n';
            return 0;
        }
    }

    cout << fuel << '\n';
    return 0;
}
