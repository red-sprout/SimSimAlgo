#include <iostream>
#include <vector>
#define MAX 100001

using namespace std;

int N, M;
int depth[MAX];
int dp[20][MAX];
vector<int> tree[MAX];

void dfs(int cur, int d) {
    depth[cur] = d;
    for(auto nxt = tree[cur].begin(); nxt != tree[cur].end(); nxt++) {
        if(depth[*nxt] == 0) {
            dfs(*nxt, d + 1);
            dp[0][*nxt] = cur;
        }
    }
}

int query(int u, int v) {
    int d, n1, n2;
    if(depth[u] > depth[v]) {
        d = depth[u] - depth[v]; n1 = u; n2 = v;
    } else {
        d = depth[v] - depth[u]; n1 = v; n2 = u;
    }

    for(int i = 19; i >= 0; i--) {
        if((d & (1 << i)) != 0) {
            n1 = dp[i][n1];
        }
    }

    if(n1 == n2) return n1;

    for(int i = 19; i >= 0; i--) {
        if(dp[i][n1] != dp[i][n2]) {
            n1 = dp[i][n1];
            n2 = dp[i][n2];
        }
    }

    return dp[0][n1];
}

void init() {
    cin >> N;
    int u, v;
    for(int i = 0; i < N - 1; i++) {
        cin >> u >> v;
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
    
    dfs(1, 1);
    for(int i = 1; i < 20; i++) {
        for(int j = 1; j <= N; j++) {
            dp[i][j] = dp[i - 1][dp[i - 1][j]];
        }
    }
}

void solve() {
    cin >> M;
    int u, v;
    for(int i = 0; i < M; i++) {
        cin >> u >> v;
        cout << query(u, v) << '\n';
    }
}

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  init();
  solve();
  return 0;
}