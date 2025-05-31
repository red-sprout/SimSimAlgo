#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int, int> pii;

int n;
int in[10001];
int dp[10001];
int t[10001];
vector<int> g[10001];
queue<int> q;

void init() {
    cin >> n;
    fill(dp, dp + n, -1);

    int tt, cc;
    for(int node = 1; node <= n; ++node) {
        cin >> tt >> cc;
        t[node] = tt;
        in[node] = cc;

        if(cc == 0) {
            dp[node] = tt;
            q.emplace(node);
            continue;
        }

        for(int i = 0; i < cc; ++i) {
            int prev; cin >> prev;
            g[prev].emplace_back(node);
        }
    }
}

void solution() {
    while(!q.empty()) {
        int cur = q.front(); q.pop();
        for(auto nxt : g[cur]) {
            dp[nxt] = max(dp[nxt], dp[cur] + t[nxt]);
            if(--in[nxt] == 0) {
                q.emplace(nxt);
            }
        }
    }

    int res = 0;
    for(int i = 1; i <= n; ++i) {
        res = max(res, dp[i]);
    }

    cout << res << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    init();
    solution();
    return 0;
}
