#include <iostream>
#include <cstring>
#include <string>

using namespace std;

string dna;
int dp[500][500];

bool check(int l, int r) {
	return (dna[l] == 'a' && dna[r] == 't') || (dna[l] == 'g' && dna[r] == 'c');
}

int dfs(int l, int r) {
	if (l >= r) return 0;
	if (l + 1 == r) return check(l, r) ? 2 : 0;
	int* res = &dp[l][r];
	if (*res != -1) return *res;

	*res = 0;
	if (check(l, r)) {
		int nxt = dfs(l + 1, r - 1);
		*res = max(*res, nxt + 2);
	}

	for (int i = l; i < r; ++i) {
		int ll = dfs(l, i);
		int rr = dfs(i + 1, r);
		*res = max(*res, ll + rr);
	}

	return *res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	getline(cin, dna);
	memset(dp, -1, sizeof(dp));
	cout << dfs(0, dna.length() - 1) << '\n';
	return 0;
}