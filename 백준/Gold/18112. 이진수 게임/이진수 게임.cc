#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

constexpr int MAX = 1 << 12;

int to_num(string s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
        int p = s.length() - i - 1;
        res |= (s[i] - '0') << p;
    }
    return res;
}

int bfs(int a, int b) {
    if (a == b) return 0;
    queue<pair<int, int>> q;
    bool v[MAX];
    q.emplace(a, 0);
    fill(v, v + MAX, false);
    v[a] = true;
    
    while (!q.empty()) {
        auto [x, t] = q.front(); q.pop();
        
        int range = 1;
        while (range <= x) range <<= 1;
        for (int i = 1; i < range / 2; i <<= 1) {
            int nx = x ^ i;
            if (nx == b) return t + 1;
            if (!v[nx]) {
                v[nx] = true;
                q.emplace(nx, t + 1);
            }
        }
        
        int nx = x + 1;
        if (nx < MAX) {
            if (nx == b) return t + 1;
            if (!v[nx]) {
                v[nx] = true;
                q.emplace(nx, t + 1);
            }
        }
        
        nx = x - 1;
        if (nx > 0) {
            if (nx == b) return t + 1;
            if (!v[nx]) {
                v[nx] = true;
                q.emplace(nx, t + 1);
            }
        }
    }
    return -1;
}

int main() {
    FASTIO;
    string s1, s2; cin >> s1 >> s2;
    int a = to_num(s1), b = to_num(s2);
    cout << bfs(a, b) << '\n';
    return 0;
}