#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
const int MAX = 500'001;

int bfs(int n, int k) {
    if (n == k) return 0;
    queue<int> q;
    bool v[2][MAX];
    memset(v, false, sizeof(v));
    q.emplace(n);
    int t = 1;
    while (!q.empty()) {
        k += t;
        if (k >= MAX) return -1;
        if (v[t & 1][k]) return t;
        int sz = q.size();
        for (int j = 0; j < sz; ++j) {
            int x = q.front(); q.pop();
            int moves[] = {x - 1, x + 1, 2 * x};
            for (int nx : moves) {
                if (nx == k) return t;
                if (0 <= nx && nx < MAX && !v[t & 1][nx]) {
                    v[t & 1][nx] = true;
                    q.emplace(nx);
                }
            }
        }
        ++t;
    }
    return -1;
}

int main() {
    FASTIO;
    int n, k;
    cin >> n >> k;
    cout << bfs(n, k) << '\n';
    return 0;
}