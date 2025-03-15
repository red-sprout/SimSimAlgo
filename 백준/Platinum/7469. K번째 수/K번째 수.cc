#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef vector<int> vi;

const int sz = 100'001;
int arr[sz];
vi tree[4 * sz];

vi merge_node(vi node1, vi node2) {
	vi res;
	int i1 = 0, i2 = 0, idx = 0, sz1 = node1.size(), sz2 = node2.size();
	res.resize(sz1 + sz2);

	while (i1 < sz1 && i2 < sz2) {
		if (node1[i1] < node2[i2]) res[idx++] = node1[i1++];
		else res[idx++] = node2[i2++];
	}

	while (i1 < sz1) res[idx++] = node1[i1++];
	while (i2 < sz2) res[idx++] = node2[i2++];

	return res;
}

void init(int node, int s, int e) {
	if (s == e) {
		tree[node] = { arr[s] };
		return;
	}

	int m = (s + e) >> 1;
	init(node << 1, s, m);
	init(node << 1 | 1, m + 1, e);

	tree[node] = merge_node(tree[node << 1], tree[node << 1 | 1]);
}

int get(int node, int s, int e, int ts, int te, int val) {
	if (te < s || e < ts) return 0;
	if (ts <= s && e <= te) return upper_bound(tree[node].begin(), tree[node].end(), val) - tree[node].begin();
	int m = (s + e) >> 1;
	return get(node << 1, s, m, ts, te, val) + get(node << 1 | 1, m + 1, e, ts, te, val);
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}

	init(1, 0, n - 1);
	int a, b, c;
	while (m-- > 0) {
		cin >> a >> b >> c;
		int l = -1e9 - 1, r = 1e9 + 1, m;
		while (l + 1 < r) {
			m = (l + r) >> 1;
			if (get(1, 0, n - 1, a - 1, b - 1, m) < c) l = m;
			else r = m;
		}
		cout << r << '\n';
	}

	return 0;
}