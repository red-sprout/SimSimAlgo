#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int n, m;
int in[1001];
vector<int> g[1001];

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n >> m;
	int cnt, a, b;
	for (int i = 0; i < m; ++i) {
		cin >> cnt;
		cin >> a;
		for (int j = 1; j < cnt; ++j) {
			cin >> b;
			g[a].emplace_back(b);
			++in[b];
			a = b;
		}
	}

	queue<int> q;
	bool v[1001];
	fill(v, v + n + 1, false);
	for (int i = 1; i <= n; ++i) {
		if (in[i] == 0) {
			q.emplace(i);
			v[i] = true;
		}
	}

	vector<int> res;
	while (!q.empty()) {
		int cur = q.front(); q.pop();
		res.emplace_back(cur);
		for (auto nxt : g[cur]) {
			if (!v[nxt] && --in[nxt] == 0) {
				q.emplace(nxt);
				v[nxt] = true;
			}
		}
	}

	if (res.size() != n) {
		cout << 0 << '\n';
		return 0;
	}

	for (auto node : res) {
		cout << node << '\n';
	}

	return 0;
}
