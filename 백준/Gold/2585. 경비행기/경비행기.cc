#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
struct Node {
    int id, x, y;
    Node(int id, int x, int y) : id(id), x(x), y(y) {}
};

vector<Node> nodes;

int get_fuel(int i1, int i2) {
    Node n1 = nodes[i1], n2 = nodes[i2];
    int d = (n1.x - n2.x) * (n1.x - n2.x) + (n1.y - n2.y) * (n1.y - n2.y);
    double sq = sqrt(d);
    if (int(sq) * int(sq) == d && int(sq) % 10 == 0) return int(sq) / 10;
    return int(sq) / 10 + 1;
}

int n, k;
bool v[1111];

bool bfs(int target) {
    queue<pair<int, int>> q;
    fill(v, v + n + 2, false);
    q.emplace(0, 0);
    v[0] = true;
    while (!q.empty()) {
        auto [now, cnt] = q.front(); q.pop();
        if (now == n + 1) return true;
        for(int i = 0; i <= n + 1; ++i) {
            if (i != now && !v[i] && cnt <= k && get_fuel(now, i) <= target) {
                v[i] = true;
                q.emplace(i, cnt + 1);
            }
        }
    }
    return false;
}

int main() {
    FASTIO;
    cin >> n >> k;
    int x, y;
    nodes.emplace_back(0, 0, 0);
    for (int i = 1; i <= n; i++) {
        cin >> x >> y;
        nodes.emplace_back(i, x, y);
    }
    nodes.emplace_back(n + 1, 10000, 10000);
    int l = 0, r = 1500;
    while (l + 1 < r) {
        int mid = (l + r) / 2;
        if (bfs(mid)) r = mid;
        else l = mid;
    }
    cout << r << '\n';
    return 0;
}
