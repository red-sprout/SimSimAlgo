#include <iostream>

using namespace std;
typedef long long ll;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int l = 1;
    ll k, sz = 2;
    cin >> k;
    while(k > sz) {
        k -= sz;
        sz <<= 1;
        ++l;
    }
    string str = "";
    for(int i = 0; i < l; i++) {
        sz >>= 1;
        if(k > sz) {
            k -= sz;
            str += "7";
        } else {
            str += "4";
        }
    }
    cout << str << '\n';
    return 0;
}