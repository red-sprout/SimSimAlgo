#include <iostream>

using namespace std;

const int dr[] = {-1, 0, 1, 0, 0};
const int dc[] = {0, -1, 0, 1, 0};

int r, c, t;
char mp[100][100];

int dfs(int rr, int cc, int tt) {
    if(tt == t) return 0;
    int res = 0;
    for(int i = 0; i < 5; ++i) {
        int nr = rr + dr[i];
        int nc = cc + dc[i];
        if(0 <= nr && nr < r && 0 <= nc && nc < c && mp[nr][nc] != '#') {
            if(mp[nr][nc] == 'S') {
                mp[nr][nc] = '.';
                res = max(res, dfs(nr, nc, tt + 1) + 1);
                mp[nr][nc] = 'S';
            } else {
                res = max(res, dfs(nr, nc, tt + 1));
            }
        }
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> r >> c >> t;
    int sr = -1, sc = -1;
    for(int i = 0; i < r; ++i) {
        for(int j = 0; j < c; ++j) {
            cin >> mp[i][j];
            if(mp[i][j] == 'G') {
                sr = i;
                sc = j;
            }
        }
    }
    cout << dfs(sr, sc, 0) << '\n';
    return 0;
}