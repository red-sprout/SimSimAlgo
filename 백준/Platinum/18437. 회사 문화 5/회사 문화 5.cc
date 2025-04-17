#include <iostream>
#include <vector>

using namespace std;

int n, num = 0;
int ss[100'001];
int ee[100'001];
int tree[400'001];
int lazy[400'001];
vector<int> g[100'001];

void ett(int cur) {
	ss[cur] = ++num;
	for (auto nxt : g[cur]) {
		ett(nxt);
	}
	ee[cur] = num;
}

void init(int node, int s, int e) {
	if (s == e) {
		tree[node] = 1;
		return;
	}
	int m = (s + e) >> 1;
	init(node << 1, s, m);
	init(node << 1 | 1, m + 1, e);
	tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

void updateLazy(int node, int s, int e) {
	if (lazy[node] == 0) return;
	if (lazy[node] == 1) tree[node] = (e - s + 1);
	else tree[node] = 0;
	if (s != e) {
		lazy[node << 1] = lazy[node];
		lazy[node << 1 | 1] = lazy[node];
	}
	lazy[node] = 0;
}

void updateTree(int node, int s, int e, int ts, int te, int val) {
	updateLazy(node, s, e);
	if (te < s || e < ts) return;
	if (ts <= s && e <= te) {
		tree[node] = val == 1 ? (e - s + 1) : 0;
		if (s != e) {
			lazy[node << 1] = val;
			lazy[node << 1 | 1] = val;
		}
		return;
	}
	int m = (s + e) >> 1;
	updateTree(node << 1, s, m, ts, te, val);
	updateTree(node << 1 | 1, m + 1, e, ts, te, val);
	tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

void update(int cmd, int i) {
	int ts = ss[i] + 1;
	int te = ee[i];
	if (ts > te) return;
	if (cmd == 1) {
		updateTree(1, 1, n, ts, te, 1);
	}
	else {
		updateTree(1, 1, n, ts, te, -1);
	}
}

int get(int node, int s, int e, int ts, int te) {
	updateLazy(node, s, e);
	if (te < s || e < ts) return 0;
	if (ts <= s && e <= te) return tree[node];
	int m = (s + e) >> 1;
	return get(node << 1, s, m, ts, te) + get(node << 1 | 1, m + 1, e, ts, te);
}

int query(int i) {
	int ts = ss[i] + 1;
	int te = ee[i];
	if (ts > te) return 0;
	return get(1, 1, n, ts, te);
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	int p;
	for (int i = 1; i <= n; ++i) {
		cin >> p;
		if (p == 0) continue;
		g[p].emplace_back(i);
	}

	ett(1);
	init(1, 1, n);

	int m; cin >> m;
	int cmd, i;
	while (m--) {
		cin >> cmd >> i;
		if (cmd == 1) {
			update(cmd, i);
		}
		else if (cmd == 2) {
			update(cmd, i);
		}
		else {
			cout << query(i) << '\n';
		}
	}
	return 0;
}
