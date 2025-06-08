#include <iostream>
#include <map>

using namespace std;

int k;
map<int, int> jbnu;

int get_key(int key) {
    auto mid = jbnu.lower_bound(key);
    auto lo = mid == jbnu.begin() ? mid : prev(mid);
    auto hi = mid == jbnu.end() ? mid : next(mid);
    auto end = jbnu.end();

    int kk, val1 = k + 1, val2 = k + 2, val3 = k + 3;
    val1 = abs((lo->first) - key);
    if(mid != end) val2 = abs((mid->first) - key);
    if(hi != end) val3 = abs((hi->first) - key);
    kk = min(val1, min(val2, val3));

    if(kk > k) return -1;
    if(lo != mid && val1 == val2) return -2;
    if(val1 == kk) return lo->first;
    if(val2 == kk) return mid->first;

    return hi->first;
}

void cmd1(int key, int val) {
    jbnu.emplace(key, val);
}

void cmd2(int key, int val) {
    auto pos = get_key(key);
    if(pos < 0) return;
    jbnu[pos] = val;
}

void cmd3(int key) {
    auto pos = get_key(key);
    if(pos == -1) {
        cout << pos << '\n';
        return;
    }
    if(pos == -2) {
        cout << '?' << '\n';
        return;
    }
    cout << jbnu[pos] << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, m; cin >> n >> m >> k;
    int cmd, key, val;

    while(n--) {
        cin >> key >> val;
        cmd1(key, val);
    }

    while(m--) {
        cin >> cmd;
        if(cmd == 1) {
            cin >> key >> val;
            cmd1(key, val);
        } else if(cmd == 2) {
            cin >> key >> val;
            cmd2(key, val);
        } else {
            cin >> key;
            cmd3(key);
        }
    }
    return 0;
}
