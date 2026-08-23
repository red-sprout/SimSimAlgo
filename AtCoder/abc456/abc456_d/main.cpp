#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

const int MOD = 998244353;

int main() {
    FASTIO;
    string s;
    cin >> s;
    ll dp[] = {0, 0, 0};
    for(char c : s) {
        int idx = c - 'a';
        ll v = 1;
        for (int i = 0; i < 3; ++i) {
            if (i == idx) continue;
            v = (v + dp[i]) % MOD;
        }
        dp[idx] = (dp[idx] + v) % MOD;
    }
    cout << (dp[0] + dp[1] + dp[2]) % MOD << '\n';
    return 0;
}
