#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int main() {
    FASTIO;
    int n, x, y;
    vector<int> xpy, xmy;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        xpy.emplace_back(x + y);
        xmy.emplace_back(x - y);
    }
    sort(xpy.begin(), xpy.end());
    sort(xmy.begin(), xmy.end());
    cout << max(xpy[n - 1] - xpy[0], xmy[n - 1] - xmy[0]) << '\n';
    return 0;
}
