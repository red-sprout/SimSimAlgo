#include <bits/stdc++.h>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    int s, k, h; cin >> s >> k >> h;
    if (s + k + h >= 100) {
        cout << "OK\n";
        return 0;
    }

    int m = min(s, min(k, h));
    if (m == s) {
        cout << "Soongsil\n";
    } else if (m == k) {
        cout << "Korea\n";
    } else if (m == h) {
        cout << "Hanyang\n";
    }

    return 0;
}