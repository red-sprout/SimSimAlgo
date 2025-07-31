#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

constexpr int MAX = 1'000'000'000;
constexpr int dr[] = {0, -1, 0, 1, 0};
constexpr int dc[] = {0, 0, -1, 0, 1};

void turn_light(char mp[10][10], int row, int col) {
    for (int i = 0; i < 5; ++i) {
        int r = row + dr[i];
        int c = col + dc[i];
        if (0 <= r && r < 10 && 0 <= c && c < 10) mp[r][c] = mp[r][c] == 'O' ? '#' : 'O';
    }
}

int simul(char mp[10][10]) {
    int cnt = 0;
    char arr[10][10];
    for (int i = 0; i < 10; ++i) for (int j = 0; j < 10; ++j) arr[i][j] = mp[i][j];
    for (int i = 1; i < 10; ++i) {
        for (int j = 0; j < 10; ++j) {
            if (arr[i - 1][j] == '#') continue;
            turn_light(arr, i, j);
            ++cnt;
        }
    }
    for (int j = 0; j < 10; ++j) {
        if (arr[9][j] != '#') return MAX;
    }
    return cnt;
}

int dfs(int cur, char mp[10][10]) {
    if (cur == 10) return simul(mp);
    int res = MAX;
    res = min(res, dfs(cur + 1, mp));
    turn_light(mp, 0, cur);
    res = min(res, dfs(cur + 1, mp) + 1);
    turn_light(mp, 0, cur);
    return res;
}

int main() {
    FASTIO;
    char mp[10][10];
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            cin >> mp[i][j];
        }
    }
    cout << dfs(0, mp) << '\n';
    return 0;
}
