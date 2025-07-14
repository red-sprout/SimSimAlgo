#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, m, sq;

struct Query {
    int id, s, e;
    Query() {}
    Query(int id, int s, int e) : id(id), s(s), e(e) {}
    bool operator < (const Query& p) const {
        if (s / sq != p.s / sq) return s / sq < p.s / sq;
        return e < p.e;
    }
};

vector<Query> queries;
int arr[100'001];
int ans[100'001];
int c, cnt[1'000'001];

int main() {
    FASTIO;
    cin >> n; sq = sqrt(n);
    for (int i = 1; i <= n; ++i) {
        cin >> arr[i];
    }
    cin >> m;
    int s, e;
    for (int i = 0; i < m; ++i) {
        cin >> s >> e;
        queries.emplace_back(i, s, e);
    }
    sort(queries.begin(), queries.end());
    s = queries[0].s;
    e = queries[0].e;
    for (int i = s; i <= e; ++i) if (cnt[arr[i]]++ == 0) ++c;
    ans[queries[0].id] = c;
    for (int i = 1; i < m; ++i) {
        int ns = queries[i].s;
        int ne = queries[i].e;
        while (s < ns) if (--cnt[arr[s++]] == 0) --c;
        while (ns < s) if (cnt[arr[--s]]++ == 0) ++c;
        while (e < ne) if (cnt[arr[++e]]++ == 0) ++c;
        while (ne < e) if (--cnt[arr[e--]] == 0) --c;
        ans[queries[i].id] = c;
    }
    for (int i = 0; i < m; ++i) {
        cout << ans[i] << '\n';
    }
    return 0;
}
