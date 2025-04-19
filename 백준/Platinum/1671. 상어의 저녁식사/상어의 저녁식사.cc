#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#define SZ 102

using namespace std;

struct shark {
    int sz, vel, brain;

    shark(int s, int v, int b) {
        sz = s; vel = v; brain = b;
    }
};

vector<shark> sh;

bool same(shark s1, shark s2) {
    return s1.sz == s2.sz && s1.vel == s2.vel && s1.brain == s2.brain;
}

bool comp(shark s1, shark s2) {
    return s1.sz >= s2.sz && s1.vel >= s2.vel && s1.brain >= s2.brain;
}

int n;
int ss = 0, ee = SZ - 1;
vector<int> g[SZ];

int c[SZ][SZ];
int f[SZ][SZ];
int p[SZ];

int ek() {
    int res = 0;
    while(1) {
        memset(p, -1, sizeof(p));
        queue<int> q;

        q.emplace(ss);
        while(!q.empty()) {
            int cur = q.front(); q.pop();
            if(cur == ee) break;
            for(auto nxt : g[cur]) {
                if(p[nxt] == -1 && c[cur][nxt] > f[cur][nxt]) {
                    q.emplace(nxt);
                    p[nxt] = cur;
                }
            }
        }

        if(p[ee] == -1) break;

        int flow = INT32_MAX;
        for(int node = ee; node != ss; node = p[node]) {
            flow = min(flow, c[p[node]][node] - f[p[node]][node]);
        }

        res += flow;
        for(int node = ee; node != ss; node = p[node]) {
            f[p[node]][node] += flow;
            f[node][p[node]] -= flow;
        }
    }
    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;

    int s, v, b;
    sh.emplace_back(shark(0, 0, 0));
    for(int i = 1; i <= n; ++i) {
        g[ss].emplace_back(i);
        g[i].emplace_back(ss);
        c[ss][i] = 2;
        cin >> s >> v >> b;
        sh.emplace_back(shark(s, v, b));
    }

    for(int i = 1; i <= n; ++i) {
        for(int j = 1; j <= n; ++j) {
            if(i == j) continue;
            if(i > j && same(sh[i], sh[j])) continue;
            if(comp(sh[i], sh[j])) {
                g[i].emplace_back(j + 50);
                g[j + 50].emplace_back(i);
                c[i][j + 50] = 1;
            }
        }
    }

    for(int i = 51; i <= n + 50; ++i) {
        g[ee].emplace_back(i);
        g[i].emplace_back(ee);
        c[i][ee] = 1;
    }

    cout << n - ek() << '\n';
    return 0;
}