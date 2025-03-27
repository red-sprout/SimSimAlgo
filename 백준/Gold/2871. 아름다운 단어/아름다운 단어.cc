#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct paper {
	char c;
	int idx;
	bool exist;

	paper(char C, int I, bool E) {
		c = C;
		idx = I;
		exist = E;
	}
};

int p;
vector<paper> list;

struct comp {
	bool operator()(int a, int b) {
		if (list[a].c == list[b].c) {
			return list[a].idx < list[b].idx;
		}
		return list[a].c > list[b].c;
	}
};

priority_queue<int, vector<int>, comp> pq;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	int n;
	string str;
	cin >> n;
	cin >> str;
	p = n - 1;
	for (int i = 0; i < n; ++i) {
		list.emplace_back(paper(str[i], i, true));
		pq.emplace(i);
	}

	string ne = "";
	string da = "";

	while (!pq.empty() || p > -1) {
		while (p > -1) {
			if (list[p].exist) {
				ne += list[p].c;
				list[p].exist = false;
				--p;
				break;
			}
			--p;
		}
		while (!pq.empty()) {
			int i = pq.top(); pq.pop();
			if (list[i].exist) {
				da += list[i].c;
				list[i].exist = false;
				break;
			}
		}
	}

	cout << (ne.compare(da) > 0 ? "DA" : "NE") << '\n';
	cout << da << '\n';
	return 0;
}