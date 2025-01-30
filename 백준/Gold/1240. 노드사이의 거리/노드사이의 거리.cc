#include <iostream>
#include <cmath>
#include <vector>

using namespace std;
typedef pair<int, int> pii;

int n, m, sz;
int depth[1001];
int dist[1001];
int dp[11][1001];

vector<pii> tree[1001];

void dfs(int cur, int d) {
    depth[cur] = d;
    int v, w;
    for (pii nxt : tree[cur]) {
        v = nxt.first;
        w = nxt.second;
        if (depth[v] == 0) {
            dist[v] = dist[cur] + w;
            dp[0][v] = cur;
            dfs(v, d + 1);
        }
    }
}

int lca(int a, int b) {
    int u, v, d;
    if (depth[a] > depth[b]) {
        u = a;
        v = b; 
        d = depth[a] - depth[b];
    } else {
        u = b;
        v = a;
        d = depth[b] - depth[a];
    }
    
    for (int i = sz; i >= 0; i--) {
        if (d & (1 << i)) {
            u = dp[i][u];
        }
    }

    if (u == v) return u;

    for (int i = sz; i >= 0; i--) {
        if (dp[i][u] != dp[i][v]) {
            u = dp[i][u];
            v = dp[i][v];
        }
    }

    return dp[0][u];
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> m;
    for (int i = 0; i < n - 1; ++i) {
        int u, v, w;
        pii edge;
        cin >> u >> v >> w;
        edge = { v, w };
        tree[u].emplace_back(edge);
        edge = { u, w };
        tree[v].emplace_back(edge);
    }

    dfs(1, 1);
    sz = int(ceil(log(n) / log(2)));
    for (int i = 1; i <= sz; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j] = dp[i - 1][dp[i - 1][j]];
        }
    }

    
    while (m--) {
        int u, v, l;
        cin >> u >> v;
        l = lca(u, v);
        cout << dist[u] + dist[v] - 2 * dist[l] << '\n';
    }

    return 0;
}