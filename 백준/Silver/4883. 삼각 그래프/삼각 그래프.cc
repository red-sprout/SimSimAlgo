#include <iostream>
#include <cmath>
#define MAX 100'000

using namespace std;

int n;
int g[MAX][3];
int dp[MAX][3];

int solution() {
	for (int i = 0; i < MAX; ++i) {
		fill(dp[i], dp[i] + 3, -1);
	}

	dp[0][0] = 1'000'000'001;
	dp[0][1] = g[0][1];
	dp[0][2] = g[0][1] + g[0][2];

	for (int i = 1; i < n; ++i) {
		dp[i][0] = g[i][0] + min(dp[i - 1][0], dp[i - 1][1]);
		dp[i][1] = g[i][1] + min(min(min(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]), dp[i][0]);
		dp[i][2] = g[i][2] + min(min(dp[i - 1][1], dp[i - 1][2]), dp[i][1]);
	}

	return dp[n - 1][1];
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	int order = 0;
	while (1) {
		cin >> n;
		if (!n) break;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < 3; ++j) {
				cin >> g[i][j];
			}
		}
		cout << ++order << ". " << solution() << '\n';
	}

	return 0;
}