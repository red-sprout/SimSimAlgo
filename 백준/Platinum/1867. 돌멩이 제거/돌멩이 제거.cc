#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int s = 0, e = 1001;

int n, k;
vector<int> g[1010];
int c[1010][1010], f[1010][1010];

int edmond_karp() {
    int res = 0;
    int p[1010];
    while(1) {
        fill(p, p + 1010, -1);
        queue<int> q;
        q.emplace(s);

        while(!q.empty()) {
            int cur = q.front(); q.pop();
            if(cur == e) break;
            for(auto nxt : g[cur]) {
                if(p[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
                    p[nxt] = cur;
                    q.emplace(nxt);
                }
            }
        }

        if(p[e] == -1) break;

        for(int node = e; node != s; node = p[node]) {
            f[p[node]][node] += 1;
            f[node][p[node]] -= 1;
        }

        ++res;
    }

    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> k;
    int row, col;
    for(int i = 1; i <= n; ++i) {
        g[s].emplace_back(i);
        g[i].emplace_back(s);
        c[s][i] = 1;

        g[i + 500].emplace_back(e);
        g[e].emplace_back(i + 500);
        c[i + 500][e] = 1;
    }

    for(int i = 0; i < k; ++i) {
        cin >> row >> col;
        g[row].emplace_back(col + 500);
        g[col + 500].emplace_back(row);
        c[row][col + 500] = 1;
    }

    cout << edmond_karp() << '\n';
    return 0;
}
