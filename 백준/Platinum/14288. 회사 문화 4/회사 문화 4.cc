#include <iostream>
#include <vector>
#define HALF (1 << 17)

using namespace std;
typedef long long ll;

int n, m;
int depth[HALF], parent[HALF], s[HALF], e[HALF];
vector<int> connected[HALF];
ll tree[HALF << 1][2], lazy[HALF << 1][2];
int check = 0;
int cnt = 0;

void dfs(int cur, int d) {
  depth[cur] = d;
  s[cur] = ++cnt;
  for(auto nxt : connected[cur]) {
    if(depth[nxt] == 0) {
      dfs(nxt, d + 1);
    }
  }
  e[cur] = cnt;
}

void update_lazy(int node, int s, int e, int up) {
  if(lazy[node][up] == 0) return;
  tree[node][up] += (e - s + 1) * lazy[node][up];
  if(s != e) {
    lazy[2 * node][up] += lazy[node][up];
    lazy[2 * node + 1][up] += lazy[node][up];
  }
  lazy[node][up] = 0;
}

void update_tree(int node, int s, int e, int ts, int te, long val, int up) {
  update_lazy(node, s, e, up);
  if(e < ts || te < s) return;
  if(ts <= s && e <= te) {
    tree[node][up] += (e - s + 1) * val;
    if(s != e) {
      lazy[2 * node][up] += val;
      lazy[2 * node + 1][up] += val;
    }
    return;
  }
  int mid = (s + e) >> 1;
  update_tree(2 * node, s, mid, ts, te, val, up);
  update_tree(2 * node + 1, mid + 1, e, ts, te, val, up);
  tree[node][up] = tree[2 * node][up] + tree[2 * node + 1][up];
}

ll get(int node, int s, int e, int ts, int te, int up) {
  update_lazy(node, s, e, up);
  if(e < ts || te < s) return 0;
  if(ts <= s && e <= te) return tree[node][up];
  int mid = (s + e) >> 1;
  return get(2 * node, s, mid, ts, te, up) + get(2 * node + 1, mid + 1, e, ts, te, up);
}

void query1(int i, int w) {
  if(check) {
    update_tree(1, 1, n, s[i], s[i], w, 1);
  } else {
    update_tree(1, 1, n, s[i], e[i], w, 0);
  }
}

ll query2(int i) {
  return get(1, 1, n, s[i], e[i], 1) + get(1, 1, n, s[i], s[i], 0);
}

void query3() {
  check = 1 - check;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  cin >> n >> m;
  for(int i = 1; i < n + 1; i++) {
    cin >> parent[i];
    if(parent[i] != -1) {
      connected[i].emplace_back(parent[i]);
      connected[parent[i]].emplace_back(i);
    }
  }
  dfs(1, 1);

  int q, i, w;
  while(m--) {
    cin >> q;
    switch(q) {
      case 1:
        cin >> i >> w;
        query1(i, w);
        break;
      case 2:
        cin >> i;
        cout << query2(i) << '\n';
        break;
      case 3:
        query3();
        break;
    }
  }

  return 0;
}
