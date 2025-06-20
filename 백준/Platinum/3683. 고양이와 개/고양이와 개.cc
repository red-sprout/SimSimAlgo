#include <bits/stdc++.h>

using namespace std;

vector<int> g[555];

int par[555];
bool vis[555];

struct Info {
    int c, d;
    bool op_cat;
    Info() {}
    Info(int c, int d, bool op_cat) : c(c), d(d), op_cat(op_cat) {}
};

int get_num(string s) {
    int res = 0;
    for (int i = 1; i < s.length(); i++) {
        res *= 10;
        res += s[i] - '0';
    }
    return res;
}

bool dfs(int cur) {
    for (int nxt : g[cur]) {
        if (!vis[nxt]) {
            vis[nxt] = true;
            if (par[nxt] == -1 || dfs(par[nxt])) {
                par[nxt] = cur;
                return true;
            }
        }
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    int test; cin >> test;
    while (test--) {
        for (int i = 0; i < 555; i++) g[i].clear();
        int c, d, v; cin >> c >> d >> v;
        Info info[555];
        for (int i = 1; i <= v; i++) {
            string a, b; cin >> a >> b;
            if (a[0] == 'C') info[i] = Info(get_num(a), get_num(b), false);
            else info[i] = Info(get_num(b), get_num(a), true);
        }

        for (int i = 1; i <= v; i++) {
            for (int j = i + 1; j <= v; j++) {
                if (info[i].op_cat == info[j].op_cat) continue;
                if (info[i].c == info[j].c || info[i].d == info[j].d) {
                    if (!info[i].op_cat) g[i].emplace_back(j);
                    else g[j].emplace_back(i);
                }
            }
        }

        int res = 0;
        memset(par, -1, sizeof(par));
        for (int i = 1; i <= v; i++) {
            if (info[i].op_cat) continue;
            memset(vis, 0, sizeof(vis));
            res += dfs(i);
        }

        cout << v - res << '\n';
    }

    return 0;
}