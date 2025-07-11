#include <iostream>
#include <vector>

using namespace std;
typedef long long ll;

int n;
ll memo[21];
bool used[21];

void init() {
    memo[0] = 1;
    for(int i = 1; i <= 20; ++i) {
        memo[i] = memo[i - 1] * i;
    }
}

ll get_num(ll order) {
    for(int i = 1; i <= n; ++i) {
        if(used[i]) continue;
        if(order-- == 0) {
            return i;
        }
    }
    return -1;
}

void cmd1(ll k) {
    for(int i = 0; i < n; ++i) {
        ll order = k / memo[n - i - 1];
        ll num = get_num(order);
        cout << num << ' ';
        used[num] = true;
        k %= memo[n - i - 1];
    }
    cout << '\n';
}

void cmd2(vector<int> v) {
    ll k = 0;
    for(int i = 0; i < n; ++i) {
        ll order = 0;
        for(int j = 1; j < v[i]; ++j) {
            if(!used[j]) ++order;
        }
        k += order * memo[n - i - 1];
        used[v[i]] = true;
    }
    cout << k + 1 << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    init();
    cin >> n;
    int cmd; cin >> cmd;
    if(cmd == 1) {
        ll k; cin >> k;
        cmd1(k - 1);
    } else {
        vector<int> v(n);
        for(int i = 0; i < n; ++i) cin >> v[i];
        cmd2(v);
    }
    return 0;
}