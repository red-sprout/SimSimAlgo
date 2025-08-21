#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        int c, g; cin >> g;
        map<int, int> mp;
        while (g--) {
            cin >> c;
            if (mp.find(c) == mp.end()) mp[c] = 0;
            mp[c]++;
        }
        for (auto it = mp.begin(); it != mp.end(); it++) {
            if (it->second & 1) {
                cout << "Case #" << i << ": " << it->first << '\n';
                break;
            }
        }
    }
    return 0;
}
