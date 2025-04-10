#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int n; cin >> n;
	vector<int> liquid(n);
	for (int i = 0; i < n; ++i) {
		cin >> liquid[i];
	}

	int l = 0, r = n - 1;
	int res = -2'00'000'001;
	int cur = 0;
	while (l < r) {
		int cur = liquid[l] + liquid[r];
		if (abs(res) > abs(cur)) {
			res = cur;
		}
		if (cur > 0) {
			--r;
		}
		else {
			++l;
		}
	}

	cout << res;
	return 0;
}