#include <iostream>
#include <vector>
#include <queue>

#define SINK 0
#define KNODE 2001
#define NNODE 2002
#define DRAIN 2003
#define SZ 2004

using namespace std;

int n, m, k;
int c[SZ][SZ];
int f[SZ][SZ];
int p[SZ];
vector<int> g[SZ];

int ek() {
	int res = 0;

	while (1) {
		fill(p, p + SZ, -1);
		queue<int> q;
		q.emplace(SINK);

		while (!q.empty()) {
			int cur = q.front(); q.pop();
			if (cur == DRAIN) break;
			for (auto nxt : g[cur]) {
				if (p[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
					q.emplace(nxt);
					p[nxt] = cur;
				}
			}
		}

		if (p[DRAIN] == -1) break;

		int flow = INT32_MAX;
		for (int i = DRAIN; i != SINK; i = p[i]) {
			flow = min(flow, c[p[i]][i] - f[p[i]][i]);
		}

		for (int i = DRAIN; i != SINK; i = p[i]) {
			f[p[i]][i] += flow;
			f[i][p[i]] -= flow;
		}

		res += flow;
	}

	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> k;
	int sz, work;

	g[SINK].emplace_back(KNODE);
	g[KNODE].emplace_back(SINK);
	c[SINK][KNODE] = k;

	for (int i = 1; i <= n; ++i) {
		g[KNODE].emplace_back(i);
		g[i].emplace_back(KNODE);
		c[KNODE][i] = k;

		g[SINK].emplace_back(i);
		g[i].emplace_back(SINK);
		c[SINK][i] = 1;
	}

	for (int i = 1; i <= n; ++i) {
		cin >> sz;
		for (int j = 0; j < sz; ++j) {
			cin >> work;
			work += 1000;
			g[i].emplace_back(work);
			g[work].emplace_back(i);
			c[i][work] = 1;
		}
	}

	for (int i = 1001; i <= m + 1000; ++i) {
		g[i].emplace_back(DRAIN);
		g[DRAIN].emplace_back(i);
		c[i][DRAIN] = 1;
	}

	cout << ek() << '\n';
	return 0;
}