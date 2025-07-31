#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

constexpr int MAX = 1'000'000'000;

int n, m;
int info[301][20], dp[301][20], mk[301][20];

int dfs(int cost, int comp) {
    if (comp == m) return 0;
    int tmp = 0;
    int* res = &dp[cost][comp];
    if (*res != -1) return *res;
    *res = 0;
    for (int i = 0; i <= n; i++) {
        if (cost + i <= n) {
            tmp = dfs(cost + i, comp + 1) + info[i][comp];
            if (*res < tmp) {
                *res = tmp;
                mk[cost][comp] = i;
            }
        }
    }
    return *res;
}

int main() {
    FASTIO;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        int p; cin >> p;
        for (int j = 0; j < m; j++) {
            cin >> info[i][j];
        }
    }
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j < m; j++) {
            dp[i][j] = -1;
        }
    }
    cout << dfs(0, 0) << '\n';
    int p = 0;
    for (int i = 0; i < m; i++) {
        cout << mk[p][i] << ' ';
        p += mk[p][i];
    }
    cout << '\n';
    return 0;
}
