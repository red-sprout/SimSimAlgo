#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;
constexpr ll MOD = 1'000'000'007;
constexpr int MAX = 100'001;

ll f[MAX] = { 1 };
ll dp[MAX][2];

int main() {
    FASTIO;
    int n; cin >> n;
    for (int i = 1; i <= n; ++i) f[i] = f[i - 1] * i % MOD;
    dp[1][0] = dp[1][1] = 0;
    dp[2][0] = dp[2][1] = 1;
    for (int i = 3; i <= n; ++i) {
        dp[i][0] = (i - 1) * dp[i - 1][1] % MOD;
        dp[i][1] = (dp[i - 1][0] + (i - 1) * dp[i - 1][1] % MOD) % MOD;
    }
    cout << f[n] * dp[n][0] % MOD << '\n';
    return 0;
}
