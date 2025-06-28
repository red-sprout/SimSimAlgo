#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int main() {
    FASTIO;
    int n; cin >> n;
    vector<ll> h(n);
    ll m = 0;
    for (int i = 0; i < n; i++) {
        cin >> h[i];
        m += h[i];
    }
    const ll lo = m / n, hi_cnt = m % n;
    sort(h.begin(), h.end(), greater<>());
    int idx = 0;
    ll res = 0;
    while (idx < n) {
        res += abs(h[idx] - lo - (idx < hi_cnt));
        ++idx;
    }
    cout << res / 2 << '\n';
    return 0;
}
