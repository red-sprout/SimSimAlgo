
#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

constexpr int MAX = 100'001;
int p[MAX], par[MAX];
set<int> color[MAX];

struct Query {
    int id, cmd, a;
    Query() {}
    Query(int _id, int _cmd, int _a) : id(_id), cmd(_cmd), a(_a) {}
};

vector<Query> queries;
vector<pii> answers;

int find_par(int x) {
    if (par[x] == x) return x;
    return par[x] = find_par(par[x]);
}

void union_par(int x, int y) {
    x = find_par(x);
    y = find_par(y);
    if (x == y) return;
    if (color[x].size() > color[y].size()) swap(x, y);
    for (auto e : color[x]) color[y].emplace(e);
    par[x] = y;
}

int main() {
    FASTIO;
    int n, q; cin >> n >> q;
    for (int i = 2; i <= n; ++i) cin >> p[i];
    int c;
    for (int i = 1; i <= n; ++i) {
        cin >> c;
        par[i] = i;
        color[i].emplace(c);
    }
    int cmd, a;
    for (int i = 0; i < n - 1 + q; ++i) {
        cin >> cmd >> a;
        queries.emplace_back(i, cmd, a);
    }
    sort(queries.begin(), queries.end(), [](const Query q1, const Query q2) {return q1.id > q2.id;});
    for (auto q : queries) {
        if (q.cmd == 1) {
            union_par(q.a, p[q.a]);
        } else {
            answers.emplace_back(q.id, color[find_par(q.a)].size());
        }
    }
    sort(answers.begin(), answers.end());
    for (auto q : answers) {
        cout << q.second << '\n';
    }
    return 0;
}