#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int DIFF = 100;
const int MAX = 402;
const int INF = 1e9;

vector<int> g[MAX];
int c[MAX][MAX], f[MAX][MAX], cost[MAX][MAX];

int mcmf(int s, int e) {
    int dist[MAX], p[MAX];
    bool inq[MAX];
    int res = 0;
    while(1) {
        queue<int> q;
        fill(dist, dist + MAX, INF);
        fill(p, p + MAX, -1);
        fill(inq, inq + MAX, false);
        q.emplace(s);
        dist[s] = 0; inq[s] = true;

        while(!q.empty()) {
            int cur = q.front(); q.pop(); inq[cur] = false;
            for(auto nxt : g[cur]) {
                if(c[cur][nxt] - f[cur][nxt] > 0 && dist[nxt] > dist[cur] + cost[cur][nxt]) {
                    dist[nxt] = dist[cur] + cost[cur][nxt];
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
            res += flow * cost[p[cur]][cur];
            f[p[cur]][cur] += flow;
            f[cur][p[cur]] -= flow;
        }
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    
    int n, m; cin >> n >> m;
    int s = 0, e = 401;

    for(int i = 1; i <= n; ++i) {
        int ain = i, aout = i + DIFF;
        g[aout].emplace_back(e);
        g[e].emplace_back(aout);
        c[aout][e] = INF;

        g[ain].emplace_back(aout);
        g[aout].emplace_back(ain);
        cin >> c[ain][aout];
    }

    for(int i = 1; i <= m; ++i) {
        int bin = i + 2 * DIFF, bout = i + 3 * DIFF;
        g[s].emplace_back(bin);
        g[bin].emplace_back(s);
        c[s][bin] = INF;

        g[bin].emplace_back(bout);
        g[bout].emplace_back(bin);
        cin >> c[bin][bout];
    }

    for(int i = 1; i <= m; ++i) {
        for(int j = 1; j <= n; ++j) {
            int ain = j, aout = j + DIFF;
            int bin = i + 2 * DIFF, bout = i + 3 * DIFF;
            g[bout].emplace_back(ain);
            g[ain].emplace_back(bout);
            c[bout][ain] = INF;
            int tmp; cin >> tmp;
            cost[bout][ain] = tmp;
            cost[ain][bout] = -tmp;
        }
    }

    cout << mcmf(s, e) << '\n';
    return 0;
}
