#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, arr[50];
bool used[50];

void init() {
    pair<int, int> parr[50];
    for (int i = 0; i < n; ++i) {
        parr[i].first = arr[i];
        parr[i].second = i;
    }
    sort(parr, parr + n);
    for (int i = 0; i < n; ++i) arr[parr[i].second] = i;
}

int main() {
    FASTIO;
    cin >> n;
    for (int i = 0; i < n; ++i) cin >> arr[i];
    init(); fill(used, used + n, false);
    int cnt = 0;
    for (int i = 0; i < n; ++i) {
        used[arr[i]] = true;
        if (arr[i] < n - 1 && used[arr[i] + 1]) continue;
        if (arr[i] > 0 && used[arr[i] - 1]) continue;
        ++cnt;
    }
    cout << cnt << '\n';
    return 0;
}
