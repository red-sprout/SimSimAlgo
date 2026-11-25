#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

using namespace std;
using ll = long long;

bool v[100][100];
const int dx[6] = {0, 0, 1, -1, -1, 1};
const int dy[6] = {2, -2, 1, -1, 1, -1};
const int mat[6][2] = {
    {2, 4}, {3, 5}, {0, 5}, {1, 4}, {0, 3}, {1, 2}
};

ll dfs(int cnt, int x, int y, int d) {
    if (v[x][y]) return cnt == 0 ? 1 : 0;
    if (cnt == 0) return 0;
    ll res = 0;
    v[x][y] = true;
    for (int nd : mat[d]) {
        int nx = x + dx[nd];
        int ny = y + dy[nd];
        res += dfs(cnt - 1, nx, ny, nd);
    }
    v[x][y] = false;
    return res;
}

ll solution(int n) {
    v[50][50] = true;
    return dfs(n, 50 + dx[0], 50 + dy[0], 0);
}

int main() {
    FASTIO
    int n;
    cin >> n;
    cout << solution(n) << '\n'; 
    return 0;
}
