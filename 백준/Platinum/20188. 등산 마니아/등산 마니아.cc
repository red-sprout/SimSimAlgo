#include <iostream>
#include <vector>
#define MAX 300'001

using namespace std;
typedef long long ll;

int n;
ll dp[MAX];
vector<int> tree[MAX];

ll comb(int x) {
	return x * (x - 1) / 2;
}

ll dfs(int cur) {
	dp[cur] = 1;
	for (int nxt : tree[cur]) {
		if (dp[nxt] == 0) {
			dp[cur] += dfs(nxt);
		}
	}
	return dp[cur];
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	int a, b;
	fill(dp, dp + n + 1, 0);
	for (int i = 0; i < n - 1; ++i) {
		cin >> a >> b;
		tree[a].emplace_back(b);
		tree[b].emplace_back(a);
	}
	dfs(1);
	ll res = 0;
	for (int i = 1; i < n + 1; ++i) {
		res += comb(n) - comb(n - dp[i]);
	}
	cout << res - comb(n) << '\n';
	return 0;
}