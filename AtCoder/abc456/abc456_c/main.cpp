#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

const int MOD = 998244353;

int main() {
    FASTIO;

    string s;
    cin >> s;

    long long ans = 0;
    int len = 0;

    for (int i = 0; i < s.length(); ++i) {
        if (i == 0 || s[i] != s[i - 1]) {
            len++;
        } else {
            len = 1;
        }

        ans = (ans + len) % MOD;
    }

    cout << ans << '\n';
    return 0;
}
