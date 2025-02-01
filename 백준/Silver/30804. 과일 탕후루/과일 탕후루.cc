#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n, s[200000];
    int v[10];

    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> s[i];
    }

    int l = 0, r = 1;
    int kind = 1;
    fill(v, v + 10, 0);
    v[s[l]]++;
    int m = 1;
    while (r < n) {
        if (kind > 2) {
            v[s[l]]--;
            if (v[s[l]] == 0) kind--;
            l++;
        }
        else {
            if (v[s[r]] == 0) kind++;
            v[s[r]]++;
            if(kind <= 2) m = max(m, r - l + 1);
            r++;
        }
    }

    cout << m << '\n';
    return 0;
}
