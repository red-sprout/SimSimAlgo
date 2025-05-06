#include <iostream>

using namespace std;

int arr[100'001];
int tree[400'001];

void init(int node, int s, int e) {
    if(s == e) {
        tree[node] = (arr[s] % 2 == 1) ? 1 : 0;
        return;
    }
    int m = (s + e) >> 1;
    init(node << 1, s, m);
    init(node << 1 | 1, m + 1, e);
    tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

void update(int node, int s, int e, int i, int x) {
    if(i < s || e < i) return;
    if(s == e) {
        tree[node] = (x % 2 == 1) ? 1 : 0;
        return;
    }
    int m = (s + e) >> 1;
    update(node << 1, s, m, i, x);
    update(node << 1 | 1, m + 1, e, i, x);
    tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

int query(int node, int s, int e, int ts, int te) {
    if(te < s || e < ts) return 0;
    if(ts <= s && e <= te) return tree[node];
    int m = (s + e) >> 1;
    return query(node << 1, s, m, ts, te) + query(node << 1 | 1, m + 1, e, ts, te);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n; cin >> n;
    for(int i = 1; i <= n; ++i) {
        cin >> arr[i];
    }

    init(1, 1, n);
    int m; cin >> m;
    int cmd, i, x, l, r, odds;
    while(m--) {
        cin >> cmd;
        if(cmd == 1) {
            cin >> i >> x;
            update(1, 1, n, i, x);
        } else if(cmd == 2) {
            cin >> l >> r;
            odds = query(1, 1, n, l, r);
            cout << r - l + 1 - odds << '\n';
        } else {
            cin >> l >> r;
            odds = query(1, 1, n, l, r);
            cout << odds << '\n';
        }
    }
    return 0;
}