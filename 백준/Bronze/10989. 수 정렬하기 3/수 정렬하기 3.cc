#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n; cin >> n;
    int cnt[10001];
    int num;

    fill(cnt, cnt + 10001, 0);
    for(int i = 0; i < n; ++i) {
        cin >> num;
        ++cnt[num];
    }

    for(int i = 1; i < 10001; ++i) {
        for(int j = 0; j < cnt[i]; ++j) {
            cout << i << '\n';
        }
    }

    return 0;
}
