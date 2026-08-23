#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

bool flag = false;

void dfs(int x, int d) {
    if(flag) return;
    if(d == 3) {
        if(x == 0) flag = true;
        return;
    }
    for(int i = 1; i <= 6; ++i) {
        dfs(x - i, d + 1);
    }
}

int main() {
    FASTIO;
    int x;
    cin >> x;
    dfs(x, 0);
    cout << (flag ? "Yes" : "No") << '\n';
    return 0;
}
