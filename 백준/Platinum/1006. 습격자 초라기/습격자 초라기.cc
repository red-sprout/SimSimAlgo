#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
constexpr int INF = 1'000'000'000;

int enemy[10001][2];
int dp[10001][3];

void choragi(int n, int w) {
    for (int i = 2; i <= n; ++i) {
        int bottom = enemy[i - 1][0] + enemy[i][0] <= w ? 1 : 2;
        int top = enemy[i - 1][1] + enemy[i][1] <= w ? 1 : 2;
        int vertical = enemy[i][0] + enemy[i][1] <= w ? 1 : 2;

        dp[i][0] = min(dp[i - 1][1] + bottom, dp[i - 1][2] + 1);
        dp[i][1] = min(dp[i - 1][0] + top, dp[i - 1][2] + 1);
        dp[i][2] = min(min(dp[i - 2][2] + top + bottom, dp[i - 1][2] + vertical), min(dp[i - 1][0] + top + 1, dp[i - 1][1] + bottom + 1));
    }
}

int solution(int n, int w) {
    if (n == 1) return enemy[1][0] + enemy[1][1] <= w ? 1 : 2;
    int ans = INF;
    int bottom = enemy[1][0], top = enemy[1][1];
    memset(dp, 0, sizeof(dp));

    dp[1][0] = dp[1][1] = 1, dp[1][2] = enemy[1][0] + enemy[1][1] <= w ? 1 : 2;
    choragi(n, w);
    ans = min(ans, dp[n][2]);

    if (enemy[1][0] + enemy[n][0] <= w) {
        dp[1][0] = dp[1][1] = 1; dp[1][2] = 2;
        enemy[1][0] = INF;
        choragi(n, w);
        ans = min(ans, dp[n][1]);
        enemy[1][0] = bottom;
    }

    if (enemy[1][1] + enemy[n][1] <= w) {
        dp[1][0] = dp[1][1] = 1; dp[1][2] = 2;
        enemy[1][1] = INF;
        choragi(n, w);
        ans = min(ans, dp[n][0]);
        enemy[1][1] = top;
    }

    if (enemy[1][0] + enemy[n][0] <= w && enemy[1][1] + enemy[n][1] <= w) {
        dp[1][0] = dp[1][1] = 1; dp[1][2] = 2;
        enemy[1][0] = enemy[1][1] = INF;
        choragi(n, w);
        ans = min(ans, dp[n - 1][2]);
        enemy[1][0] = bottom;
        enemy[1][1] = top;
    }

    return ans;
}

int main() {
    FASTIO;
    int t; cin >> t;
    while (t--) {
        int n, w; cin >> n >> w;
        for (int i = 1; i <= n; ++i) cin >> enemy[i][0];
        for (int i = 1; i <= n; ++i) cin >> enemy[i][1];
        cout << solution(n, w) << '\n';
    }
    return 0;
}
