#include <iostream>
#include <vector>

using namespace std;

int r, c, k;
int cnt = 0;
bool v[5][5];
char map[5][5];

int dr[] = { -1, 0, 1, 0 };
int dc[] = { 0, -1, 0, 1 };

void dfs(int rr, int cc, int kk) {
    if (rr == 0 && cc == c - 1) {
        if(kk == k) ++cnt; return;
    }
    v[rr][cc] = true;
    for (int i = 0; i < 4; ++i) {
        int nr = rr + dr[i];
        int nc = cc + dc[i];
        if (0 <= nr && nr < r && 0 <= nc && nc < c && !v[nr][nc] && map[nr][nc] != 'T') {
            dfs(nr, nc, kk + 1);
        }
    }
    v[rr][cc] = false;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> r >> c >> k;
    for (int i = 0; i < r; ++i) {
        for (int j = 0; j < c; ++j) {
            cin >> map[i][j];
        }
    }
    dfs(r - 1, 0, 1);
    cout << cnt << '\n';
    return 0;
}
