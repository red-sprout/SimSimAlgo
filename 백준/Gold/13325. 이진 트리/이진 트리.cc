#include <bits/stdc++.h>

using namespace std;

constexpr int MAX = 1 << 22;

int k, sum;
int w[MAX], dp[MAX];

int dfs(int cur) {
    if (cur >= (1 << (k + 1))) return 0;
    int* res = &dp[cur];
    if (*res != -1) return *res;
    *res = 0;
    *res = max(*res, dfs(cur << 1) + w[cur << 1]);
    *res = max(*res, dfs(cur << 1 | 1) + w[cur << 1 | 1]);
    return *res;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    cin >> k;
    int wsum = 0;
    for (int i = 2; i < 1 << (k + 1); i++) {
        cin >> w[i];
        wsum += w[i];
    }
    memset(dp, -1, sizeof(dp));
    dfs(1);
    for (int i = 2; i < 1 << (k + 1); i++) {
        wsum += dp[i >> 1] - dp[i] - w[i];
    }
    cout << wsum << '\n';
    return 0;
}