#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int n; cin >> n;
	vector<int> arr(n);
	for (int i = 0; i < n; ++i) {
		cin >> arr[i];
	}
	sort(arr.begin(), arr.end());
	cout << arr[(n - 1) / 2] << '\n';
	return 0;
}
