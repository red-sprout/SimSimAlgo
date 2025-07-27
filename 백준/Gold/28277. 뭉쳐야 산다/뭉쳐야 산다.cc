#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

constexpr int MAX = 500'001;
set<int> s[MAX];

void union_p(int x, int y) {
    if (s[y].size() > s[x].size()) swap(s[x], s[y]);
    for (auto e : s[y]) s[x].emplace(e);
    s[y].clear();
}

int main() {
    FASTIO;
    int n, q; cin >> n >> q;
    int ni, si;
    for (int i = 1; i <= n; ++i) {
        cin >> ni;
        for (int j = 0; j < ni; ++j) {
            cin >> si;
            s[i].emplace(si);
        }
    }
    int cmd, a, b;
    while (q--) {
        cin >> cmd;
        if (cmd == 1) {
            cin >> a >> b;
            union_p(a, b);
        } else {
            cin >> a;
            cout << s[a].size() << '\n';
        }
    }
    return 0;
}