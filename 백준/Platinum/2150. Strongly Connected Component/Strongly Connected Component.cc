#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>
#include <cstring>

using namespace std;
const int sz = 10'005;

int v, e, id;
int p[sz];
bool finished[sz];
vector<int> g[sz], scc[sz];
stack<int> stk;
int scc_cnt;

int dfs(int cur) {
    p[cur] = ++id;
    stk.push(cur);
    int par = p[cur];

    for (int nxt : g[cur]) {
        if (p[nxt] == 0) par = min(par, dfs(nxt));
        else if (!finished[nxt]) par = min(par, p[nxt]);
    }
    
    if (par == p[cur]) {
        while (1) {
            int t = stk.top(); stk.pop();
            finished[t] = 1;
            scc[scc_cnt].emplace_back(t);
            if (t == cur) break;
        }
        sort(scc[scc_cnt].begin(), scc[scc_cnt].end());
        ++scc_cnt;
    }

    return par;
}

int comp(vector<int> a, vector<int> b) {
    return a[0] < b[0];
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> v >> e;
    for (int i = 0; i < e; ++i) {
        int a, b;
        cin >> a >> b;
        g[a].emplace_back(b);
    }

    for (int i = 1; i <= v; ++i) {
        if (p[i] == 0) dfs(i);
    }

    vector<vector<int>> res(scc, scc + scc_cnt);
    sort(res.begin(), res.end(), comp);

    cout << scc_cnt << '\n';
    for (auto& s : res) {
        for (int x : s) cout << x << ' ';
        cout << -1 << '\n';
    }
    return 0;
}
