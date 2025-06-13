#include <iostream>
#include <vector>

using namespace std;

const int MAX = 100'001;

int id = 0;
int tree[4 * MAX], lazy[4 * MAX];
int arr[MAX], dep[MAX], in[MAX], out[MAX];
vector<int> g[MAX];

void dfs(int cur, int d) {
    dep[cur] = d;
    in[cur] = ++id;
    for(auto nxt : g[cur]) {
        if(dep[nxt] == 0) dfs(nxt, d + 1);
    }
    out[cur] = id;
}

void update_lazy(int node, int s, int e) {
    if(lazy[node] == 0) return;
    tree[node] ^= lazy[node] * ((e - s + 1) % 2);
    if(s != e) {
        lazy[node << 1] ^= lazy[node];
        lazy[node << 1 | 1] ^= lazy[node];
    }
    lazy[node] = 0;
}

void update_tree(int node, int s, int e, int ts, int te, int val) {
    update_lazy(node, s, e);
    if(te < s || e < ts) return;
    if(ts <= s && e <= te) {
        tree[node] ^= val * ((e - s + 1) % 2);
        if(s != e) {
            lazy[node << 1] ^= val;
            lazy[node << 1 | 1] ^= val;
        }
        return;
    }
    int m = (s + e) >> 1;
    update_tree(node << 1, s, m, ts, te, val);
    update_tree(node << 1 | 1, m + 1, e, ts, te, val);
    tree[node] = tree[node << 1] ^ tree[node << 1 | 1];
}

int query(int node, int s, int e, int ts, int te) {
    update_lazy(node, s, e);
    if(te < s || e < ts) return 0;
    if(ts <= s && e <= te) return tree[node];
    int m = (s + e) >> 1;
    return query(node << 1, s, m, ts, te) ^ query(node << 1 | 1, m + 1, e, ts, te);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, m; cin >> n >> m;
    int a, b;
    for(int i = 0; i < n - 1; ++i) {
        cin >> a >> b;
        g[a].emplace_back(b);
        g[b].emplace_back(a);
    }
    dfs(1, 1);
    int val;
    for (int i = 1; i <= n; i++) {
        cin >> val;
        update_tree(1, 1, n, in[i], in[i], val);
    }
    int q, x, y;
    while(m--) {
        cin >> q;
        switch(q) {
            case 1:
                cin >> x;
                cout << query(1, 1, n, in[x], out[x]) << '\n';
                break;
            case 2:
                cin >> x >> y;
                update_tree(1, 1, n, in[x], out[x], y);
                break;
        }
    }
    return 0;
}