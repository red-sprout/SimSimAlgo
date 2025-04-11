#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#define INF 1'000'000'001
#define SZ 1'000'001

using namespace std;
typedef pair<int, int> pii;

struct edge {
	int v; int d; int u;
	edge(int V, int D, int U) {
		v = V; d = D; u = U;
	}
};

struct comp {
	bool operator()(const edge e1, const edge e2) {
		return e1.d > e2.d;
	}
};

int n, s, e;
int dist[SZ][2];
bool v[SZ][2];
priority_queue<edge, vector<edge>, comp> pq;
vector<pii> g[SZ];

int dijkstra() {
	pq.emplace(edge(s, 0, 0));
	while (!pq.empty()) {
		edge cur = pq.top(); pq.pop();
		if (cur.v == e) return cur.d;
		if (v[cur.v][cur.u]) continue;
		v[cur.v][cur.u] = true;
		for (pii nxt : g[cur.v]) {
			if (!v[nxt.first][cur.u] && dist[nxt.first][cur.u] > cur.d + nxt.second) {
				dist[nxt.first][cur.u] = cur.d + nxt.second;
				pq.emplace(edge(nxt.first, dist[nxt.first][cur.u], cur.u));
			}
			if (cur.u == 0 && !v[nxt.first][1] && dist[nxt.first][1] > cur.d) {
				dist[nxt.first][1] = cur.d;
				pq.emplace(edge(nxt.first, dist[nxt.first][1], 1));
			}
		}
	}

	return -1;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> s >> e;
	int a, b, c;
	for (int i = 0; i < n - 1; ++i) {
		cin >> a >> b >> c;
		g[a].emplace_back(make_pair(b, c));
		g[b].emplace_back(make_pair(a, c));
	}
	memset(dist, INF, sizeof(dist));
	memset(v, false, sizeof(v));
	cout << dijkstra() << '\n';
	return 0;
}