#include <iostream>
#include <cstring>

using namespace std;
typedef pair<int, int> pii;

const int INF = 1e9;
int c, n, cnt;
pii info[20];
int dp[20][2000];

int dfs(int cur, int cust) {
  if (cust >= c) return 0;
  if (cur == n) return INF;

  int* res = &dp[cur][cust];
  if(*res != -1) return *res;
  *res = INF;

  for (int i = 0; i < cnt; ++i) {
    *res = min(*res, dfs(cur + 1, cust + i * info[cur].second) + i * info[cur].first);
  }
  return *res;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  cin >> c >> n;
  int a, b, m = INF;
  for (int i = 0; i < n; ++i) {
    cin >> a >> b;
    info[i] = {a, b};
    m = min(m, b);
  }
  cnt = c / m + 2;
  memset(dp, -1, sizeof(dp));
  cout << dfs(0, 0) << '\n';
  return 0;
}