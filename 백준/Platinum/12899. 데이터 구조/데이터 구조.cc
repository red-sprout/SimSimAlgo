#include <iostream>

using namespace std;

const int MAX = 2000000;
int tree[1 << 22];

void updateTree(int node, int s, int e, int idx, int v) {
  if(e < idx || idx < s) return;
  if(s == e) {
    tree[node] += v;
    return;
  }
  int mid = (s + e) >> 1;
  updateTree(node << 1, s, mid, idx, v);
  updateTree(node << 1 | 1, mid + 1, e, idx, v);
  tree[node] = tree[node << 1] + tree[node << 1 | 1];
}

int get(int node, int s, int e, int k) {
  tree[node] -= 1;
  if(s == e) return s;
  int left = tree[node << 1];
  int mid = (s + e) >> 1;
  if(k <= left) {
    return get(node << 1, s, mid, k);
  } else {
    return get(node << 1 | 1, mid + 1, e, k - left);
  }
}

void update(int val) {
  updateTree(1, 1, MAX, val, 1);
}

int query(int val) {
  return get(1, 1, MAX, val);
}

void solution() {
  int n, cmd, val;
  cin >> n;
  while(n-- > 0) {
    cin >> cmd >> val;
    if(cmd == 1) {
      update(val);
    } else {
      cout << query(val) << '\n';
    }
  }
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  solution();
  return 0;
}