#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int x, b; cin >> x >> b;
    if (x == 0) {
        cout << 0 << '\n';
        return 0;
    }
    deque<int> a;
    bool flag = x < 0 && b > 0;
    if (flag) x = -x;
    int pb = abs(b);
    while (x != 0) {
        int mod = x % b;
        if (mod < 0) mod += pb;
        x = (x - mod) / b;
        a.emplace_back(mod);
    }
    if (flag) cout << '-';
    while (!a.empty()) {
        cout << a.back();
        a.pop_back();
    }
    cout << '\n';
    return 0;
}
