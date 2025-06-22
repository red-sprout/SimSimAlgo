#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    int T; cin >> T;
    int dp[15][15] = { 0 };
    for (int i = 0; i < 15; i++) dp[0][i] = i;
    for (int i = 1; i < 15; i++) {
        for (int j = 1; j < 15; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }
    while (T--) {
        int k, n; cin >> k >> n;
        cout << dp[k][n] << '\n';
    }
    return 0;
}