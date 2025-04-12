#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct comp {
  bool operator()(int a, int b) {
      return a > b;
  }  
};

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	priority_queue<int, vector<int>, comp> pq;

	int n;
	cin >> n;
	for (int i = 0; i < n * n; ++i) {
		int v;
		cin >> v;
		pq.emplace(v);
        if(pq.size() > n) pq.pop();
	}

	cout << pq.top() << '\n';
	return 0;
}