#include <iostream>
#include <cstring>

using namespace std;

int n;
int aa[2000], bb[2000];
int dp[2000][2000][3];

int dfs(int a, int b, int st) {
	if (a == n || b == n) return 0;
	if (dp[a][b][st] != -1) return dp[a][b][st];
	int res = 0;
	res = max(res, dfs(a + 1, b, 0));
	res = max(res, dfs(a + 1, b + 1, 1));
	if (aa[a] > bb[b]) {
		res = max(res, dfs(a, b + 1, 2) + bb[b]);
	}
	return dp[a][b][st] = res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n;
	for (int i = 0; i < n; ++i) {
		cin >> aa[i];
	}

	for (int i = 0; i < n; ++i) {
		cin >> bb[i];
	}

	memset(dp, -1, sizeof(dp));
	cout << dfs(0, 0, 0) << '\n';
	return 0;
}