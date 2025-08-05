#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

int n, t;
int dp[100][10001];
pii p[100];

int dfs(int cur, int time) {
    if (cur == n) return 0;
    int* res = &dp[cur][time];
    if (*res != -1) return *res;
    *res = 0;
    *res = max(*res, dfs(cur + 1, time));
    if (time + p[cur].first <= t) {
        *res = max(*res, dfs(cur + 1, time + p[cur].first) + p[cur].second);
    }
    return *res;
}

int main() {
    FASTIO;
    cin >> n >> t;
    for (int i = 0; i < n; i++) cin >> p[i].first >> p[i].second;
    for (int i = 0; i < n; i++) fill(dp[i], dp[i] + 10001, -1);
    cout << dfs(0, 0) << '\n';
    return 0;
}