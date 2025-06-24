#include <bits/stdc++.h>

using namespace std;

struct Point {
    int id, x, y;
    Point() {}
    Point(int id, int x, int y) : id(id), x(x), y(y) {}
};

bool operator<(const Point &a, const Point &b) {
    return a.x == b.x ? a.y < b.y : a.x < b.x;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    int n; cin >> n;
    vector<Point> points(n);
    for (int i = 0; i < n; i++) {
        points[i].id = i + 1;
        cin >> points[i].x >> points[i].y;
    }
    sort(points.begin(), points.end());
    for (int i = 0; i < n - 1; i++) {
        cout << points[i].id << ' ' << points[i + 1].id << '\n';
    }
    return 0;
}