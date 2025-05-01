#include <iostream>
#include <set>
#define SZ 1'000'001

using namespace std;

bool is_prime[SZ];
set<int> us;

void init() {
    fill(is_prime, is_prime + SZ, true);
    for (int i = 2; i < SZ; ++i) {
        if (is_prime[i]) {
            us.emplace(i);
            for (int j = 2; i * j < SZ; ++j) {
                is_prime[i * j] = false;
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    init();
    int t; cin >> t;
    int n, cnt;
    while (t--) {
        cin >> n;
        cnt = 0;
        for (auto p = us.begin(); p != us.end(); ++p) {
            if (n / 2 < *p) break;
            if (us.find(n - *p) != us.end()) ++cnt;
        }
        cout << cnt << '\n';
    }
    return 0;
}
