#include <iostream>
#include <cstring>
#define INF -1e9;

using namespace std;

int n, s, m;
int v[50];
int dp[50][1001];

int dfs(int cur, int vol) {
	if (vol < 0 || vol > m) return INF;
	if (cur == n) return 0;
	int* res = &dp[cur][vol];
	if (*res != -1) return *res;
	*res = INF;
	*res = max(*res, dfs(cur + 1, vol - v[cur]) - v[cur]);
	*res = max(*res, dfs(cur + 1, vol + v[cur]) + v[cur]);
	return *res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> s >> m;
	for (int i = 0; i < n; ++i) cin >> v[i];
	memset(dp, -1, sizeof(dp));
	dfs(0, s);
	cout << (dp[0][s] + s < 0 ? -1 : dp[0][s] + s) << '\n';
	return 0;
}