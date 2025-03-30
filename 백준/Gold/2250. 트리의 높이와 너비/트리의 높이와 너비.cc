#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

struct node {
	int p, l, r;
};

int n, m, root;
int sz[10001];
int depth[10001];
int width[10001];

vector<node> nodes;

int dfs1(int cur, int d) {
	depth[cur] = d;
	m = max(m, d);
	sz[cur] = 1;
	if (nodes[cur].l != -1) {
		sz[cur] += dfs1(nodes[cur].l, d + 1);
	}
	if (nodes[cur].r != -1) {
		sz[cur] += dfs1(nodes[cur].r, d + 1);
	}
	return sz[cur];
}

void dfs2(int cur, int s) {
	width[cur] = s + 1;
	if (nodes[cur].l != -1) {
		width[cur] += sz[nodes[cur].l];
		dfs2(nodes[cur].l, s);
	}
	if (nodes[cur].r != -1) {
		dfs2(nodes[cur].r, width[cur]);
	}
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	nodes.resize(n + 1);
	fill(sz, sz + n, 0);
	fill(depth, depth + n, 0);
	fill(width, width + n, 0);
	for (int i = 1; i <= n; ++i) {
		nodes[i] = { -1, -1, -1 };
	}

	int cur, l, r;
	for (int i = 0; i < n; ++i) {
		cin >> cur >> l >> r;
		nodes[cur].l = l;
		nodes[cur].r = r;
		if (l != -1) {
			nodes[l].p = cur;
		}
		if (r != -1) {
			nodes[r].p = cur;
		}
	}

	root = 1;
	for (int i = 1; i <= n; ++i) {
		if (nodes[i].p == -1) {
			root = i;
			break;
		}
	}

	dfs1(root, 1);
	dfs2(root, 1);
	int idx = 1, w = 1;
	for (int i = 2; i <= m; ++i) {
		l = INT16_MAX;
		r = 0;
		for (int j = 1; j <= n; ++j) {
			if (depth[j] == i) {
				l = min(l, width[j]);
				r = max(r, width[j]);
			}
		}
		if (w < r - l + 1) {
			idx = i;
			w = r - l + 1;
		}
	}

	cout << idx << ' ' << w << '\n';
	return 0;
}