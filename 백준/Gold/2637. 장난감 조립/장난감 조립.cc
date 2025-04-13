#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;
typedef pair<int, int> pii;

int n, m;
vector<pii> g[101];
int in[101];
int dp[101];

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;
	int x, y, k;
	while (m-- > 0) {
		cin >> x >> y >> k;
		g[x].emplace_back(make_pair(y, k));
		in[y]++;
	}

	vector<int> res;
	queue<int> q;
	q.emplace(n);
	dp[n] = 1;
	while (!q.empty()) {
		int cur = q.front(); q.pop();
		if (g[cur].empty()) {
			res.emplace_back(cur);
		}
		for (pii edge : g[cur]) {
			int nxt = edge.first;
			int c = edge.second;
			dp[nxt] += dp[cur] * c;
			if (--in[nxt] == 0) q.emplace(nxt);
		}
	}

	sort(res.begin(), res.end());
	for (int v : res) {
		cout << v << ' ' << dp[v] << '\n';
	}

	return 0;
}
