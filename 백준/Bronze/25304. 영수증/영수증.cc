#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int x, n, a, b;
	cin >> x >> n;
	for (int i = 0; i < n; ++i) {
		cin >> a >> b;
		x -= a * b;
	}
	cout << (x == 0 ? "Yes" : "No") << '\n';
	return 0;
}