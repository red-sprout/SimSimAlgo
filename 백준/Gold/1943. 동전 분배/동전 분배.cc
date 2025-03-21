#include <iostream>
#include <cstring>

using namespace std;
typedef pair<int, int> pii;

int n, half;
int dp[100][50'001];
pii coins[100];

int dfs(int cur, int price) {
	if (price == half) return 1;
	if (cur == n || price > half) return 0;
	if (dp[cur][price] != -1) return dp[cur][price];
	int res = 0;
	for (int i = 0; i <= coins[cur].second; ++i) {
		res = max(res, dfs(cur + 1, price + coins[cur].first * i));
	}
	return dp[cur][price] = res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	for (int test = 0; test < 3; ++test) {
		memset(dp, -1, sizeof(dp));
		cin >> n;
		int val, cnt;
        half = 0;
		for (int i = 0; i < n; ++i) {
			cin >> val >> cnt;
			coins[i] = { val, cnt };
			half += val * cnt;
		}
		if (half % 2 == 1) {
			cout << 0 << '\n';
			continue;
		}
		half /= 2;
		cout << dfs(0, 0) << '\n';
	}
	return 0;
}