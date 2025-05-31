#include <iostream>
#include <vector>
#include <cstring>
using namespace std;

int n, m, sum;
char map[80][80];
vector<int> g[6400];
int p[6400];
bool v[6400];

int get_pos(int row, int col) {
    return row * 80 + col;
}

void init() {
    cin >> n >> m;
    for(int i = 0; i < 6400; ++i) g[i].clear();
    memset(p, -1, sizeof(p));
    sum = 0;

    for(int i = 0; i < n; ++i) {
        cin >> map[i];
    }

    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < m; ++j) {
            if(map[i][j] == '.') ++sum;
        }
    }

    int dx[] = {-1,  0, 1, -1, 0, 1};
    int dy[] = {-1, -1, -1,  1, 1, 1};

    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < m; j += 2) {
            if(map[i][j] != '.') continue;
            int u = get_pos(i, j);

            for(int d = 0; d < 6; ++d) {
                int ni = i + dx[d];
                int nj = j + dy[d];
                if(ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
                if(map[ni][nj] != '.') continue;
                int v = get_pos(ni, nj);
                g[u].push_back(v);
            }
        }
    }
}

bool dfs(int cur) {
    for(int nxt : g[cur]) {
        if(v[nxt]) continue;
        v[nxt] = true;
        if(p[nxt] == -1 || dfs(p[nxt])) {
            p[nxt] = cur;
            return true;
        }
    }
    return false;
}

int solution() {
    int res = 0;
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < m; j += 2) {
            if(map[i][j] != '.') continue;
            int node = get_pos(i, j);
            memset(v, 0, sizeof(v));
            if(dfs(node)) ++res;
        }
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int test; cin >> test;
    while(test--) {
        init();
        cout << sum - solution() << '\n';
    }
    return 0;
}
