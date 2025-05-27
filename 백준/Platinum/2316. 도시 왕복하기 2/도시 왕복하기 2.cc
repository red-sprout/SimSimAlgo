#include <iostream>
#include <vector>
#include <queue>
#define INF 1'000'000'000

using namespace std;

int n;
int c[1000][1000], f[1000][1000];
vector<int> g[1000];

int edmond_karp() {
    int res = 0;
    int p[1000];

    while(1) {
        fill(p, p + 1000, -1);
        queue<int> q;
        q.emplace(1);
        while(!q.empty()) {
            int cur = q.front(); q.pop();
            if(cur == 2) break;
            for(auto nxt : g[cur]) {
                if(p[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
                    p[nxt] = cur;
                    q.emplace(nxt);
                }
            }
        }

        if(p[2] == -1) break;

        int node;
        int flow = INF;
        for(node = 2; node != 1; node = p[node]) {
            flow = min(flow, c[p[node]][node] - f[p[node]][node]);
        }

        for(node = 2; node != 1; node = p[node]) {
            f[p[node]][node] += flow;
            f[node][p[node]] -= flow;
        }

        res += flow;
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int m; cin >> n >> m;
    for(int i = 3; i <= n; ++i) {
        g[i].emplace_back(i + 400);
        g[i + 400].emplace_back(i);
        c[i][i + 400] = 1;
    }

    int u, v;
    for(int i = 0; i < m; ++i) {
        cin >> u >> v;
        int out_u = u == 1 || u == 2 ? u : u + 400;
        int out_v = v == 1 || v == 2 ? v : v + 400;
        g[out_u].emplace_back(v);
        g[v].emplace_back(out_u);
        g[out_v].emplace_back(u);
        g[u].emplace_back(out_v);
        c[out_u][v] = 1;
        c[out_v][u] = 1;
    }

    cout << edmond_karp() << '\n';

    return 0;
}