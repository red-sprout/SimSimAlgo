#include <bits/stdc++.h>

using namespace std;
typedef pair<int, int> pii;

constexpr int MAX = 1e6;
typedef long long ll;

int u[MAX];

void init() {
    u[1] = 1;
    for (int i = 1; i < MAX; ++i) {
        for (int j = 2 * i; j < MAX; j += i) {
            u[j] -= u[i];
        }
    }
}

ll sfn(ll x) {
    ll res = 0;
    for (ll i = 1; i * i <= x; ++i) res += u[i] * x / (i * i);
    return res;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    init();
    int k; cin >> k;
    ll left = 0, right = 1e10;
    while (left + 1 < right) {
        ll mid = (left + right) / 2;
        if (sfn(mid) < k) left = mid;
        else right = mid;
    }
    cout << right << '\n';
    return 0;
}