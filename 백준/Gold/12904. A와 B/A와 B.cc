#include <iostream>
#include <queue>

using namespace std;

string s, t;
deque<char> dq;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> s >> t;

	for (int i = 0; i < t.length(); ++i) {
		dq.emplace_back(t[i]);
	}

	char c;
	bool is_back = true;
	for (int i = t.length(); i > s.length(); --i) {
		if (is_back) {
			c = dq.back();
			dq.pop_back();
		}
		else {
			c = dq.front();
			dq.pop_front();
		}

		if (c == 'B') {
			is_back = !is_back;
		}
	}

	string res = "";
	while (!dq.empty()) {
		if (is_back) {
			res += dq.front();
			dq.pop_front();
		}
		else {
			res += dq.back();
			dq.pop_back();
		}
	}

	cout << (s.compare(res) == 0) << '\n';
	return 0;
}
