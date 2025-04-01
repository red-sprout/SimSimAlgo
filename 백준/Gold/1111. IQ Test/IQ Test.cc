#include <iostream>

using namespace std;

int n;
int arr[50];

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	int a, b;
	cin >> n;
	for (int i = 0; i < n; ++i) {
		cin >> arr[i];
	}

	if (n == 1) {
		cout << 'A' << '\n';
		return 0;
	}

	if (n == 2) {
		if (arr[1] == arr[0]) {
			cout << arr[1] << '\n';
		}
		else {
			cout << 'A' << '\n';
		}
		return 0;
	}

	
	a = 1, b = 0;
	if (arr[1] != arr[0]) {
		if ((arr[2] - arr[1]) % (arr[1] - arr[0]) != 0) {
			cout << 'B' << '\n';
			return 0;
		}
		a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
		b = arr[1] - arr[0] * a;
	}

	for (int i = 1; i < n - 1; ++i) {
		if (arr[i + 1] != arr[i] * a + b) {
			cout << 'B' << '\n';
			return 0;
		}
	}

	cout << arr[n - 1] * a + b << '\n';
	return 0;
}