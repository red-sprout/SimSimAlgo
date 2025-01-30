#include <iostream>

using namespace std;

struct Matrix {
	long long a11, a12, a21, a22;
	
	Matrix operator*(Matrix a) {
		return {
			a11 * a.a11 + a12 * a.a21
			, a11* a.a12 + a12 * a.a22
			, a21* a.a11 + a22 * a.a21
			, a21* a.a12 + a22 * a.a22
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
Matrix b = { 1, 0, 0, 0 };

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	
	cin >> n;
	Matrix res = pow(a, n - 1) * b;
	cout << res.a11 << '\n';

	return 0;
}