#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef pair<int, int> pii;
typedef long long ll;

struct Edge {
    int u, v, w;
    Edge(int _u, int _v, int _w) : u(_u), v(_v), w(_w) {}
};

const int MAX = 50001;

bool check[200000];
vector<pii> g[MAX], mst[MAX];
vector<Edge> edges;

int n, m;
int par[MAX], dep[MAX], pp[17][MAX], wmax[17][MAX], swmax[17][MAX];

int find_p(int x) { return (par[x] == x ? x : par[x] = find_p(par[x])); }
void union_p(int x, int y) { (x > y ? par[x] : par[y]) = (x > y ? y : x); }

int kruskal() {
    int res = 0, cnt = 0;
    sort(edges.begin(), edges.end(), [](Edge e1, Edge e2) { return e1.w < e2.w; });
    for(int i = 1; i <= n; ++i) par[i] = i;
    for(int i = 0; i < m; ++i) {
        Edge e = edges[i];
        int pu = find_p(e.u);
        int pv = find_p(e.v);
        if(pu != pv) {
            union_p(pu, pv);
            res += e.w;
            mst[e.u].emplace_back(e.v, e.w);
            mst[e.v].emplace_back(e.u, e.w);
            check[i] = true;
            if(++cnt == n - 1) return res;
        }
    }
    return -1;
}

void dfs(int cur, int d) {
    dep[cur] = d;
    for(auto nxt : mst[cur]) {
        if(dep[nxt.first] == 0) {
            pp[0][nxt.first] = cur;
            wmax[0][nxt.first] = nxt.second;
            swmax[0][nxt.first] = -1;
            dfs(nxt.first, d + 1);
        }
    }
}

pii calc(vector<int> nxt) {
    int mm = -1, sm = -1;
    for(int x : nxt) {
        if(x == -1) continue;
        if(mm < x) {
            sm = mm;
            mm = x;
        } else if(sm < x && x < mm) {
            sm = x;
        }
    }
    return { mm, sm };
}

pii lca(int u, int v) {
    int mm = -1, sm = -1;
    pii res;
    if(dep[u] < dep[v]) swap(u, v);
    for(int i = 16; i >= 0; --i) {
        if(dep[pp[i][u]] >= dep[v]) {
            res = calc({ mm, sm, wmax[i][u], swmax[i][u] });
            mm = res.first;
            sm = res.second;
            u = pp[i][u];
        }
    }
    if(u == v) return { mm, sm };
    for(int i = 16; i >= 0; --i) {
        if(pp[i][u] != pp[i][v]) {
            res = calc({ mm, sm, wmax[i][u], swmax[i][u], wmax[i][v], swmax[i][v] });
            mm = res.first;
            sm = res.second;
            u = pp[i][u];
            v = pp[i][v];
        }
    }
    res = calc({ mm, sm, wmax[0][u], swmax[0][u], wmax[0][v], swmax[0][v] });
    mm = res.first;
    sm = res.second;
    return { mm, sm };
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> m;
    int u, v, w;
    for(int i = 0; i < m; ++i) {
        cin >> u >> v >> w;
        g[u].emplace_back(v, w);
        g[v].emplace_back(u, w);
        Edge e(u, v, w);
        edges.emplace_back(e);
    }
    
    int mst_val = kruskal();
    if(mst_val == -1) {
        cout << mst_val << '\n';
        return 0;
    }

    dfs(1, 1);
    for(int i = 1; i < 17; ++i) {
        for(int j = 1; j <= n; ++j) {
            pp[i][j] = pp[i - 1][pp[i - 1][j]];
            pii res = calc({ wmax[i - 1][j], swmax[i - 1][j], wmax[i - 1][pp[i - 1][j]], swmax[i - 1][pp[i - 1][j]] });
            wmax[i][j] = res.first;
            swmax[i][j] = res.second;
        }
    }

    ll smst = 1e18;
    int mm, sm;
    for(int i = 0; i < m; ++i) {
        if(check[i]) continue;
        Edge e = edges[i];
        pii res = lca(e.u, e.v);
        mm = res.first, sm = res.second;
        if(e.w > mm) smst = min(smst, ll(mst_val - mm + e.w));
        else if(e.w > sm && sm != -1) smst = min(smst, ll(mst_val - sm + e.w));
    }

    cout << (smst == 1e18 ? -1 : smst) << '\n';
    return 0;
}
