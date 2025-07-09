#include <iostream>
#include <vector>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

struct SegTree {
    int size;
    vector<ll> tree;

    SegTree(int n) {
        size = n;
        tree.resize(4 * n);
    }

    void build(int node, int s, int e, const vector<ll>& arr) {
        if (s == e) {
            tree[node] = arr[s];
            return;
        }
        int m = (s + e) >> 1;
        build(node << 1, s, m, arr);
        build(node << 1 | 1, m + 1, e, arr);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    void update(int node, int s, int e, int idx, ll val) {
        if (idx < s || idx > e) return;
        if (s == e) {
            tree[node] = val;
            return;
        }
        int m = (s + e) >> 1;
        update(node << 1, s, m, idx, val);
        update(node << 1 | 1, m + 1, e, idx, val);
        tree[node] = tree[node << 1] + tree[node << 1 | 1];
    }

    ll query(int node, int s, int e, int ts, int te) {
        if (te < s || e < ts) return 0;
        if (ts <= s && e <= te) return tree[node];
        int m = (s + e) >> 1;
        return query(node << 1, s, m, ts, te) + query(node << 1 | 1, m + 1, e, ts, te);
    }

    void build(const vector<ll>& arr) {
        build(1, 0, size - 1, arr);
    }

    void update(int idx, ll val) {
        update(1, 0, size - 1, idx, val);
    }

    ll query(int l, int r) {
        return query(1, 0, size - 1, l, r);
    }
};

int main() {
    FASTIO;
    int n, m, k; cin >> n >> m >> k;
    vector<ll> arr(n);
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }

    SegTree seg_tree(n);
    seg_tree.build(arr);

    int a;
    ll b, c;
    for (int i = 0; i < m + k; ++i) {
        cin >> a >> b >> c;
        if (a == 1) {
            seg_tree.update(b - 1, c);
        } else {
            cout << seg_tree.query(b - 1, c - 1) << '\n';
        }
    }
    return 0;
}
