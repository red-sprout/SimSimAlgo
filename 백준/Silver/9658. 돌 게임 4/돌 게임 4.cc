#include <iostream>

using namespace std;

bool arr[1001];

void init() {
    arr[2] = arr[4] = true;
    for(int i = 5; i < 1001; ++i) {
        arr[i] = !(arr[i - 1] && arr[i - 3] && arr[i - 4]);
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n; cin >> n;
    init();
    cout << (arr[n] ? "SK" : "CY") << '\n';
    return 0;
}
