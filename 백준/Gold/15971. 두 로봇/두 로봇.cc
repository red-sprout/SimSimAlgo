#include <iostream>
#include <vector>
#include <queue>
#define INF 1'000'000'001
#define SZ 1'000'001

using namespace std;
typedef pair<int, int> pii;

struct comp {
	bool operator()(const pii e1, const pii e2) {
		return e1.second > e2.second;
	}
};

int n, s, e;
int dist[SZ];
int p[SZ];
bool v[SZ];
priority_queue<pii, vector<pii>, comp> pq;
vector<pii> g[SZ];

int dijkstra() {
	pq.emplace(make_pair(s, 0));
	while (!pq.empty()) {
		pii cur = pq.top(); pq.pop();
		if (cur.first == e) return cur.second;
		if (v[cur.first]) continue;
		v[cur.first] = true;
		for (pii nxt : g[cur.first]) {
			if (!v[nxt.first] && dist[nxt.first] > cur.second + nxt.second) {
				dist[nxt.first] = cur.second + nxt.second;
				p[nxt.first] = cur.first;
				pq.emplace(make_pair(nxt.first, dist[nxt.first]));
			}
		}
	}

	return 0;
}

int path() {
	int res = 0;
	for (int cur = e; p[cur] != -1; cur = p[cur]) {
		for (pii nxt : g[cur]) {
			if (nxt.first == p[cur]) {
				res = max(res, nxt.second);
			}
		}
	}
	return res;
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
	fill(dist + 1, dist + n + 1, INF);
	fill(p + 1, p + n + 1, -1);
	fill(v + 1, v + n + 1, false);
	cout << (s != e ? dijkstra() - path() : 0) << '\n';
	return 0;
}