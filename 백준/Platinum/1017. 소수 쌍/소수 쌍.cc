#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#define SZ 1001

using namespace std;

int n, first;
bool prime[2001];

vector<int> g[SZ];
vector<int> fset, nfset;
int parent[SZ];
bool v[SZ];

void init() {
	fill(prime + 2, prime + 2001, true);
	for (int i = 2; i <= sqrt(2001); ++i) {
		if (!prime[i]) continue;
		for (int j = 2; i * j < 2001; ++j) {
			prime[i * j] = false;
		}
	}

	for (auto f : fset) {
		for (auto nf : nfset) {
			if (prime[f + nf]) g[f].emplace_back(nf);
		}
	}
}

bool dfs(int cur) {
	if (cur == first || v[cur]) return false;
	for (int nxt : g[cur]) {
		if (v[nxt]) continue;
		v[nxt] = true;
		if (parent[nxt] == -1 || dfs(parent[nxt])) {
			parent[nxt] = cur;
			return true;
		}
	}
	return false;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	cin >> first;
	fset.emplace_back(first);

	int num;
	for (int i = 1; i < n; ++i) {
		cin >> num;
		if (first % 2 == num % 2) {
			fset.emplace_back(num);
		}
		else {
			nfset.emplace_back(num);
		}
	}

	if (fset.size() != nfset.size()) {
		cout << -1 << '\n';
		return 0;
	}

	init();
	vector<int> res;
	for (auto node : g[first]) {
		fill(parent, parent + SZ, -1);
		parent[first] = node;
		parent[node] = first;
		int tmp_size = 1;
		for (int i = 1; i < fset.size(); ++i) {
			fill(v, v + SZ, false);
			if (dfs(fset[i])) ++tmp_size;
		}
		if (tmp_size == nfset.size()) {
			res.emplace_back(node);
		}
	}

	if (res.empty()) {
		cout << -1 << '\n';
	}
	else {
		sort(res.begin(), res.end());
		for (auto ele : res) {
			cout << ele << ' ';
		}
		cout << '\n';
	}
	return 0;
}
