#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int cnt = 0, f[10];

void init(string &n) {
    int sz = n.size();
    f[0] = 0;
    int idx = 0;
    for (int i = 1; i < sz; i++) {
        while (idx > 0 && n[i] != n[idx]) idx = f[idx - 1];
        if (n[i] == n[idx]) f[i] = ++idx;
    }
}

void kmp(string &h, string &n) {
    int hsz = h.size(), nsz = n.size();
    int idx = 0;
    for (int i = 0; i < hsz; i++) {
        while (idx > 0 && h[i] != n[idx]) idx = f[idx - 1];
        if (h[i] == n[idx]) {
            if (idx == nsz - 1) {
                ++cnt; idx = f[idx];
            } else {
                ++idx;
            }
        }
    }
}

int main() {
    FASTIO;
    string h, n; cin >> h >> n;
    init(n);
    kmp(h, n);
    cout << cnt;
    return 0;
}
