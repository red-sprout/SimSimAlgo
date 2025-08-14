#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    queue<int> q;
    int n, num; cin >> n;
    for (int i = 1; i <= n; ++i) {
        q.emplace(i);
    }
    while (1) {
        num = q.front(); q.pop();
        if (q.empty()) break;
        num = q.front(); q.pop();
        q.emplace(num);
    }
    cout << num << '\n';
    return 0;
}
