#include <iostream>
#include <vector>

using namespace std;
typedef pair<int, int> pii;

const int N = 20;
vector<int> tree[100'001];
int depth[100'001];
int kind[100'001];
pii dp[N][100'001];
bool vv[6];

void dfs(int cur, int d) {
    depth[cur] = d;
    for (int nxt : tree[cur]) {
        if (depth[nxt]) continue;
        dp[0][nxt].first = cur;
        dp[0][nxt].second = 1 << kind[cur];
        dfs(nxt, d + 1);
    }
}

bool lca(int u, int v, int k) {
    int bits = (1 << kind[u]) | (1 << kind[v]);
    if (depth[u] < depth[v]) swap(u, v);
    int diff = depth[u] - depth[v];

    for (int i = 0; i < N; ++i) {
        if (diff & (1 << i)) {
            bits |= dp[i][u].second;
            u = dp[i][u].first;
        }
    }

    if (u != v) {
        for (int i = N - 1; i >= 0; --i) {
            if (dp[i][u].first != dp[i][v].first) {
                bits |= dp[i][u].second;
                bits |= dp[i][v].second;
                u = dp[i][u].first;
                v = dp[i][v].first;
            }
        }
        bits |= dp[0][u].second;
        bits |= dp[0][v].second;
    }

    return bits & (1 << k);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);

    int n; cin >> n;
    for (int i = 1; i <= n; ++i) {
        cin >> kind[i];
        vv[kind[i]] = true;
    }

    for (int i = 0; i < n - 1; ++i) {
        int u, v; cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }

    dfs(1, 1);

    for (int i = 1; i <= n; ++i) dp[0][i].second |= (1 << kind[i]);
    for (int i = 1; i < N; ++i) {
        for (int j = 1; j <= n; ++j) {
            dp[i][j].first = dp[i - 1][dp[i - 1][j].first].first;
            dp[i][j].second = dp[i - 1][j].second | dp[i - 1][dp[i - 1][j].first].second;
        }
    }

    int m, s, k, cur; cin >> m;
    cin >> s >> k;
    cur = s;
    cout << (vv[k] ? "PLAY\n" : "CRY\n");

    --m;
    while (m--) {
        cin >> s >> k;
        cout << (lca(cur, s, k) ? "PLAY\n" : "CRY\n");
        cur = s;
    }

    return 0;
}
