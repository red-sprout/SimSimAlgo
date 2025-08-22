#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, k;
string s;

int simul(bool isL) {
    int cnt = 1;
    for (int i = 0; i < s.length(); ++i) {
        if (isL) {
            if (s[i] == 'L') isL = !isL;
            else ++cnt;
        } else {
            if (s[i] == 'R') isL = !isL;
            else ++cnt;
        }
    }
    return cnt;
}

int main() {
    FASTIO;
    cin >> n >> k;
    cin >> s;
    int cnt = simul(true);
    cout << max(k - cnt + 1, 0) << '\n';
    return 0;
}
