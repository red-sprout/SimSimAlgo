#include <iostream>
#include <vector>

using namespace std;

const int MAX = 10101;
int n, m;
bool v[MAX];
int sz[MAX];
vector<int> g[MAX];

void init() {
  cin >> n >> m;

  while(m--) {
    int a, b;
    cin >> a >> b;
    g[b].emplace_back(a);
  }
}

int dfs(int cur) {
  v[cur] = true;
  sz[cur] = 1;

  for(auto nxt : g[cur]) {
    if(!v[nxt]) {
      sz[cur] += dfs(nxt);
    }
  }

  return sz[cur];
}

void solution() {
  int ans[n + 1];
  int cnt = 0;

  for(int i = 1; i <= n; i++) {
    fill(&v[1], &v[n + 1], false);
    fill(&sz[1], &sz[n + 1], 0);
    ans[i] = dfs(i);
    cnt = max(cnt, ans[i]);
  }

  for(int i = 1; i <= n; i++) {
    if(cnt == ans[i]) cout << i << ' ';
  }
  cout << '\n';
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  init();
  solution();
}