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
    if (b > 0) {
        bool flag = x < 0;
        if (flag) x = -x;
        while (x != 0) {
            int mod = x % b;
            x = x / b;
            a.emplace_back(mod);
        }
        if (flag) cout << '-';
        while (!a.empty()) {
            cout << a.back();
            a.pop_back();
        }
        cout << '\n';
    } else {
        while (x != 0) {
            int mod = x % b;
            if (mod < 0) mod -= b;
            x = (x - mod) / b;
            a.emplace_back(mod);
        }
        while (!a.empty()) {
            cout << a.back();
            a.pop_back();
        }
        cout << '\n';
    }

    return 0;
}
