#include <iostream>
#include <queue>
#include <cstring>

using namespace std;
typedef pair<int, int> pii;

int n, m;
pii start;
char map[600][600];
int dr[4] = { -1, 0, 1, 0 };
int dc[4] = { 0, -1, 0, 1 };

int solution() {
    int res = 0;
    bool v[600][600];
    memset(v, false, sizeof(v));

    queue<pii> q;
    v[start.first][start.second] = true;
    q.emplace(start);

    while (!q.empty()) {
        pii cur = q.front();
        q.pop();

        for (int i = 0; i < 4; ++i) {
            int nr = cur.first + dr[i];
            int nc = cur.second + dc[i];
            if (0 <= nr && nr < n && 0 <= nc && nc < m
                && !v[nr][nc] && map[nr][nc] != 'X') {
                if (map[nr][nc] == 'P') res++;
                v[nr][nc] = true;
                q.emplace(make_pair(nr, nc));
            }
        }
    }

    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> m;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> map[i][j];
            if (map[i][j] == 'I') {
                start.first = i;
                start.second = j;
            }
        }
    }

    int res = solution();
    if (res) {
        cout << res << '\n';
    }
    else {
        cout << "TT" << '\n';
    }

    return 0;
}
