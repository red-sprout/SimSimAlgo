#include <iostream>

using namespace std;

int n;
bool dp[1001];

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n;
	dp[1] = true;
	dp[2] = false;
	dp[3] = true;
	dp[4] = true;
	for (int i = 5; i <= n; ++i) {
		dp[i] = !(dp[i - 1] && dp[i - 3] && dp[i - 4]);
	}

	string res = dp[n] ? "SK" : "CY";
	cout << res << '\n';
	return 0;
}