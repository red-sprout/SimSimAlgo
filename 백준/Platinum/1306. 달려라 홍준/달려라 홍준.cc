#include <iostream>

using namespace std;

const int MAX = 1'000'000;

int n, m;
int arr[MAX];
int tree[4 * MAX];

void update(int node, int s, int e, int idx, int val) {
	if (idx < s || e < idx) return;
	if (s == e) {
		tree[node] = val;
		return;
	}

	int m = (s + e) >> 1;
	update(node << 1, s, m, idx, val);
	update(node << 1 | 1, m + 1, e, idx, val);
	tree[node] = max(tree[node << 1], tree[node << 1 | 1]);
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> m;
	for (int i = 0; i < n; ++i) {
		cin >> arr[i];
	}

	for (int i = 0; i < 2 * m - 1; ++i) {
		update(1, 0, n - 1, i, arr[i]);
	}

	for (int i = 2 * m - 1; i <= n; ++i) {
		cout << tree[1] << ' ';
		update(1, 0, n - 1, i - 2 * m + 1, 0);
		if(i < n) update(1, 0, n - 1, i, arr[i]);
	}

	cout << '\n';
	return 0;
}