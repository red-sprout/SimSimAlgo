#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef vector<int> vi;

const int sz = 100'001;

int n;
int arr[sz];
vi tree[4 * sz];

vi merge_node(vi node1, vi node2) {
    int i1 = 0, i2 = 0, idx = 0, n1 = node1.size(), n2 = node2.size();
    vi res(n1 + n2);
    while(i1 < n1 && i2 < n2) {
        if(node1[i1] < node2[i2]) res[idx++] = node1[i1++];
        else res[idx++] = node2[i2++];
    }

    while(i1 < n1) res[idx++] = node1[i1++];
    while(i2 < n2) res[idx++] = node2[i2++];

    return res;
}

void init(int node, int s, int e) {
    if(s == e) {
        tree[node] = { arr[s] };
        return;
    }
    int m = (s + e) >> 1;
    init(node << 1, s, m);
    init(node << 1 | 1, m + 1, e);
    tree[node] = merge_node(tree[node << 1], tree[node << 1 | 1]);
}

int get_num(int node, int s, int e, int ts, int te, int val) {
    if(te < s || e < ts) return 0;
    if(ts <= s && e <= te) return upper_bound(tree[node].begin(), tree[node].end(), val) - tree[node].begin();
    int m = (s + e) >> 1;
    return get_num(node << 1, s, m, ts, te, val) + get_num(node << 1 | 1, m + 1, e, ts, te, val);
}

int query(int i, int j, int k) {
    return j - i - get_num(1, 1, n, i, j, k) + 1;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    for(int i = 1; i <= n; ++i) cin >> arr[i];
    init(1, 1, n);
    int last_ans = 0, a, b, c, m;
    cin >> m;
    while(m--) {
        cin >> a >> b >> c;
        last_ans = query(a ^ last_ans, b ^ last_ans, c ^ last_ans);
        cout << last_ans << '\n';
    }
    return 0;
}
