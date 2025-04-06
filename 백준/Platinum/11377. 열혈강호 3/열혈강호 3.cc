#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#define SZ 2003

using namespace std;

int n, m, k;
int s = 0, mid = SZ - 2, e = SZ - 1;
vector<int> g[SZ];
int c[SZ][SZ];
int f[SZ][SZ];
int p[SZ];

int ek() {
	int res = 0;
	while (1) {
		memset(p, -1, sizeof(p));
		queue<int> q;
		q.emplace(s);
		while (!q.empty()) {
			int cur = q.front(); q.pop();
			if (cur == e) break;
			for (int nxt : g[cur]) {
				if (p[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
					q.emplace(nxt);
					p[nxt] = cur;
				}
			}
		}
		if (p[e] == -1) break;

		int cost = INT32_MAX;
		for (int cur = e; cur != s; cur = p[cur]) {
			cost = min(cost, c[p[cur]][cur] - f[p[cur]][cur]);
		}

		for (int cur = e; cur != s; cur = p[cur]) {
			f[p[cur]][cur] += cost;
			f[cur][p[cur]] -= cost;
		}

		res += cost;
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m >> k;
	int cnt, work;
	g[s].emplace_back(mid);
	g[mid].emplace_back(s);
	c[s][mid] = k;
	for (int i = 1; i <= n; ++i) {
		cin >> cnt;
		g[s].emplace_back(i);
		g[i].emplace_back(s);
		c[s][i] = 1;
		g[mid].emplace_back(i);
		g[i].emplace_back(mid);
		c[mid][i] = 1;
		for (int j = 0; j < cnt; ++j) {
			cin >> work;
			work += 1000;
			g[i].emplace_back(work);
			g[work].emplace_back(i);
			c[i][work] = 1;
		}
	}

	for (int i = 1001; i <= m + 1000; ++i) {
		g[i].emplace_back(e);
		g[e].emplace_back(i);
		c[i][e] = 1;
	}

	cout << ek() << '\n';
	return 0;
}