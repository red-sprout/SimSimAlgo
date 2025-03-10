#include <iostream>
#include <cstring>
#include <vector>

#define MAX 1'000'000'000

using namespace std;

int n, m;
int dp[10][1 << 10];
int student[10];

int dfs(int cur, int pv) {
    if(pv == (1 << n) - 1) return 0;
    if(cur == m) return MAX;
    if(dp[cur][pv] != -1) return dp[cur][pv];
    int res = MAX;
    res = min(res, dfs(cur + 1, pv | student[cur]) + 1);
    res = min(res, dfs(cur + 1, pv));
    return dp[cur][pv] = res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    memset(dp, -1, sizeof(dp));
    memset(student, 0, sizeof(student));
    cin >> n >> m;
    int o, p, i, j;
    for(i = 0; i < m; ++i) {
        cin >> o;
        for(j = 0; j < o; ++j) {
            cin >> p;
            student[i] |= 1 << (p - 1);
        }
    }
    int res = dfs(0, 0);
    cout << ((res == MAX) ? -1 : res) << '\n';
    return 0;
}