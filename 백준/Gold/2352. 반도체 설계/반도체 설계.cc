#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n, arr[40000]; cin >> n;
    for (int i = 0; i < n; ++i) cin >> arr[i];
    vector<int> lis;
    for (int i = 0; i < n; ++i) {
        int l = -1, r = lis.size(), m;
        while (l + 1 < r) {
            m = (l + r) / 2;
            if (lis[m] >= arr[i]) r = m;
            else l = m;
        }
        if (r == lis.size()) lis.emplace_back(arr[i]);
        else lis[r] = arr[i];
    }
    cout << lis.size() << '\n';
    return 0;
}
