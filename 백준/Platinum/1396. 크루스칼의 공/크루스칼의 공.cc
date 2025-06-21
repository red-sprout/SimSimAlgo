#include <bits/stdc++.h>

using namespace std;

const int SZ = 200001;

struct Edge {
    int a, b, c;
    Edge() {}
    Edge(int a, int b, int c) : a(a), b(b), c(c) {}
};

int n, m;
int par[18][SZ], dep[SZ], p[SZ], tem[SZ], cnt[SZ];
vector<int> g[SZ];
vector<Edge> edges;

bool comp(const Edge &a, const Edge &b) {
    return a.c < b.c;
}

int find_p(int x) {
    return p[x] == x ? x : p[x] = find_p(p[x]);
}

void dfs(int cur, int d) {
    dep[cur] = d;
    for (auto nxt : g[cur]) {
        if (dep[nxt] == 0) {
            par[0][nxt] = cur;
            dfs(nxt, d + 1);
        }
    }
}

int lca(int u, int v) {
    if (dep[u] < dep[v]) swap(u, v);
    for (int i = 17; i >= 0; i--) {
        if (dep[u] - dep[v] & 1 << i) {
            u = par[i][u];
        }
    }
    if (u == v) return u;
    for (int i = 17; i >= 0; i--) {
        if (par[i][u] != par[i][v]) {
            u = par[i][u];
            v = par[i][v];
        }
    }
    return par[0][u];
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> n >> m;
    int a, b, c;
    edges.resize(m);

    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        edges[i] = Edge(a, b, c);
    }

    sort(edges.begin(), edges.end(), comp);
    for (int i = 0; i < SZ; i++) {
        p[i] = i; cnt[i] = 1;
    }

    for (auto e : edges) {
        a = find_p(e.a); b = find_p(e.b);
        if (a != b) {
            ++n;
            g[n].emplace_back(a);
            g[n].emplace_back(b);
            p[a] = n; p[b] = n;
            tem[n] = e.c;
            cnt[n] = cnt[a] + cnt[b];
        }
    }

    for (int i = 1; i <= n; ++i) {
        if (p[i] == i) dfs(i, 1);
    }

    for (int i = 1; i < 18; ++i) {
        for (int j = 1; j <= n; ++j) {
            par[i][j] = par[i - 1][par[i - 1][j]];
        }
    }

    int q; cin >> q;
    while (q--) {
        int u, v; cin >> u >> v;
        if (find_p(u) != find_p(v)) cout << "-1\n";
        else {
            int l = lca(u, v);
            cout << tem[l] << ' ' << cnt[l] << '\n';
        }
    }
    return 0;
}