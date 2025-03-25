#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

struct line {
	int x1, y1, x2, y2;
};

int n;
int parent[3000];
vector<line> lines;

ll ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
	ll d[] = { x2 - x1, y2 - y1, x3 - x2, y3 - y2 };
	return d[0] * d[3] - d[1] * d[2];
}

bool in_range(int x1, int y1, int x2, int y2, int x3, int y3) {
	bool inx, iny;
	inx = min(x1, x2) <= x3 && x3 <= max(x1, x2);
	iny = min(y1, y2) <= y3 && y3 <= max(y1, y2);
	return inx && iny;
}

bool meet(line l1, line l2) {
	ll res[4];
	res[0] = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1);
	res[1] = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
	if (res[0] * res[1] > 0) return false;
	res[2] = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1);
	res[3] = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);
	if (res[2] * res[3] > 0) return false;
	if (res[0] * res[1] == 0 && res[2] * res[3] == 0) {
		if (in_range(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1)) return true;
		if (in_range(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2)) return true;
		if (in_range(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1)) return true;
		if (in_range(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2)) return true;
		return false;
	}
	return true;
}

int find_parent(int x) {
	if (parent[x] < 0) return x;
	return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y) {
	x = find_parent(x);
	y = find_parent(y);
	if (x == y) return;
	int szx = -parent[x];
	int szy = -parent[y];
	if (szx > szy) {
		parent[y] = x;
		parent[x] -= szy;
	}
	else {
		parent[x] = y;
		parent[y] -= szx;
	}
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

	cin >> n;
	lines.resize(n);
	fill(parent, parent + n, -1);

	int x1, y1, x2, y2;
	for (int i = 0; i < n; ++i) {
		cin >> x1 >> y1 >> x2 >> y2;
		lines[i] = { x1, y1, x2, y2 };
	}

	for (int i = 0; i < n - 1; ++i) {
		for (int j = i + 1; j < n; ++j) {
			if (meet(lines[i], lines[j])) {
				union_parent(i, j);
			}
		}
	}

	int cnt = 0;
	int max_size = 0;
	for (int i = 0; i < n; ++i) {
		if (parent[i] < 0) {
			++cnt;
			max_size = max(max_size, -parent[i]);
		}
	}

	cout << cnt << '\n' << max_size << '\n';
	return 0;
}