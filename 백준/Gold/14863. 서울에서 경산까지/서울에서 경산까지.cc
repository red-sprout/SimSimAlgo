#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int n, k;
ll dp[100][100001];
ll wt[100], ww[100], bt[100], bw[100];

ll dfs(int cur, int t) {
    if (t > k) return -1e9;
    if (cur == n) return 0;
    ll* res = &dp[cur][t];
    if (*res != -1) return *res;
    *res = 0;
    *res = max(dfs(cur + 1, t + wt[cur]) + ww[cur], dfs(cur + 1, t + bt[cur]) + bw[cur]);
    return *res;
}

int main() {
    FASTIO;
    cin >> n >> k;
    for (int i = 0; i < n; ++i) cin >> wt[i] >> ww[i] >> bt[i] >> bw[i];
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= k ; ++j) {
            dp[i][j] = -1;
        }
    }
    cout << dfs(0, 0) << '\n';
    return 0;
}
