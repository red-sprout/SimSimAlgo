#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef long long ll;

int main() {
    FASTIO;
    int n; cin >> n;
    ll sum = 0, a;
    for (int i = 0; i < n; ++i) {
        cin >> a;
        sum = max(a - n + i, sum);
    }
    cout << sum << '\n';
    return 0;
}
