#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n, m; cin >> n >> m;
    vector<vector<int>> circuit(n);
    for (int i = 0; i < n; ++i) {
        int cnt; cin >> cnt;
        circuit[i].resize(cnt);
        for (int j = 0; j < cnt; ++j) {
            cin >> circuit[i][j];
        }
    }

    bool light[m + 1], flag = false;
    for (int i = 0; i < n; ++i) {
        fill (light, light + m + 1, false);
        for (int j = 0; j < n; ++j) {
            if (i == j) continue;
            for (auto s : circuit[j]) {
                light[s] = true;
            }
        }
        flag = true;
        for (int j = 1; j < m + 1; ++j) {
            if (!light[j]) {
                flag = false;
                break;
            }
        }
        if (flag) break;
    }
    cout << flag << '\n';
    return 0;
}
