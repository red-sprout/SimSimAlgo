#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int t, cost;
	cin >> t;
	for (int i = 0; i < t; ++i) {
		cin >> cost;
		cout << cost / 25 << ' ';
		cost %= 25;
		cout << cost / 10 << ' ';
		cost %= 10;
		cout << cost / 5 << ' ';
		cost %= 5;
		cout << cost << '\n';
	}
	return 0;
}