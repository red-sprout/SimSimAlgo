#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	string str; cin >> str;
	int res[26];
	fill(res, res + 26, 0);
	for (int i = 0; i < str.length(); ++i) {
		if ('A' <= str[i] && str[i] <= 'Z') {
			++res[str[i] - 'A'];
		}
		else {
			++res[str[i] - 'a'];
		}
	}

	int cnt = 1;
	char c = 'A';
	for (int i = 1; i < 26; ++i) {
		if (res[c - 'A'] < res[i]) {
			cnt = 1;
			c = i + 'A';
		}
		else if (res[c - 'A'] == res[i]) {
			++cnt;
		}
	}

	cout << (cnt == 1 ? c : '?') << '\n';
	return 0;
}