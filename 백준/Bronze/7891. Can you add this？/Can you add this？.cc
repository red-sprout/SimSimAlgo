#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int t, x, y;
	cin >> t;
	while (t-- > 0) {
		cin >> x >> y;
		cout << x + y << '\n';
	}
	return 0;
}