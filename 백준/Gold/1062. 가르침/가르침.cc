#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
constexpr int acint = 1 << ('a' - 'a') | 1 << ('c' - 'a') | 1 << ('i' - 'a') | 1 << ('n' - 'a') | 1 << ('t' - 'a');
vector<int> v;
int res = 0;

void dfs(int cur, int p, int k, vector<int> check) {
    if (cur == check.size()) {
        if (k != 0) return;
        int cnt = 0;
        for (auto ele : v) {
            if ((ele & p) == ele) ++cnt;
        }
        res = max(res, cnt);
        return;
    }
    dfs(cur + 1, p, k, check);
    dfs(cur + 1, p | (1 << check[cur]), k - 1, check);
}

int solution(int n, int k) {
    int p = 0;
    vector<int> check;
    for (auto ele : v) p |= ele;
    for (int i = 0; i < 26; i++) {
        if (p & (1 << i)) {
            check.emplace_back(i);
        }
    }
    if (check.size() <= k) return n;
    dfs(0, 0, k, check);
    return res;
}

int main() {
    FASTIO;
    int n, k; cin >> n >> k;
    v.resize(n);
    for (int i = 0; i < n; i++) {
        string s; cin >> s;
        v[i] = 0;
        for (int j = 0; j < s.length(); j++) {
            v[i] |= 1 << (s[j] - 'a');
        }
        v[i] ^= acint;
    }
    k -= 5;
    if (k < 0) {
        cout << 0 << '\n';
    } else {
        cout << solution(n, k) << '\n';
    }
    return 0;
}
