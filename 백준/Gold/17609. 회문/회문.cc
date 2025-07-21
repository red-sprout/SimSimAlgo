#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

bool check(string s) {
    int l = 0, r = s.length() - 1;
    while (l <= r) {
        if (s[l++] != s[r--]) return false;
    }
    return true;
}

int solution(string s) {
    int l = 0, r = s.length() - 1;
    while (l <= r) {
        if (s[l] != s[r]) {
            string ll = s.substr(l, r - l);
            if (check(ll)) return 1;
            string rr = s.substr(l + 1, r - l);
            if (check(rr)) return 1;
            return 2;
        }
        ++l; --r;
    }
    return 0;
}

int main() {
    FASTIO;
    int t; cin >> t;
    string s;
    while (t--) {
        cin >> s;
        cout << solution(s) << '\n';
    }
    return 0;
}
