#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n, m; cin >> n >> m;
    vector<int> s(m + 1);
    s[0] = 0;
    int idx = 0;
    for(int i = 1; i <= m; ++i) {
        cin >> s[i];
        if(n <= s[i] && s[i] <= 2 * n) {
            idx = i;
        }
    }

    if(idx != 0) {
        cout << 1 << '\n' << idx << '\n';
        return 0;
    }

    int sum = 0;
    vector<int> res;
    for(int i = 1; i <= m; ++i) {
        if(sum >= n) break;
        if(s[i] < n) {
            sum += s[i];
            res.emplace_back(i);
        }
    }

    cout << res.size() << '\n';
    for(auto ele : res) {
        cout << ele << '\n';
    }

    return 0;
}
