#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int t; cin >> t;
    string res = "";
    while(t) {
        res = char(t % 9 + '0') + res;
        t /= 9;
    }
    cout << res << '\n';
    return 0;
}