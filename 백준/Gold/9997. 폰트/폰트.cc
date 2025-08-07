#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n;
vector<int> words;

int dfs(int cur, int vis) {
    if (cur == n) return vis == (1 << 26) - 1;
    return dfs(cur + 1, vis) + dfs(cur + 1, vis | words[cur]);
}

int main() {
    FASTIO;
    cin >> n;
    int w; string s;
    for (int i = 0; i < n; ++i) {
        w = 0; cin >> s;
        for (int j = 0; j < s.length(); ++j) {
            w |= 1 << (s[j] - 'a');
        }
        words.emplace_back(w);
    }
    cout << dfs(0, 0) << '\n';
    return 0;
}
