#include <bits/stdc++.h>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
    int n; cin >> n;
    int l = 1, r = n;
    for (int i = 0; i < n; i++) {
        if (i % 2 != 0) {
            cout << l++ << ' ';
        } else {
            cout << r-- << ' ';
        }
    }
    cout << '\n';
    return 0;
}