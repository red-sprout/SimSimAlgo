#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int n, arr[40000]; cin >> n;
    for (int i = 0; i < n; ++i) cin >> arr[i];
    vector<int> lis;
    for (int i = 0; i < n; ++i) {
        auto it = lower_bound(lis.begin(), lis.end(), arr[i]);
        if (it == lis.end()) lis.emplace_back(arr[i]);
        else *it = arr[i];
    }
    cout << lis.size() << '\n';
    return 0;
}
