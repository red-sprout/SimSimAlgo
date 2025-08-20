#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    string s;
    map<char, string> mp;
    mp[' '] = "%20";
    mp['!'] = "%21";
    mp['$'] = "%24";
    mp['%'] = "%25";
    mp['('] = "%28";
    mp[')'] = "%29";
    mp['*'] = "%2a";
    while (1) {
        getline(cin, s);
        if (s == "#") break;
        for (int i = 0; i < s.length(); i++) {
            if (mp.find(s[i]) == mp.end()) cout << s[i];
            else cout << mp[s[i]];
        }
        cout << '\n';
    }
    return 0;
}
