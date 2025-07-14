#include <iostream>
#include <vector>
#include <algorithm>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

struct Pos {
    int id, x;
    Pos() {}
    Pos(int id, int x) : id(id), x(x) {}
    bool operator < (const Pos& p) const {
        return x < p.x;
    }
};

vector<Pos> pos;
int ans[1'000'000];

int main() {
    FASTIO;
    int n; cin >> n;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        pos.emplace_back(i, x);
    }
    sort(pos.begin(), pos.end());
    int bf = pos[0].x;
    int idx = 0;
    for (int i = 0; i < n; ++i) {
        Pos p = pos[i];
        if (bf != p.x) {
            bf = p.x;
            ++idx;
        }
        ans[p.id] = idx;
    }
    for (int i = 0; i < n; ++i) {
        cout << ans[i] << ' ';
    }
    cout << '\n';
    return 0;
}
