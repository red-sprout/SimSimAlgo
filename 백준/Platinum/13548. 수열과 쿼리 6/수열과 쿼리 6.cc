#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, m, sq;

struct Query {
    int id, s, e;
    Query() {}
    Query(int _id, int _s, int _e) : id(_id), s(_s), e(_e) {}
    bool operator<(const Query& q) const {
        if (s / sq != q.s / sq) return s / sq < q.s / sq;
        return e < q.e;
    }
};

vector<Query> queries;
int arr[100'001], ans[100'001], frq[100'001], cnt[100'001], max_val;

void add(int x) {
    --cnt[frq[x]];
    ++frq[x];
    ++cnt[frq[x]];
    if (frq[x] > max_val) max_val = frq[x];
}

void remove(int x) {
    --cnt[frq[x]];
    if (frq[x] == max_val && cnt[frq[x]] == 0) --max_val;
    --frq[x];
    ++cnt[frq[x]];
}

int main() {
    FASTIO;
    cin >> n; sq = sqrt(n);
    for (int i = 1; i <= n; ++i)  cin >> arr[i];
    cin >> m;
    queries.resize(m);
    int s, e;
    for (int i = 0; i < m; ++i) {
        cin >> s >> e;
        queries[i] = Query(i, s, e);
    }

    sort(queries.begin(), queries.end());
    s = 1, e = 0;

    for (int i = 0; i < m; i++) {
        int ns = queries[i].s, ne = queries[i].e;
        while (e < ne) add(arr[++e]);
        while (ne < e) remove(arr[e--]);
        while (s < ns) remove(arr[s++]);
        while (ns < s) add(arr[--s]);
        ans[queries[i].id] = max_val;
    }

    for (int i = 0; i < m; i++) cout << ans[i] << '\n';

    return 0;
}
