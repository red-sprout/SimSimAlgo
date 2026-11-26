#include <bits/stdc++.h>

using namespace std;

#define FASTIO ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr)

using ll = long long;
using ull = unsigned long long;
using pii = pair<int, int>;
using pll = pair<ll, ll>;

constexpr int INF = 0x3f3f3f3f;
constexpr ll LINF = 0x3f3f3f3f3f3f3f3fLL;
constexpr int MOD = 1'000'000'007;

struct query {
    int i, p;
    char t;
    query(int _i, char _t, int _p) : i(_i), p(_p), t(_t) { }
};

vector<int> g[1010];
vector<pii> edges;
int dist[1010];

void bfs(int s) {
    queue<int> q;
    q.emplace(s);
    while(q.size()) {
        int cur = q.front();
        q.pop();
        for(int nxt : g[cur]) {
            if(dist[nxt] > dist[cur] + 1) {
                dist[nxt] = dist[cur] + 1;
                q.emplace(nxt);
            }
        }
    }
}

void set_graph(int m, vector<query> updates) {
    vector<bool> v(m + 1);
    for(query u : updates) {
        v[u.p] = 1;
    }
    for(int i = 1; i <= m; ++i) {
        if(v[i]) continue;
        pii e = edges[i];
        g[e.first].emplace_back(e.second);
    }
}

void add_graph(int p) {
    int s = edges[p].first, e = edges[p].second;
    g[s].emplace_back(e);
    bfs(s);
}

int main() {
    FASTIO;
    
    int n, m, q, u, v;
    cin >> n >> m >> q;
    edges.emplace_back(0, 0);
    for(int i = 0; i < m; ++i) {
        cin >> u >> v;
        edges.emplace_back(u, v);
    }

    char t;
    int p;
    vector<query> queries;
    vector<query> updates;
    for(int i = 0; i < q; ++i) {
        cin >> t >> p;
        queries.emplace_back(i, t, p);
        if(t == 'U') updates.emplace_back(i, t, p);
    }

    set_graph(m, updates);
    fill(dist, dist + 1010, MOD);
    dist[1] = 0;
    bfs(1);

    vector<int> res;
    reverse(queries.begin(), queries.end());
    for(query qry : queries) {
        if(qry.t == 'U') {
            add_graph(qry.p);
        } else {
            res.emplace_back(dist[qry.p] == MOD ? -1 : dist[qry.p]);
        }
    }
    
    reverse(res.begin(), res.end());
    for(int r : res) {
        cout << r << '\n';
    }
    return 0;
}