#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int arr[5];
    for(int i = 0; i < 5; ++i) {
        cin >> arr[i];
    }
    cout << (arr[0] + arr[1] + arr[2] + arr[3] + arr[4]) / 5 << '\n';
    sort(arr, arr + 5);
    cout << arr[2] << '\n';
    return 0;
}