#include <iostream>

using namespace std;

int n;
int arr[2501];
int dp[2501];

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 1; i < n + 1; ++i) {
		cin >> arr[i];
	}
	
	int cnt = 0;
	fill(dp, dp + n + 1, 0);
	for (int i = 1; i < n + 1; ++i) {
		dp[i] = max(dp[i], dp[i - 1]);
		cnt = arr[i];
		for (int j = i + 1; j < n + 1; ++j) {
			cnt = arr[j] - cnt;
			if (cnt < 0) {
				break;
			}
			if (cnt == 0) {
				dp[j] = max(dp[j], dp[i - 1] + 1);
				break;
			}
		}
	}

	cout << n - dp[n] << '\n';
	return 0;
}