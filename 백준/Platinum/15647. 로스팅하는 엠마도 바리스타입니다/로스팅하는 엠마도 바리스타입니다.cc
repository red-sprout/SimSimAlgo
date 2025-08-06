#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

constexpr int SZ = 300'001;

int n;
ll dep[SZ], par[SZ], sub[SZ], dist[SZ], ans[SZ];
vector<pll> tree[SZ];

ll dfs1(int cur, int depth) {
    dep[cur] = depth;
    sub[cur] = 1;
    for (auto [nxt, d] : tree[cur]) {
        if (dep[nxt] == 0) {
            par[nxt] = cur;
            sub[cur] += dfs1(nxt, depth + 1);
        }
    }
    return sub[cur];
}

ll dfs2(int cur) {
    dist[cur] = 0;
    for (auto [nxt, d] : tree[cur]) {
        if (dep[nxt] > dep[cur]) {
            dist[cur] += dfs2(nxt) + d * sub[nxt];
        }
    }
    return dist[cur];
}

void dfs3(int cur) {
    for (auto [nxt, d] : tree[cur]) {
        if (dep[nxt] > dep[cur]) {
            ans[nxt] = ans[cur] + (n - 2 * sub[nxt]) * d;
            dfs3(nxt);
        }
    }
}

int main() {
    FASTIO;
    cin >> n;
    int u, v, d;
    for (int i = 0; i < n - 1; ++i) {
        cin >> u >> v >> d;
        tree[u].emplace_back(v, d);
        tree[v].emplace_back(u, d);
    }
    dfs1(1, 1);
    ans[1] = dfs2(1);
    dfs3(1);
    for (int i = 1; i <= n; ++i) cout << ans[i] << '\n';
    return 0;
}
