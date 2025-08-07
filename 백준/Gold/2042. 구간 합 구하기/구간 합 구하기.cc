#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

typedef long long ll;

struct SegTree {
    int size;
    vector<ll> tree;

    SegTree(int n) {
        size = n;
        tree.resize(2 * n);
    }

    void update(ll x, ll val) {
        for (tree[x += size] = val; x > 1; x >>= 1) tree[x >> 1] = tree[x] + tree[x ^ 1];
    }

    ll query(ll l, ll r) {
        ll res = 0;
        for (l += size, r += size; l < r; l >>= 1, r >>= 1) {
            if (l & 1) res += tree[l++];
            if (r & 1) res += tree[--r];
        }
        return res;
    }
};

int main() {
    FASTIO;
    int n, m, k; cin >> n >> m >> k;
    SegTree tree(n);
    ll a, b, c;
    for (int i = 0; i < n; ++i) {
        cin >> a; tree.update(i, a);
    }
    for (int i = 0; i < m + k; ++i) {
        cin >> a >> b >> c;
        if (a == 1) tree.update(b - 1, c);
        else cout << tree.query(b - 1, c) << '\n';
    }
    return 0;
}
