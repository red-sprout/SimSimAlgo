#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

double cnt = 0;
int dice[3][6];

void dfs(int d, int v) {
    if(d == 3) {
        if((v & (1 << 4)) != 0 && (v & (1 << 5)) != 0 && (v & (1 << 6)) != 0) {
            ++cnt;
        }
        return;
    }
    for(int i = 0; i < 6; ++i) {
        dfs(d + 1, v | (1 << dice[d][i]));
    }
}

int main() {
    FASTIO;
    for(int i = 0; i < 3; ++i) {
        for(int j = 0; j < 6; ++j) {
            cin >> dice[i][j];
        } 
    }
    dfs(0, 0);
    cout << (cnt / (6 * 6 * 6)) << '\n';
    return 0;
}
