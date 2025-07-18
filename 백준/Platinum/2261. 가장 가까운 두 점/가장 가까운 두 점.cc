#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

int n;
vector<pii> v;

int dist(pii p1, pii p2) {
    return (p1.first - p2.first) * (p1.first - p2.first) + (p1.second - p2.second) * (p1.second - p2.second);
}

int divide(int s, int e) {
    int res = 1e9;
    if (e - s < 3) {
        for (int i = s; i <= e; i++) {
            for (int j = i + 1; j <= e; j++) {
                res = min(res, dist(v[i], v[j]));
            }
        }
        return res;
    }

    int mid = (s + e) / 2;
    int dl = divide(s, mid);
    int dr = divide(mid, e);
    int d = min(dl, dr);

    if (d == 0) return 0;

    vector<pii> area;
    for (int i = s; i <= e; i++) {
        int dx = v[mid].first - v[i].first;
        if (dx * dx < d) {
            area.emplace_back(v[i]);
        }
    }

    res = d;
    sort(area.begin(), area.end(), [](const pii& p1, const pii& p2) {
        return (p1.second != p2.second) ? (p1.second < p2.second) : (p1.first < p2.first);
    });

    for (int i = 0; i < area.size(); ++i) {
        for (int j = i + 1; j < area.size(); ++j) {
            int dy = abs(area[i].second - area[j].second);
            if (dy * dy < d) {
                res = min(res, dist(area[i], area[j]));
            } else {
                break;
            }
        }
    }

    return res;
}

int main() {
    FASTIO;
    cin >> n; v.resize(n);
    for (int i = 0; i < n; ++i) {
        cin >> v[i].first >> v[i].second;
    }
    sort(v.begin(), v.end());
    cout << divide(0, n - 1) << '\n';
    return 0;
}