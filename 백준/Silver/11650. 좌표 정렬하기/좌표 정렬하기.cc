#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n; cin >> n;
    vector<pair<int, int>> v;
    for (int i = 0; i < n; ++i) {
        int x, y; cin >> x >> y;
        v.emplace_back(x, y);
    }
    sort(v.begin(), v.end());
    for (auto [x, y] : v) {
        cout << x << " " << y << '\n';
    }
    return 0;
}
