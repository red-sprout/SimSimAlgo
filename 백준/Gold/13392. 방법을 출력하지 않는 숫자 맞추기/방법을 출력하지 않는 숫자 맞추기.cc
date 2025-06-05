#include <iostream>
#include <cstring>

using namespace std;

int n;
string first, last;
int dp[10001][10];

int dfs(int cur, int left) {
    if(cur > n) return 0;
    int* res = &dp[cur][left];
    if(*res != -1) return *res;
    *res = 1e9;
    int x = (last[cur - 1] - '0') - ((first[cur - 1] - '0' + left) % 10);
    int l = x >= 0 ? x : 10 + x;
    int r = x <= 0 ? -x : 10 - x;
    *res = min(*res, dfs(cur + 1, (left + l) % 10) + l);
    *res = min(*res, dfs(cur + 1, left) + r);
    return *res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    cin.ignore();
    getline(cin, first);
    getline(cin, last);
    memset(dp, -1, sizeof(dp));
    cout << dfs(1, 0) << '\n';
    return 0;
}
