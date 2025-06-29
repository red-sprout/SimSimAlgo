#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;
typedef pair<int, ll> pil;

const ll MOD = 1'000'000'007;
const int MAX = 100'001;

int n;
ll ans = 0;
int dep[MAX];
vector<pil> g[MAX];

ll dfs(int cur, int d) {
    dep[cur] = d;
    ll sum = 1;
    for (auto p : g[cur]) {
        int nxt = p.first;
        ll w = p.second;
        if (dep[nxt] == 0) {
            ll sub = dfs(nxt, d + 1) * w % MOD;
            ans = (ans + sub * sum % MOD) % MOD;
            sum = (sum + sub) % MOD;
        }
    }
    return sum;
}

int main() {
    FASTIO;
    cin >> n;
    int a, b, w;
    fill(dep, dep + MAX, 0);
    for (int i = 0; i < n - 1; i++) {
        cin >> a >> b >> w;
        g[a].emplace_back(b, w);
        g[b].emplace_back(a, w);
    }
    dfs(1, 1);
    cout << ans << '\n';
    return 0;
}
