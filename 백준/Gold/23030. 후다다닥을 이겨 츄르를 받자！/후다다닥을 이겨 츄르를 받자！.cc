#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

bool vis[1111];
int dist[1111];
vector<pii> g[1111];

int dijkstra(int t, int u, int v) {
    fill(vis, vis + 1111, 0);
    fill(dist, dist + 1111, 1e9);
    priority_queue<pii, vector<pii>, greater<>> pq;
    dist[u] = 0;
    pq.emplace(0, u);
    while (!pq.empty()) {
        auto [ct, cu] = pq.top(); pq.pop();
        if (cu == v) return ct;
        if (vis[cu]) continue;
        vis[cu] = 1;
        for (auto nxt : g[cu]) {
            int nt = nxt.second ? t : 1;
            if (!vis[nxt.first] && dist[nxt.first] > dist[cu] + nt) {
                dist[nxt.first] = dist[cu] + nt;
                pq.emplace(dist[nxt.first], nxt.first);
            }
        }
    }
    return -1;
}

int main() {
    FASTIO;
    int n; cin >> n;
    for (int i = 1; i <= n; ++i) {
        int cnt; cin >> cnt;
        int bf = i * 100 + 1;
        for (int j = 2; j <= cnt; ++j) {
            int nxt = i * 100 + j;
            g[bf].emplace_back(nxt, 0);
            g[nxt].emplace_back(bf, 0);
            bf = nxt;
        }
    }
    int m; cin >> m;
    int p1, p2, q1, q2, p, q;
    for (int i = 0; i < m; ++i) {
        cin >> p1 >> p2 >> q1 >> q2;
        p = p1 * 100 + p2;
        q = q1 * 100 + q2;
        g[p].emplace_back(q, 1);
        g[q].emplace_back(p, 1);
    }
    int k; cin >> k;
    int t, u1, u2, v1, v2, u, v;
    for (int i = 0; i < k; ++i) {
        cin >> t >> u1 >> u2 >> v1 >> v2;
        u = u1 * 100 + u2;
        v = v1 * 100 + v2;
        cout << dijkstra(t, u, v) << '\n';
    }
    return 0;
}
