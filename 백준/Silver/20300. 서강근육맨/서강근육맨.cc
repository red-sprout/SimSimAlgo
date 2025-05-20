#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef long long ll;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n; cin >> n;
    vector<ll> arr(n);

    for(int i = 0; i < n; ++i) {
        cin >> arr[i];
    }

    sort(arr.begin(), arr.end());

    ll min = 0, m;
    if(n % 2) {
        min = arr[n - 1];
        --n;
    }

    for (int i = 0; i < n / 2; i++) {
        m = arr[i] + arr[(n - 1) - i];
        if (m > min) min = m;
    }

    cout << min;
    return 0;
}
