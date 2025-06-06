#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int DIFF = 400;
const int MAX = 802;
const int INF = 1e9;

vector<int> g[MAX];
int c[MAX][MAX], f[MAX][MAX], dist[MAX][MAX];

pair<int, int> mcmf(int s, int e) {
    pair<int, int> res;
    int p[MAX], d[MAX];
    bool inq[MAX];
    while(1) {
        queue<int> q;
        fill(p, p + MAX, -1);
        fill(d, d + MAX, INF);
        fill(inq, inq + MAX, false);
        q.emplace(s); d[s] = 0; inq[s] = true;
        
        while(!q.empty()) {
            int cur = q.front(); q.pop(); inq[cur] = false;
            for(auto nxt : g[cur]) {
                if(c[cur][nxt] - f[cur][nxt] > 0 && d[nxt] > d[cur] + dist[cur][nxt]) {
                    d[nxt] = d[cur] + dist[cur][nxt];
                    p[nxt] = cur;
                    if(!inq[nxt]) {
                        q.emplace(nxt);
                        inq[nxt] = true;
                    }
                }
            }
        }

        if(p[e] == -1) break;

        int flow = INF;
        for(int cur = e; cur != s; cur = p[cur]) {
            flow = min(flow, c[p[cur]][cur] - f[p[cur]][cur]);
        }

        for(int cur = e; cur != s; cur = p[cur]) {
            res.second += flow * dist[p[cur]][cur];
            f[p[cur]][cur] += flow;
            f[cur][p[cur]] -= flow;
        }

        ++res.first;
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    
    int n, m; cin >> n >> m;
    int s = 0, e = 801;
    for(int i = 1; i <= n; ++i) {
        g[s].emplace_back(i); g[i].emplace_back(s);
        c[s][i] = 1;
    }
    for(int i = DIFF + 1; i <= DIFF + m; ++i) {
        g[i].emplace_back(e); g[e].emplace_back(i);
        c[i][e] = 1;
    }

    for(int i = 1; i <= n; ++i) {
        int k; cin >> k;
        for(int j = 0; j < k; ++j) {
            int work, sal; cin >> work >> sal;
            work += DIFF;
            g[i].emplace_back(work); g[work].emplace_back(i);
            dist[i][work] = sal; dist[work][i] = -sal;
            c[i][work] = 1;
        }
    }

    pair<int, int> res = mcmf(s, e);
    cout << res.first << '\n' << res.second << '\n';
    return 0;
}
