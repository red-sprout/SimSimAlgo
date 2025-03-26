#include <iostream>
#include <cstring>
#include <vector>
#define SIZE 16
#define MAX 40001

using namespace std;
typedef long long ll;
typedef pair<int, ll> pil;

int n, sz;
ll dist[MAX];
int depth[MAX];
int dp[SIZE][MAX];
vector<pil> tree[MAX];

void dfs(int cur, int d) {
	depth[cur] = d;
	for (pil nxt : tree[cur]) {
		if (depth[nxt.first] == 0) {
			dist[nxt.first] = dist[cur] + nxt.second;
			dp[0][nxt.first] = cur;
			dfs(nxt.first, d + 1);
		}
	}
}

int lca(int a, int b) {
	int n1, n2, d;
	if (depth[a] > depth[b]) {
		n1 = a;
		n2 = b;
		d = depth[a] - depth[b];
	}
	else {
		n1 = b;
		n2 = a;
		d = depth[b] - depth[a];
	}

	for (int i = SIZE - 1; i >= 0; --i) {
		if ((d & (1 << i)) != 0) {
			n1 = dp[i][n1];
		}
	}

	if (n1 == n2) return n1;

	for (int i = SIZE - 1; i >= 0; --i) {
		if (dp[i][n1] != dp[i][n2]) {
			n1 = dp[i][n1];
			n2 = dp[i][n2];
		}
	}

	return dp[0][n1];
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	memset(dist, 0, sizeof(dist));
	memset(depth, 0, sizeof(depth));
	memset(dp, 0, sizeof(dp));
	int a, b, c;
	for (int i = 0; i < n - 1; ++i) {
		cin >> a >> b >> c;
		tree[a].emplace_back(make_pair(b, c));
		tree[b].emplace_back(make_pair(a, c));
	}

	dfs(1, 1);
	for (int i = 1; i < SIZE; ++i) {
		for (int j = 1; j < n + 1; ++j) {
			dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}
	}

	int m;
	cin >> m;
	for (int i = 0; i < m; ++i) {
		cin >> a >> b;
		c = lca(a, b);
		cout << dist[a] + dist[b] - 2 * dist[c] << '\n';
	}
	return 0;
}