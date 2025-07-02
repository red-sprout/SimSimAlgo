#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

const int MAX = 1e6;
typedef long long ll;

int u[MAX];

void init() {
    u[1] = 1;
    for (int i = 1; i < MAX; i++) {
        for (int j = 2 * i; j < MAX; j += i) {
            u[j] -= u[i];
        }
    }
}

ll sfn(ll x) {
    ll res = 0;
    for (ll i = 1; i * i <= x; i++) res += u[i] * x / (i * i);
    return res;
}

ll get_sfn(ll k) {
    ll left = 0, right = 1e12 + 1;
    while (left + 1 < right) {
        ll mid = (left + right) / 2;
        if (sfn(mid) < k) left = mid;
        else right = mid;
    }
    return right;
}

int main() {
    FASTIO;
    init();
    ll a, b; cin >> a >> b;
    ll pa = sfn(a), pb = sfn(b);
    ll res = pb - pa;
    if (a == get_sfn(pa)) ++res;
    cout << res << '\n';
    return 0;
}
