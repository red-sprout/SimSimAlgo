#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, l, r, x;
vector<int> a;

bool validate(int v) {
    int easy = -1, diff = -1, sum = 0;
    for (int i = 0; i < n; i++) {
        if ((v & (1 << i)) == 0) continue;
        if (easy == -1) easy = a[i];
        diff = a[i];
        sum += a[i];
    }
    return l <= sum && sum <= r && diff - easy >= x;
}

int dfs(int cur, int v) {
    if (cur == n) return validate(v);
    int res = 0;
    res += dfs(cur + 1, v);
    res += dfs(cur + 1, v | (1 << cur));
    return res;
}

int main() {
    FASTIO;
    cin >> n >> l >> r >> x;
    a.resize(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    sort(a.begin(), a.end());
    cout << dfs(0, 0) << '\n';
    return 0;
}