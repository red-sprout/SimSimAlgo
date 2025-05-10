#include <iostream>
#include <vector>
#include <algorithm>
#define SZ 100'000

using namespace std;
typedef long long ll;

struct Query {
    int cmd;
    int k;
    int i, j;
    ll v;
    int id;
};

ll arr[SZ + 1];
ll tree[4 * SZ + 1];
ll ans[SZ + 1];

void init(int node, int s, int e) {
    if (s == e) {
        tree[node] = arr[s];
        return;
    }
    int m = (s + e) >> 1;
    init(node << 1, s, m);
    init(node << 1 | 1, m + 1, e);
    tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

void cmd1(int node, int s, int e, int idx, ll val) {
    if (idx < s || e < idx) return;
    if (s == e) {
        tree[node] = val;
        return;
    }
    int m = (s + e) >> 1;
    cmd1(node << 1, s, m, idx, val);
    cmd1(node << 1 | 1, m + 1, e, idx, val);
    tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

ll cmd2(int node, int s, int e, int l, int r) {
    if (r < s || e < l) return 0;
    if (l <= s && e <= r) return tree[node];
    int m = (s + e) >> 1;
    return cmd2(node << 1, s, m, l, r)
         + cmd2(node << 1 | 1, m + 1, e, l, r);
}

bool comp(const Query &a, const Query &b) {
    if (a.k != b.k) return a.k < b.k;
    return a.cmd < b.cmd;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    for (int i = 1; i <= n; ++i)
        cin >> arr[i];

    init(1, 1, n);

    int m;
    cin >> m;
    vector<Query> queries;
    queries.reserve(m);

    int update_cnt = 0, query_cnt = 0;
    for (int t = 0; t < m; ++t) {
        int cmd;
        cin >> cmd;
        if (cmd == 1) {
            ++update_cnt;
            Query q;
            q.cmd = 1;
            q.k = update_cnt;
            cin >> q.i >> q.v;
            queries.push_back(q);
        } else {
            Query q;
            q.cmd = 2;
            cin >> q.k >> q.i >> q.j;
            q.id = ++query_cnt;
            queries.push_back(q);
        }
    }

    sort(queries.begin(), queries.end(), comp);

    for (auto &q : queries) {
        if (q.cmd == 1) {
            cmd1(1, 1, n, q.i, q.v);
        } else {
            ans[q.id] = cmd2(1, 1, n, q.i, q.j);
        }
    }

    for (int i = 1; i <= query_cnt; ++i)
        cout << ans[i] << '\n';

    return 0;
}
