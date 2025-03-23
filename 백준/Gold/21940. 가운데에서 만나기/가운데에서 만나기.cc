#include <iostream>
#include <vector>
#define MAX 1'000'000'000

using namespace std;

int n, m, k;
int dist[201][201];
vector<int> clist;

void fw() {
	for (int k = 1; k < n + 1; ++k) {
		for (int i = 1; i < n + 1; ++i) {
			for (int j = 1; j < n + 1; ++j) {
				dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
			}
		}
	}
}

vector<int> solution() {
	int max_time = MAX;
	vector<int> res;
	for (int i = 1; i < n + 1; ++i) {
		int time = 0;
		for (int c : clist) {
			time = max(time, dist[i][c] + dist[c][i]);
		}
		if (max_time > time) {
			res.clear();
			res.emplace_back(i);
			max_time = time;
		}
		else if (max_time == time) {
			res.emplace_back(i);
		}
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n >> m;
	for (int i = 1; i < n + 1; ++i) {
		for (int j = 1; j < n + 1; ++j) {
			dist[i][j] = i == j ? 0 : MAX;
		}
	}

	int a, b, t;
	for (int i = 0; i < m; ++i) {
		cin >> a >> b >> t;
		dist[a][b] = t;
	}

	fw();

	cin >> k;
	clist.resize(k);
	for (int i = 0; i < k; ++i) {
		cin >> clist[i];
	}

	vector<int> res = solution();
	for (int v : res) {
		cout << v << ' ';
	}
	cout << '\n';
	return 0;
}