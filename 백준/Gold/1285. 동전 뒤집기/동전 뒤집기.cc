#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n;
bool mp[20][20];

int cnt() {
    int res = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (mp[i][j]) ++res;
        }
    }
    return res;
}

int flip_col() {
    int res = 0;
    for (int j = 0; j < n; j++) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += mp[i][j];
        }
        res += cnt > n / 2 ? n - cnt : cnt;
    }
    return res;
}

int dfs(int cur) {
    if (cur == n) return flip_col();
    int res = 400;
    res = min(res, dfs(cur + 1));
    for (int i = 0; i < n; i++) mp[cur][i] = !mp[cur][i];
    res = min(res, dfs(cur + 1));
    for (int i = 0; i < n; i++) mp[cur][i] = !mp[cur][i];
    return res;
}

int main() {
    FASTIO;
    cin >> n; char c;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> c;
            mp[i][j] = c == 'T';
        }
    }
    cout << dfs(0) << '\n';
    return 0;
}
