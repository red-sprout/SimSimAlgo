#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

string s;
vector<pii> p;
set<string> res;

bool selected(int idx, int v) {
    for (int i = 0; i < p.size(); ++i) {
        if (v & (1 << i) && (p[i].first == idx || p[i].second == idx)) return true;
    }
    return false;
}

void dfs(int cur, int v) {
    if (cur == p.size()) {
        if (v == 0) return;
        string ans = "";
        for (int i = 0; i < s.length(); i++) {
            if (selected(i, v)) continue;
            ans += s[i];
        }
        res.emplace(ans);
        return;
    }
    dfs(cur + 1, v);
    dfs(cur + 1, v | (1 << cur));
}

int main() {
    FASTIO;
    cin >> s;
    int top = -1;
    int arr[s.length()];
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == '(') arr[++top] = i;
        else if (s[i] == ')') p.emplace_back(arr[top--], i);
    }
    dfs(0, 0);
    for (auto it = res.begin(); it != res.end(); it++) {
        cout << *it << '\n';
    }
    return 0;
}
