#include <iostream>
#include <vector>

using namespace std;

struct query {
	int order, x, a, b;
	query(int Q, int X, int A) {
		order = Q; x = X; a = A; b = 0;
	}
	query(int Q, int X, int A, int B) {
		order = Q; x = X; a = A; b = B;
	}
};

const string yes = "YES";
const string no = "NO";
const int MAX = 200'001;

int n;
int tree[MAX];
int parent[MAX];
vector<query> queries;

int find_parent(int x) {
	if (parent[x] == x) return x;
	return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y) {
	x = find_parent(x);
	y = find_parent(y);
	if (x == y) return;
	parent[x] = y;
}

vector<string> solution() {
	int a, b;
	vector<string> res;
	for (int i = queries.size() - 1; i >= 0; i--) {
		if (queries[i].x == 0) {
			a = queries[i].a;
			union_parent(a, tree[a]);
		}
		else {
			a = queries[i].a;
			b = queries[i].b;
			if (find_parent(a) == find_parent(b)) {
				res.emplace_back(yes);
			}
			else {
				res.emplace_back(no);
			}
		}
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	
	int q;
	cin >> n >> q;
	for (int i = 2; i < n + 1; ++i) {
		cin >> tree[i];
	}
	for (int i = 1; i < n + 1; ++i) {
		parent[i] = i;
	}

	int x, a, b;
	for (int i = 0; i < (q + n - 1); ++i) {
		cin >> x >> a;
		if (x == 0) {
			query cur = query(i, x, a);
			queries.emplace_back(cur);
		}
		else {
			cin >> b;
			query cur = query(i, x, a, b);
			queries.emplace_back(cur);
		}
	}

	vector<string> res = solution();
	for (int i = res.size() - 1; i >= 0; --i) {
		cout << res[i] << '\n';
	}

	return 0;
}