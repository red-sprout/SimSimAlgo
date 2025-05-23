#include <iostream>

using namespace std;

int calc(int d) {
    int res = 0;
    while(res * res + res <= d) {
        ++res;
    }
    return res - 1;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int t; cin >> t;
    int d;
    while(t--) {
        cin >> d;
        cout << calc(d) << '\n';
    }
    return 0;
}
