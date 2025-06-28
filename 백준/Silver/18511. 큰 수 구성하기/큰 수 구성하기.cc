#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int n, k, l, res;
vector<int> arr;
vector<int> num;

void dfs(int cur) {
    if (cur == l) {
        int tmp = 0;
        for (auto i : num) {
            tmp *= 10;
            tmp += i;
            if (n >= tmp && res < tmp) res = tmp;
        }
        return;
    }
    for (auto i : arr) {
        num[cur] = i;
        dfs(cur + 1);
        num[cur] = -1;
    }
}

int main() {
    FASTIO;
    cin >> n >> k;
    l = to_string(n).length();
    arr.resize(k);
    for (int i = 0; i < k; i++) {
        cin >> arr[i];
    }
    num.resize(l);
    for (int i = 0; i < l; i++) {
        num[i] = -1;
    }
    res = 0;
    dfs(0);
    cout << res << '\n';
    return 0;
}
