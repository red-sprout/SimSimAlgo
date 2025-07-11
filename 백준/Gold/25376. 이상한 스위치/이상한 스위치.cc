#include <iostream>
#include <vector>
#include <queue>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

int n;
bool vis[1 << 20];
vector<int> b[20];

int bfs(int v) {
    queue<pii> q;
    q.emplace(v, 0);
    vis[v] = true;

    while (!q.empty()) {
        auto [cv, cc] = q.front(); q.pop();
        if (cv == (1 << n) - 1) return cc;

        for (int i = 0; i < n; ++i) {
            if (cv & 1 << i) continue;
            int nv = cv | (1 << i);
            for (auto e : b[i]) {
                nv ^= 1 << e;
            }
            if (!vis[nv]) {
                vis[nv] = true;
                q.emplace(nv, cc + 1);
            }
        }
    }

    return -1;
}

int main() {
    FASTIO;
    cin >> n;
    int v = 0;
    for (int i = 0; i < n; ++i) {
        int idx; cin >> idx;
        if (idx) v |= 1 << i;
    }
    int c;
    for (int i = 0; i < n; ++i) {
        cin >> c;
        b[i].resize(c);
        for (int j = 0; j < c; ++j) {
            cin >> b[i][j];
            --b[i][j];
        }
    }

    cout << bfs(v) << '\n';
    return 0;
}
