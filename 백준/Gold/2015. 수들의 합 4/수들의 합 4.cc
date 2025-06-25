#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int main() {
    FASTIO;
    int n, k; cin >> n >> k;
    ll a, p = 0;
    unordered_map<ll, ll> cnt_map;
    cnt_map[0] = 1;

    ll cnt = 0;
    for (int i = 1; i <= n; i++) {
        cin >> a; p += a;
        cnt += cnt_map[p - k];
        ++cnt_map[p];
    }

    cout << cnt << '\n';
    return 0;
}
