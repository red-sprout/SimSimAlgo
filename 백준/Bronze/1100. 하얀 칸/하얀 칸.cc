#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int res = 0;
	char c;
	for (int i = 0; i < 8; ++i) {
		for (int j = 0; j < 8; ++j) {
			cin >> c;
			if ((i + j) % 2 == 0 && c == 'F') {
				++res;
			}
		}
	}
	cout << res << '\n';
	return 0;
}