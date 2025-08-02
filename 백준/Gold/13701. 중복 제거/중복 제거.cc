#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    bitset<1 << 25> s;
    for (int i = 0; cin >> i;) {
        if (!s[i]) {
            s[i] = 1;
            cout << i << " ";
        }
    }
    cout << '\n';
    return 0;
}
