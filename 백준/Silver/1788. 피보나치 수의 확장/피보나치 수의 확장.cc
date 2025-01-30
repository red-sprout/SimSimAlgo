#include <iostream>
#include <cmath>
#define MAX 1'000'000'000

using namespace std;

struct Matrix {
	long long a11, a12, a21, a22;
	
	Matrix operator*(Matrix a) {
		return {
			(a11 * a.a11 % MAX + a12 * a.a21 % MAX) % MAX
			, (a11 * a.a12 % MAX + a12 * a.a22 % MAX) % MAX
			, (a21 * a.a11 % MAX + a22 * a.a21 % MAX) % MAX
			, (a21 * a.a12 % MAX + a22 * a.a22 % MAX) % MAX
		};
	}
};

Matrix pow(Matrix a, int p) {
	if (p == 0) return { 1, 0, 0, 1 };
	if (p == 1) return a;
	Matrix half = pow(a, p / 2);
	if (p % 2) {
		return half * half * a;
	} else {
		return half * half;
	}
}

int n;
Matrix a = { 1, 1, 1, 0 };
Matrix a_ = { 0, 1, 1, -1 };
Matrix b = { 1, 0, 0, 0 };

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n;
	int sign;
	long long res;
	Matrix c;
	if (n > 0) {
		c = pow(a, n - 1) * b;
		sign = 1;
		res = c.a11;
	} else if (n < 0) {
		c = pow(a_, 1 - n) * b;
		res = c.a11;
		if (res > 0) {
			sign = 1;
		} else if (res < 0) {
			sign = -1;
			res = -res;
		} else {
			sign = 0;
		}
	} else {
		sign = 0;
		res = 0;
	}

	cout << sign << '\n' << res << '\n';
	return 0;
}