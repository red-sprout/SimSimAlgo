#include <iostream>
#define MAX 1e9 + 1
#define SIZE (1 << 17)

using namespace std;

int N, M;
int arr[SIZE];
int tree[SIZE << 1];

int merge(int idx1, int idx2) {
  return arr[tree[idx1]] > arr[tree[idx2]] ? tree[idx2] : tree[idx1];
}

void init(int node, int s, int e) {
  if(s == e) {
    tree[node] = s;
    return;
  }
  int mid = (s + e) >> 1;
  init(2 * node, s, mid);
  init(2 * node + 1, mid + 1, e);
  tree[node] = merge(2 * node, 2 * node + 1);
}

void update(int node, int s, int e, int idx, int val) {
  if(e < idx || idx < s) return;
  if(s == e) {
    tree[node] = val;
    return;
  }
  int mid = (s + e) >> 1;
  update(2 * node, s, mid, idx, val);
  update(2 * node + 1, mid + 1, e, idx, val);
  tree[node] = merge(2 * node, 2 * node + 1);
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

  cin >> N;
  for(int i = 1; i < N + 1; i++) {
    cin >> arr[i];
  }

  init(1, 1, N);
  int q, idx, val;
  cin >> M;
  for(int i = 0; i < M; i++) {
    cin >> q;
    switch (q) {
    case 1:
      cin >> idx >> val;
      arr[idx] = val;
      update(1, 1, N, idx, idx);
      break;
    case 2:
      cout << tree[1] << '\n';
      break;
    }
  }

  return 0;
}