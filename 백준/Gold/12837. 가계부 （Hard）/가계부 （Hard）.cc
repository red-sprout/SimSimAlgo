#include <iostream>
#define HALF (1 << 20)

using namespace std;
typedef long long ll;

int n, m; 
ll tree[HALF << 1];

void update(int node, int s, int e, int p, int x) {
    if(e < p || p < s) return;
    if(s == e) {
      tree[node] += x;
      return;
    }
    int mid = (s + e) >> 1;
    update(2 * node, s, mid, p, x);
    update(2 * node + 1, mid + 1, e, p, x);
    tree[node] = tree[2 * node] + tree[2 * node + 1];
}

ll get(int node, int s, int e, int p, int q) {
    if(e < p || q < s) return 0;
    if(p <= s && e <= q) return tree[node];
    int mid = (s + e) >> 1;
    return get(2 * node, s, mid, p, q) + get(2 * node + 1, mid + 1, e, p, q);
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> m;

    while (m--) {
        int cmd;
        cin >> cmd;
        if (cmd == 1) {
            int p, x;
            cin >> p >> x;
            update(1, 1, n, p, x);
        } 
        else if (cmd == 2) {
            int p, q;
            cin >> p >> q;
            cout << get(1, 1, n, p, q) << '\n';
        }
    }

    return 0;
}
