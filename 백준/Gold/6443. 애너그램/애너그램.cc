#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
int main() {
    FASTIO;
    int n; string s;
    cin >> n;
    while (n--) {
        cin >> s;
        vector<char> v(s.begin(), s.end());
        sort(v.begin(), v.end());
        do {
            for (char c : v) cout << c;
            cout << '\n';
        } while (next_permutation(v.begin(), v.end()));
    }
    return 0;
}