#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int, int> pii;

int n, m, k, sz;
int candy[30001];
pii g[30001];
int dp[30001];
bool v[30001];
vector<int> graph[30001];

pii bfs(int start) {
    queue<int> q;
    q.emplace(start);
    v[start] = true;
    pii result = { 1, candy[start] };

    while (!q.empty()) {
        int cur = q.front(); q.pop();
        for (int nxt : graph[cur]) {
            if (!v[nxt]) {
                v[nxt] = true;
                q.emplace(nxt);
                result.first++;
                result.second += candy[nxt];
            }
        }
    }
    return result;
}

int knapsack() {
    fill(dp, dp + k, 0);
    for (int i = 0; i < sz; ++i) {
        for (int j = k - 1; j >= g[i].first; --j) {
            dp[j] = max(dp[j], dp[j - g[i].first] + g[i].second);
        }
    }
    return dp[k - 1];
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);

    cin >> n >> m >> k;
    for (int i = 1; i <= n; ++i) {
        cin >> candy[i];
    }

    for (int i = 1, a, b; i <= m; ++i) {
        cin >> a >> b;
        graph[a].emplace_back(b);
        graph[b].emplace_back(a);
    }

    sz = 0;
    for (int i = 1; i <= n; ++i) {
        if (!v[i]) {
            g[sz++] = bfs(i);
        }
    }

    cout << knapsack() << '\n';
    return 0;
}
