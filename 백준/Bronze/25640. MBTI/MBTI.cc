#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    string s; cin >> s;
    int n, cnt = 0; cin >> n;
    for (int i = 0; i < n; i++) {
        string nm; cin >> nm;
        if (nm == s) {
            cnt++;
        }
    }
    cout << cnt << '\n';
    return 0;
}
