#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MAX = 202;
const int INF = 1e9;

vector<int> g[MAX];
int c[MAX][MAX], f[MAX][MAX], w[MAX][MAX];

void connect(int from, int to) {
  g[from].emplace_back(to);
  g[to].emplace_back(from);
}

pair<int, int> mcmf(int s, int e) {
  pair<int, int> res;
  int p[MAX], dist[MAX];
  bool inq[MAX];

  while(1) {
    queue<int> q;
    fill(p, p + MAX, -1);
    fill(dist, dist + MAX, INF);
    fill(inq, inq + MAX, 0);
    q.emplace(s);
    dist[s] = 0;
    inq[s] = 1;

    while(!q.empty()) {
      int cur = q.front(); q.pop(); inq[cur] = 0;
      for(auto nxt : g[cur]) {
        if(dist[nxt] > dist[cur] + w[cur][nxt] && c[cur][nxt] - f[cur][nxt] > 0) {
          p[nxt] = cur;
          dist[nxt] = dist[cur] + w[cur][nxt];
          if(!inq[nxt]) {
            q.emplace(nxt);
            inq[nxt] = 1;
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
      res.second += flow * w[p[cur]][cur];
      f[p[cur]][cur] += flow;
      f[cur][p[cur]] -= flow;
    }

    res.first += flow;
  }

  return res;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  int n, m; cin >> n >> m;
  int s = 0, e = 201;

  int cnt;
  for (int i = 1; i <= n; ++i) {
    cin >> c[i + 100][e];
    connect(i + 100, e);
  }

  for(int i = 1; i <= m; ++i) {
    cin >> c[s][i];
    connect(s, i);
  }

  for(int i = 1; i <= m; ++i) {
    for(int j = 1; j <= n; ++j) {
      connect(i, j + 100);
    }
  }

  for (int i = 1; i <= m; ++i) {
    for (int j = 1; j <= n; ++j) {
      cin >> c[i][j + 100];
    }
  }

  for (int i = 1; i <= m; ++i) {
    for (int j = 1; j <= n; ++j) {
      cin >> w[i][j + 100];
      w[j + 100][i] = -w[i][j + 100];
    }
  }

  pair<int, int> p = mcmf(s, e);
  cout << p.first << '\n' << p.second << '\n';
  return 0;
}