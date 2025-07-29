#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

string s;
int table[5001];

int get_max(string p) {
    int sz = p.size();
    int idx = 0, m = 0;
    fill(table, table + 5001, 0);
    for (int i = 1; i < sz; ++i) {
        while (idx > 0 && p[idx] != p[i]) idx = table[idx - 1];
        if (p[idx] == p[i]) m = max(m, table[i] = ++idx);
    }
    return m;
}

int main() {
    FASTIO;
    cin >> s;
    int ans = 0;
    for (int i = 0; i < s.length(); ++i) {
        ans = max(ans, get_max(s.substr(i, s.length() - i)));
    }
    cout << ans << '\n';
    return 0;
}
