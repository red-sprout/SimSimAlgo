#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

constexpr int MAX = 300'001;

int n; ll s;
vector<pll> info;
vector<int> nxt;
vector<ll> dp;

ll dfs(int cur) {
    if (cur > n) return 0;
    ll *res = &dp[cur];
    if (*res != -1) return *res;
    *res = max(*res, dfs(cur + 1));
    *res = max(*res, dfs(nxt[cur]) + info[cur].second);
    return *res;
}

int main() {
    FASTIO;
    cin >> n >> s;
    info.resize(n + 1);
    nxt.resize(n + 1);
    dp.resize(n + 1, -1);

    info[0].first = 0; info[0].second = 0;
    for (int i = 1; i <= n; ++i) {
        cin >> info[i].first >> info[i].second;
    }

    sort(info.begin(), info.end(), [](const pll &a, const pll &b) {
        if (a.first != b.first) return a.first < b.first;
        return a.second < b.second;
    });

    fill(nxt.begin(), nxt.end(), n + 1);
    for (int i = 1; i <= n; ++i) {
        pll p = {info[i].first + s, 0};
        nxt[i] = int(lower_bound(info.begin(), info.end(), p) - info.begin());
    }

    cout << dfs(1) << '\n';
    return 0;
}
