#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;
typedef pair<int, int> pii;

int main() {
    FASTIO;
    vector<pii> v(9);
    for (int i = 0; i < 9; i++) {
        cin >> v[i].first;
        v[i].second = i + 1;
    }
    sort(v.begin(), v.end(), greater<>());
    cout << v[0].first << '\n' << v[0].second << '\n';
    return 0;
}
