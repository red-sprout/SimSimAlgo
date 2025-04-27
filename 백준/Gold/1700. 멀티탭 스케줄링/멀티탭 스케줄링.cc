#include <iostream>
#include <unordered_set>
#include <vector>
#include <queue>
#define M 1'000'000'000

using namespace std;

int n, k;
vector<int> process;
queue<int> order[101];

struct comp {
	bool operator()(int p1, int p2) {
		return order[p1].front() < order[p2].front();
	}
};

void init() {
	cin >> n >> k;
	int name;
	for (int i = 0; i < k; ++i) {
		cin >> name;
		process.emplace_back(name);
		order[name].emplace(i);
	}
	for (int i = 0; i < 101; ++i) {
		order[i].emplace(M);
	}
}

int solution() {
	int cnt = 0, name;
	unordered_set<int> s;
    queue<int> q;
	priority_queue<int, vector<int>, comp> pq;
	for (int i = 0; i < k; ++i) {
		name = process[i];
		order[name].pop();
		if (s.find(name) == s.end()) {
			if(s.size() == n) {
				int rmv = pq.top(); pq.pop();
				s.erase(rmv);
				++cnt;
			}
			pq.emplace(name);
			s.insert(name);
		}
		while (!pq.empty()) {
			q.emplace(pq.top());
			pq.pop();
		}
		while (!q.empty()) {
			pq.emplace(q.front()); q.pop();
		}
	}

	return cnt;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	init();
	cout << solution() << '\n';
	return 0;
}