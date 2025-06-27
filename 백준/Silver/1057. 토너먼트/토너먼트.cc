#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n, kim, lim; cin >> n >> kim >> lim;
    int cnt = 0;
    while (kim != lim) {
        kim = (kim + 1) / 2;
        lim = (lim + 1) / 2;
        cnt++;
    }
    cout << cnt << '\n';
    return 0;
}
