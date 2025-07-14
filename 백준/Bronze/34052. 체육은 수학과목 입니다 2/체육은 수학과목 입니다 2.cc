#include <iostream>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int main() {
    FASTIO;
    int sum = 0, t;
    for (int i = 0; i < 4; ++i) {
        cin >> t;
        sum += t;
    }
    cout << (sum <= 1500 ? "Yes\n" : "No\n");
    return 0;
}
