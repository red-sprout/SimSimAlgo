#include <iostream>
#include <cstring>

using namespace std;

int n;
string first, last;
int dp[10001][10];
int path[10001][10];

int dfs(int cur, int left) {
    if(cur > n) return 0;
    int* res = &dp[cur][left];
    if(*res != -1) return *res;
    *res = 1e9;
    int x = (last[cur - 1] - '0') - ((first[cur - 1] - '0' + left) % 10);
    int l = (x + 10) % 10;
    int r = (10 - l) % 10;
    int val;

    val = dfs(cur + 1, (left + l) % 10) + l;
    if(*res > val) {
        *res = val;
        path[cur][left] = l;
    }

    val = dfs(cur + 1, left) + r;
    if(*res > val) {
        *res = val;
        path[cur][left] = -r;
    }

    return *res;
}

void track() {
    int left = 0, val;
    for(int cur = 1; cur <= n; ++cur) {
        val = path[cur][left];
        cout << cur << ' ' << val << '\n';
        if(val > 0) left = (left + val) % 10;
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    cin.ignore();
    getline(cin, first);
    getline(cin, last);
    memset(dp, -1, sizeof(dp));
    cout << dfs(1, 0) << '\n';
    track();
    return 0;
}
