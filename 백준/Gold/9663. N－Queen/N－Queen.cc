#include <iostream>
using namespace std;

int N, cnt;

void nqueen(int row, int col_mask, int diag1_mask, int diag2_mask) {
    if (row == N) {
        cnt++;
        return;
    }
    int available = ((1 << N) - 1) & ~(col_mask | diag1_mask | diag2_mask);
    while (available) {
        int col = available & -available;
        nqueen(row + 1, col_mask | col, (diag1_mask | col) << 1, (diag2_mask | col) >> 1);
        available &= (available - 1);
    }
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N;
    cnt = 0;
    nqueen(0, 0, 0, 0);
    cout << cnt << '\n';
    return 0;
}