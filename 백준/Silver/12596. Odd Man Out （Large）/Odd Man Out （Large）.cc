#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n; cin >> n;
    for (int i = 1; i <= n; i++) {
        int c, res = 0, g; cin >> g;
        while (g--) {
            cin >> c; res ^= c;
        }
        cout << "Case #" << i << ": " << res << '\n';
    }
    return 0;
}
