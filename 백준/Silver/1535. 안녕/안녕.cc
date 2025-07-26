#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n;
int l[20], j[20];
int dp[100][2000];

int dfs(int cur, int loss) {
    if (cur == n) return 0;
    if (dp[cur][loss] != -1) return dp[cur][loss];
    int ans = 0;
    ans = max(ans, dfs(cur + 1, loss));
    if (loss + l[cur] < 100) ans = max(ans, dfs(cur + 1, loss + l[cur]) + j[cur]);
    return dp[cur][loss] = ans;
}

int main() {
    FASTIO;
    cin >> n;
    for (int i = 0; i < n; i++) cin >> l[i];
    for (int i = 0; i < n; i++) cin >> j[i];
    for (int i = 0; i < 100; i++) {
        for (int j = 0; j < 2000; j++) {
            dp[i][j] = -1;
        }
    }
    cout << dfs(0, 0) << '\n';
    return 0;
}
