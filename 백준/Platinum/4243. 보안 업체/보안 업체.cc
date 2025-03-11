#include <iostream>
#include <vector>
#include <cstring>
#define MAX 1e18

typedef long long ll;
using namespace std;

int n;
ll sum[101];
ll dp[101][101][2];

ll dfs(int l, int r, int d) {
    if (dp[l][r][d] != -1) return dp[l][r][d];
    if (l == 1 && r == n) return 0;
    ll res = MAX;
    int pos = d ? r : l;
    if (l > 1) res = min(res, dfs(l - 1, r, 0) + (sum[pos] - sum[l - 1]) * (n - r + l - 1));
    if (r < n) res = min(res, dfs(l, r + 1, 1) + (sum[r + 1] - sum[pos]) * (n - r + l - 1));
    return dp[l][r][d] = res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int t, a, i;
    cin >> t;
    sum[0] = 0; sum[1] = 0;
    while (t-- > 0) {
        cin >> n >> a;
        memset(dp, -1, sizeof(dp));
        for (i = 2; i <= n; ++i) {
            cin >> sum[i];
            sum[i] += sum[i - 1];
        }
        cout << min(dfs(a, a, 0), dfs(a, a, 1)) << '\n';
    }
    return 0;
}