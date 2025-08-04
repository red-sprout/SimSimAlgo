#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int sq;

struct Query {
    int id, x, y;
    Query() {}
    Query(int _id, int _x, int _y) : id(_id), x(_x), y(_y) {}
    bool operator<(const Query& q) const {
        if (x / sq != q.x / sq) return x / sq < q.x / sq;
        return y < q.y;
    };
};

int main() {
    FASTIO;
    int n, q; cin >> n >> q;
    int arr[n + 1];
    for (int i = 1; i <= n; ++i) cin >> arr[i];
    sq = sqrt(n);

    vector<Query> queries;
    int res[q], x, y;
    for (int i = 0; i < q; ++i) {
        cin >> x >> y;
        queries.emplace_back(i, x, y);
    }

    sort(queries.begin(), queries.end());

    x = queries[0].x;
    y = queries[0].y;
    int cnt = 0, cnt_arr[100'001];
    fill(cnt_arr, cnt_arr + 100'001, 0);
    for (int i = x; i <= y; ++i) {
        if (++cnt_arr[arr[i]] == 3) ++cnt;
    }

    res[queries[0].id] = cnt;
    for (int i = 1; i < q; ++i) {
        while (x < queries[i].x) if (cnt_arr[arr[x++]]-- == 3) --cnt;
        while (x > queries[i].x) if (++cnt_arr[arr[--x]] == 3) ++cnt;
        while (y < queries[i].y) if (++cnt_arr[arr[++y]] == 3) ++cnt;
        while (y > queries[i].y) if (cnt_arr[arr[y--]]-- == 3) --cnt;
        res[queries[i].id] = cnt;
    }

    for (int i = 0; i < q; ++i) cout << res[i] << '\n';
    return 0;
}
