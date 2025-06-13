#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

typedef long long ll;
typedef pair<int, ll> pil;

struct Edge {
    int u, v; ll w;
    Edge(int _u, int _v, ll _w) : u(_u), v(_v), w(_w) {}
};

const int MAX = 200'001;
int n, m, p[MAX], depth[MAX], pp[20][MAX];
ll wmax[20][MAX];
vector<pil> g[MAX], mst[MAX];
vector<Edge> edges, sorted_edges;

int find_p(int x) { return p[x] == x ? x : p[x] = find_p(p[x]); }
void union_p(int x, int y) { (x > y ? p[x] : p[y]) = (x > y ? y : x); }
bool comp(Edge e1, Edge e2) { return e1.w < e2.w; }

void dfs(int cur, int d) {
    depth[cur] = d;
    for(auto nxt : mst[cur]) {
        if(depth[nxt.first] == 0) {
            pp[0][nxt.first] = cur;
            wmax[0][nxt.first] = nxt.second;
            dfs(nxt.first, d + 1);
        }
    }
}

ll lca(int u, int v) {
    ll res = 0;
    if(depth[u] < depth[v]) swap(u, v);
    for(int i = 19; i >= 0; --i) {
        if(depth[pp[i][u]] >= depth[v]) {
            res = max(res, wmax[i][u]);
            u = pp[i][u];
        }
    }
    if(u == v) return res;
    for(int i = 19; i >= 0; --i) {
        if(pp[i][u] != pp[i][v]) {
            res = max(res, max(wmax[i][u], wmax[i][v]));
            u = pp[i][u];
            v = pp[i][v];
        }
    }
    res = max(res, max(wmax[0][u], wmax[0][v]));
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> m;
    int u, v; ll w;
    for(int i = 0; i < m; ++i) {
        cin >> u >> v >> w;
        g[u].emplace_back(v, w);
        g[v].emplace_back(u, w);
        Edge e(u, v, w);
        edges.emplace_back(e);
        sorted_edges.emplace_back(e);
    }

    ll mst_val = 0;
    int cnt = 0;
    sort(sorted_edges.begin(), sorted_edges.end(), comp);
    for(int i = 1; i <= n; ++i) p[i] = i;
    for(auto& e : sorted_edges) {
        int u = find_p(e.u), v = find_p(e.v);
        if (u != v) {
            union_p(u, v);
            mst_val += e.w;
            mst[e.u].emplace_back(e.v, e.w);
            mst[e.v].emplace_back(e.u, e.w);
            if (++cnt == n - 1) break;
        }
    }

    dfs(1, 1);
    for(int i = 1; i < 20; ++i) {
        for(int j = 1; j <= n; ++j) {
            pp[i][j] = pp[i - 1][pp[i - 1][j]];
            wmax[i][j] = max(wmax[i - 1][j], wmax[i - 1][pp[i - 1][j]]);
        }
    }
    
    for (auto& e : edges) cout << mst_val - lca(e.u, e.v) + e.w << '\n';

    return 0;
}
