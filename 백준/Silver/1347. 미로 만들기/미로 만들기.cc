#include <iostream>

using namespace std;

const int dr[] = { 1, 0, -1, 0 };
const int dc[] = { 0, -1, 0, 1 };

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    char memo[101][101];
    for(int i = 0; i < 101; ++i) {
        for(int j = 0; j < 101; ++j) {
            memo[i][j] = '#';
        }
    }
    memo[50][50] = '.';
    int n; cin >> n;
    string order; cin >> order;
    int r = 50, c = 50, idx = 0;
    int min_r = 50, min_c = 50, max_r = 50, max_c = 50;
    for(int i = 0; i < n; ++i) {
        if(order[i] == 'R') {
            idx = (idx + 1) % 4;
        } else if (order[i] == 'L') {
            idx = (idx + 3) % 4;
        } else {
            r += dr[idx];
            c += dc[idx];
            min_r = min(min_r, r);
            max_r = max(max_r, r);
            min_c = min(min_c, c);
            max_c = max(max_c, c);
            memo[r][c] = '.';
        }
    }
    for(int i = min_r; i <= max_r; ++i) {
        for(int j = min_c; j <= max_c; ++j) {
            cout << memo[i][j];
        }
        cout << '\n';
    }
    return 0;
}