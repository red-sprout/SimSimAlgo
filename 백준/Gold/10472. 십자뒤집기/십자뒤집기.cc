#include <iostream>

using namespace std;

const int dr[] = {0, -1, 0, 1, 0};
const int dc[] = {0, 0, -1, 0, 1};

int paint(int idx, int b) {
  int r = idx / 3;
  int c = idx % 3;
  for(int d = 0; d < 5; ++d) {
    int nr = r + dr[d];
    int nc = c + dc[d];
    if(0 <= nr && nr < 3 && 0 <= nc && nc < 3) {
      b ^= 1 << (3 * nr + nc);
    }
  }
  return b;
}

int dfs(int idx, int prv, int nxt) {
  if(prv == nxt) return 0;
  if(idx == 9) return 1e9;
  int res = 1e9;
  res = min(res, dfs(idx + 1, prv, nxt));
  res = min(res, dfs(idx + 1, paint(idx, prv), nxt) + 1);
  return res;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  int p; cin >> p;
  char c;
  int prv, nxt;

  while(p--) {
    prv = 0, nxt = 0;
    for(int i = 0; i < 3; ++i) {
      for(int j = 0; j < 3; ++j) {
        cin >> c;
        if(c == '*') nxt |= 1 << (3 * i + j);
      }
    }

    cout << dfs(0, prv, nxt) << '\n';
  }
  return 0;
}