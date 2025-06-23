#include <bits/stdc++.h>

using namespace std;

struct Info {
    int node, exc, d;
    Info(int _n, int _e, int _d) : node(_n), exc(_e), d(_d) {}
};

const int INF = 1e9;

int n, m;
bool c[1001];
int e[1001][1001];

struct comp {
    bool operator() (const Info& i1, const Info& i2) {
        return i1.exc == i2.exc ? i1.d > i2.d : i1.exc > i2.exc;
    }
};

pair<int, int> dijkstra() {
    priority_queue<Info, vector<Info>, comp> pq;

    int dist[1001][1001];
    bool visited[1001][1001];
    for (int i = 0; i < 1001; ++i) {
        for (int j = 0; j < 1001; ++j) {
            dist[i][j] = INF;
            visited[i][j] = false;
        }
    }

    pq.emplace(0, 0, 0);
    dist[0][0] = 0;

    while (!pq.empty()) {
        Info cur = pq.top(); pq.pop();
        if (cur.node == m) return { cur.exc, cur.d };
        if (visited[cur.node][cur.exc]) continue;
        visited[cur.node][cur.exc] = true;
        for (int i = 0; i < n; i++) {
            int nexc = cur.exc + (c[cur.node] != c[i]);
            if (!visited[i][nexc] && dist[i][nexc] > dist[cur.node][cur.exc] + e[cur.node][i]) {
                dist[i][nexc] = dist[cur.node][cur.exc] + e[cur.node][i];
                pq.emplace(i, nexc, dist[i][nexc]);
            }
        }
    }

    return { -1, -1 };
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    for (int i = 0; i < 1001; ++i) {
        for (int j = 0; j < 1001; ++j) {
            e[i][j] = INF;
        }
    }

    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }

    int tmpDist;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> tmpDist;
            if (tmpDist) e[i][j] = tmpDist;
        }
    }

    pair<int, int> res = dijkstra();
    cout << res.first << ' ' << res.second << '\n';
    return 0;
}