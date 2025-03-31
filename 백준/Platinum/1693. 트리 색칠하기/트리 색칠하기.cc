#include <iostream>
#include <vector>
#include <cstring>
#define MAX 1'000'000'000

using namespace std;
typedef long long ll;

int n;
int depth[100'001];
ll dp[100'001][20];
vector<int> tree[100'001];

void dfs1(int cur, int d) {
	depth[cur] = d;
	for (int nxt : tree[cur]) {
		if (depth[nxt] == 0) {
			dfs1(nxt, d + 1);
		}
	}
}

ll dfs2(int cur, int color) {
	if (dp[cur][color] != -1) return dp[cur][color];
	dp[cur][color] = 0;
	for (int nxt : tree[cur]) {
		ll res = MAX;
		for (int nc = 1; nc < 20; ++nc) {
			if (nc != color && depth[cur] < depth[nxt]) {
				res = min(res, dfs2(nxt, nc) + nc);
			}
		}
		if (res != MAX) {
			dp[cur][color] += res;
		}
	}
	return dp[cur][color];
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	int a, b;
	memset(dp, -1, sizeof(dp));
	for (int i = 0; i < n - 1; ++i) {
		cin >> a >> b;
		tree[a].emplace_back(b);
		tree[b].emplace_back(a);
	}
	tree[0].emplace_back(1);
	dfs1(0, 0);
	cout << dfs2(0, 0) << '\n';
	return 0;
}