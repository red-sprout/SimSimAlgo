#include <iostream>

using namespace std;
typedef long long ll;

int n;
ll arr[100'000];
ll minTree[400'000];
ll sumTree[400'000];

void init(int node, int s, int e) {
	if (s == e) {
		minTree[node] = s;
		sumTree[node] = arr[s];
		return;
	}

	int m = (s + e) >> 1;
	init(node << 1, s, m);
	init(node << 1 | 1, m + 1, e);

	minTree[node] = arr[minTree[node << 1]] < arr[minTree[node << 1 | 1]] ? minTree[node << 1] : minTree[node << 1 | 1];
	sumTree[node] = sumTree[node << 1] + sumTree[node << 1 | 1];
}

int getMin(int node, int s, int e, int ts, int te) {
	if (te < s || e < ts) return -1;
	if (ts <= s && e <= te) return minTree[node];

	int m = (s + e) >> 1;
	int l = getMin(node << 1, s, m, ts, te);
	int r = getMin(node << 1 | 1, m + 1, e, ts, te);

	if (l == -1) return r;
	if (r == -1) return l;
	return arr[l] < arr[r] ? l : r;
}

ll getSum(int node, int s, int e, int ts, int te) {
	if (te < s || e < ts) return 0;
	if (ts <= s && e <= te) return sumTree[node];

	int m = (s + e) >> 1;

	return getSum(node << 1, s, m, ts, te) + getSum(node << 1 | 1, m + 1, e, ts, te);
}

ll solution(int s, int e) {
	int idx = getMin(1, 0, n - 1, s, e);
	ll sum = getSum(1, 0, n - 1, s, e);
	ll area = arr[idx] * sum;

	if (s < idx) {
		area = max(area, solution(s, idx - 1));
	}

	if (idx < e) {
		area = max(area, solution(idx + 1, e));
	}

	return area;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 0; i < n; ++i) {
		cin >> arr[i];
	}

	init(1, 0, n - 1);
	cout << solution(0, n - 1) << '\n';
	return 0;
}