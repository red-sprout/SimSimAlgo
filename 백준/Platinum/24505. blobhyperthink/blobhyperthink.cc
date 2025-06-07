#include <iostream>

using namespace std;
typedef long long ll;

const int MAX = 100'001;
const int MOD = 1'000'000'007;

ll tree[12][4 * MAX];

void update(int l, int node, int s, int e, int idx, ll val) {
    if(e < idx || idx < s) return;
    if(s == e) {
        tree[l][node] = (tree[l][node] + val) % MOD;
        return;
    }
    int m = (s + e) >> 1;
    update(l, node << 1, s, m, idx, val);
    update(l, node << 1 | 1, m + 1, e, idx, val);
    tree[l][node] = (tree[l][node << 1] + tree[l][node << 1 | 1]) % MOD;
}

ll query(int l, int node, int s, int e, int ts, int te) {
    if(te < s || e < ts) return 0;
    if(ts <= s && e <= te) return tree[l][node];
    int m = (s + e) >> 1;
    return (query(l, node << 1, s, m, ts, te) + query(l, node << 1 | 1, m + 1, e, ts, te)) % MOD;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    
    int n, a; cin >> n;
    for(int cur = 1; cur <= n; ++cur) {
        cin >> a; update(1, 1, 0, n, a, 1);
        for(int l = 2; l < 12; ++l) {
            ll val = query(l - 1, 1, 0, n, 0, a - 1);
            update(l, 1, 0, n, a, val);
        }
    }

    cout << query(11, 1, 0, n, 0, n) << '\n';
    return 0;
}
