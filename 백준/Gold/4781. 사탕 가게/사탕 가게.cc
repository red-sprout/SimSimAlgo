#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int n, m;
int c[5001], p[5001], dp[10001];

int main() {
    FASTIO;
    while (1) {
        double tmp; cin >> n >> tmp; m = int(tmp * 100 + 0.5);
        if (n == 0 && m == 0) break;
        for (int i = 0; i < n; ++i) {
            cin >> c[i] >> tmp; p[i] = int(tmp * 100 + 0.5);
        }
        fill(dp, dp + 10001, 0);
        for (int i = 0; i < n; ++i) {
            for (int j = p[i]; j <= m; ++j) {
                dp[j] = max(dp[j], dp[j - p[i]] + c[i]);
            }
        }
        int ans = 0;
        for (int i = 0; i <= m; ++i) ans = max(ans, dp[i]);
        cout << ans << '\n';
    }
    return 0;
}
