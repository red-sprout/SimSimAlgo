#include <iostream>

using namespace std;

int n;
int a[8], b[8];
bool v[8];

int calc() {
    int res = 0;
    for(int i = 0; i < n - 1; ++i) {
        res += abs(b[i] - b[i + 1]);
    }
    return res;
}

int dfs(int cur) {
    if(cur == n) {
        return calc();
    }

    int res = -1e9;
    for(int i = 0; i < n; ++i) {
        if(!v[i]) {
            v[i] = true;
            b[cur] = a[i];
            res = max(res, dfs(cur + 1));
            v[i] = false;
        }
    }

    return res;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    for(int i = 0; i < n; ++i) {
        cin >> a[i];
    }
    int b[8];
    cout << dfs(0) << '\n';
    return 0;
}
