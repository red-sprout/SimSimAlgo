#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

bool flag = false;
int n, w[7];

bool helloworld() {
    if (w[0] == 0 || w[4] == 0) return false;
    int hello = w[0] * 10000 + w[1] * 1000 + w[2] * 100 + w[2] * 10 + w[3];
    int world = w[4] * 10000 + w[3] * 1000 + w[5] * 100 + w[2] * 10 + w[6];
    return flag = (hello + world == n);
}

void print() {
    cout << "  " << w[0] << w[1] << w[2] << w[2] << w[3] << '\n';
    cout << "+ " << w[4] << w[3] << w[5] << w[2] << w[6] << '\n';
    cout << "-------\n";
    cout << (n < 100000 ? "  " : " ") << n << '\n';
}

void dfs(int cur, int v) {
    if (flag) return;
    if (cur == 7) {
        if (helloworld()) print();
        return;
    }
    for (int i = 0; i < 10; ++i) {
        if ((v & (1 << i)) != 0) continue;
        w[cur] = i;
        dfs(cur + 1, v | (1 << i));
    }
}

int main() {
    FASTIO;
    cin >> n;
    dfs(0, 0);
    if (!flag) cout << "No Answer\n";
    return 0;
}
