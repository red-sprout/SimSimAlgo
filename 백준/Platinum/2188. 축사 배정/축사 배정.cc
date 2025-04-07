#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int n, m;
vector<int> g[402];
int p[402];
bool v[402];

bool dfs(int cur) {
	for (int nxt : g[cur]) {
		if (v[nxt]) continue;
		v[nxt] = true;
		if (p[nxt] == -1 || dfs(p[nxt])) {
			p[nxt] = cur;
			return true;
		}
	}
	return false;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;
	int s, num;
	for (int i = 1; i <= n; ++i) {
		cin >> s;
		for (int j = 0; j < s; ++j) {
			cin >> num;
			num += 200;
			g[i].emplace_back(num);
		}
	}
	int ans = 0;
	memset(p, -1, sizeof(p));
	for (int i = 1; i <= n; ++i) {
		memset(v, false, sizeof(v));
		if (dfs(i)) ++ans;
	}
	cout << ans;
	return 0;
}