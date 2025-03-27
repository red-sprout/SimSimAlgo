#include <iostream>
#include <vector>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int n, m;
	cin >> n >> m;
	vector<int> arr;
	arr.resize(n);

	int key = 0;
	for (int i = 0; i < n; ++i) {
		string str;
		cin >> str;
		int val = 0;
		for (int j = 0; j < str.length(); ++j) {
			val |= 1 << (str[j] - 'a');
			key |= 1 << (str[j] - 'a');
		}
		arr[i] = val;
	}

	int cmd, cnt;
	char c;
	for (int i = 0; i < m; ++i) {
		cin >> cmd >> c;
		if (cmd == 1) {
			key ^= 1 << (c - 'a');
		}
		else {
			key |= 1 << (c - 'a');
		}
		cnt = 0;
		for (int j = 0; j < n; ++j) {
			if ((arr[j] & key) == arr[j]) ++cnt;
		}
		cout << cnt << '\n';
	}

	return 0;
}