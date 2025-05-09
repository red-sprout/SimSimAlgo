#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int SINK = 0, DRAIN = 201, SZ = 100;

int n, m;
vector<int> g[DRAIN + 1];
int c[DRAIN + 1][DRAIN + 1];
int f[DRAIN + 1][DRAIN + 1];
int p[DRAIN + 1];

int edmondKarp() {
    int res = 0;
    while(1) {
        fill(p, p + DRAIN + 1, -1);
        queue<int> q;
        q.emplace(SINK);

        while(!q.empty()) {
            int cur = q.front(); q.pop();
            if(cur == DRAIN) break;
            for(auto nxt : g[cur]) {
                if(p[nxt] == -1 && c[cur][nxt] - f[cur][nxt] > 0) {
                    p[nxt] = cur;
                    q.emplace(nxt);
                }
            }
        }

        if(p[DRAIN] == -1) break;

        int flow = INT32_MAX;
        for(int node = DRAIN; node != SINK; node = p[node]) {
            flow = min(flow, c[p[node]][node] - f[p[node]][node]);
        }

        for(int node = DRAIN; node != SINK; node = p[node]) {
            f[p[node]][node] += flow;
            f[node][p[node]] -= flow;
        }

        res += flow;
    }
    
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> m;
    int a, b;
    for(int i = 1; i <= n; ++i) {
        g[SINK].emplace_back(i);
        g[i].emplace_back(SINK);
        ++c[SINK][i];
        g[i + SZ].emplace_back(DRAIN);
        g[DRAIN].emplace_back(i + SZ);
        ++c[i + SZ][DRAIN];
    }

    while(m--) {
        cin >> a >> b;
        g[a].emplace_back(b + SZ);
        g[b + SZ].emplace_back(a);
        ++c[a][b + SZ];
    }

    cout << edmondKarp() << '\n';
    return 0;
}