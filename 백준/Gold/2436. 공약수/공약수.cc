#include <iostream>
#include <cmath>

using namespace std;

int gcd(int i, int j) {
	int m = j % i;
	while (m != 0) {
		j = i;
		i = m;
		m = j % i;
	}
	return i;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int g, l;
	cin >> g >> l;
	int k = l / g;
	int plus = INT32_MAX;
	int a = g, b = l;
	for (int i = 1; i <= k; ++i) {
		int j = k / i;
		if (i * j != k) continue;
		if (i > j) break;
		if (gcd(i, j) != 1) continue;
		if (plus > g * i + g * j) {
			a = g * i;
			b = g * j;
			plus = a + b;
		}
	}

	cout << a << ' ' << b << '\n';
	return 0;
}