#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef vector<int> vi;
typedef long long ll;

constexpr int mod = 1'000'000'007;

int id = 0;
vi depth, in, out, color, iton;
vector<vi> g, tree;

void resize(int n) {
    depth.resize(n + 1);
    in.resize(n + 1);
    out.resize(n + 1);
    color.resize(n + 1);
    iton.resize(n + 1);
    g.resize(n + 1);
    tree.resize(4 * n + 1);
}

void dfs(int cur, int d) {
    depth[cur] = d;
    in[cur] = ++id;
    iton[id] = cur;
    for(auto nxt : g[cur]) {
        if(depth[nxt] == 0) dfs(nxt, d + 1);
    }
    out[cur] = id;
}

vi merge(vi v1, vi v2) {
    int n1 = v1.size(), n2 = v2.size();
    int i1 = 0, i2 = 0, idx = 0;
    vi res(n1 + n2);
    while(i1 < n1 && i2 < n2) {
        if(v1[i1] < v2[i2]) res[idx++] = v1[i1++];
        else res[idx++] = v2[i2++];
    }
    while(i1 < n1) res[idx++] = v1[i1++];
    while(i2 < n2) res[idx++] = v2[i2++];
    return res;
}

void init(int node, int s, int e) {
    if(s == e) {
        tree[node] = { color[iton[s]] };
        return;
    }
    int m = (s + e) >> 1;
    init(node << 1, s, m);
    init(node << 1 | 1, m + 1, e);
    tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
}

int get_cnt(int node, int s, int e, int ts, int te, int val) {
    if(te < s || e < ts) return 0;
    if(ts <= s && e <= te) return upper_bound(tree[node].begin(), tree[node].end(), val) - tree[node].begin();
    int m = (s + e) >> 1;
    return get_cnt(node << 1, s, m, ts, te, val) + get_cnt(node << 1 | 1, m + 1, e, ts, te, val);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, m, c, a, b; cin >> n >> m >> c;
    resize(n); for(int i = 1; i <= n; ++i) cin >> color[i];
    for(int i = 0; i < n - 1; ++i) {
        cin >> a >> b;
        g[a].emplace_back(b);
        g[b].emplace_back(a);
    }
    dfs(1, 1);
    init(1, 1, n);
    int node, val;
    ll res = 0;
    while(m--) {
        cin >> node >> val;
        res = (res + get_cnt(1, 1, n, in[node], out[node], val)) % mod;
    }
    cout << res << '\n';
    return 0;
}
