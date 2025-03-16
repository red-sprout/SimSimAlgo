#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

const int MAX = 100'001;

int n;
int num = 0;
int depth[MAX], s[MAX], e[MAX];
ll lazy[4 * MAX], tree[4 * MAX];
vector<int> g[MAX];

void dfs(int cur, int d) {
	depth[cur] = d;
	s[cur] = ++num;
	for (int nxt : g[cur]) {
		if (depth[nxt] == 0) {
			dfs(nxt, d + 1);
		}
	}
	e[cur] = num;
}

void updateLazy(int node, int s, int e) {
	if (lazy[node] == 0) return;
	tree[node] += (e - s + 1) * lazy[node];
	if (s != e) {
		lazy[node << 1] += lazy[node];
		lazy[node << 1 | 1] += lazy[node];
	}
	lazy[node] = 0;
}

void updateTree(int node, int s, int e, int ts, int te, int val) {
	updateLazy(node, s, e);
	if (te < s || e < ts) return;
	if (ts <= s && e <= te) {
		tree[node] += val;
		if (s != e) {
			lazy[node << 1] += val;
			lazy[node << 1 | 1] += val;
		}
		return;
	}

	int m = (s + e) >> 1;
	updateTree(node << 1, s, m, ts, te, val);
	updateTree(node << 1 | 1, m + 1, e, ts, te, val);
	tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

ll get(int node, int s, int e, int ts, int te) {
	updateLazy(node, s, e);
	if (te < s || e < ts) return 0;
	if (ts <= s && e <= te) return tree[node];

	int m = (s + e) >> 1;
	return get(node << 1, s, m, ts, te) + get(node << 1 | 1, m + 1, e, ts, te);
}

void update(int i, int w) {
	updateTree(1, 1, n, s[i], e[i], w);
}

ll balance(int i) {
	return get(1, 1, n, s[i], s[i]);
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int m, p;
	cin >> n >> m;
	for (int i = 1; i <= n; ++i) {
		cin >> p;
		if (p == -1) continue;
		g[p].emplace_back(i);
	}

	dfs(1, 1);
	fill(lazy, lazy + (4 * n + 1), 0);
	fill(tree, tree + (4 * n + 1), 0);

	int q, i, w;
	while (m--) {
		cin >> q;
		if (q == 1) {
			cin >> i >> w;
			update(i, w);
		}
		else {
			cin >> i;
			cout << balance(i) << '\n';
		}
	}

	return 0;
}