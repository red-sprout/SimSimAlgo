#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef long long ll;

int n, m;
vector<int> a, b;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n >> m;
	a.resize(n);
	b.resize(m);

	for (int i = 0; i < n; ++i) {
		cin >> a[i];
	}

	for (int i = 0; i < m; ++i) {
		cin >> b[i];
	}

	ll res[3];
	fill(res, res + 3, 0);
	sort(b.begin(), b.end());
	int l, u;
	for (int i = 0; i < n; i++) {
		l = lower_bound(b.begin(), b.end(), a[i]) - b.begin();
		u = upper_bound(b.begin(), b.end(), a[i]) - b.begin();
		res[0] += l;
		res[1] += m - u;
		res[2] += u - l;
	}

	cout << res[0] << ' ' << res[1] << ' ' << res[2] << '\n';
	return 0;
}