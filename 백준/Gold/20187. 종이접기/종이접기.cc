#include <iostream>
#include <cmath>
#include <unordered_map>
#include <vector>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int k, n;
vector<char> paper[1 << 8][1 << 8];

void fill_r(int cur, int target, int idx, char c) {
    if (cur == target) {
        if (c == '.') return;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 1 << target; ++j) {
                paper[i][idx + j].emplace_back(c);
            }
        }
        return;
    }
    int range = 1 << (cur - 1);
    fill_r(cur - 1, target, idx, 'R');
    fill_r(cur - 1, target, idx + range, '.');
}

void fill_l(int cur, int target, int idx, char c) {
    if (cur == target) {
        if (c == '.') return;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 1 << target; ++j) {
                paper[i][idx + j].emplace_back(c);
            }
        }
        return;
    }
    int range = 1 << (cur - 1);
    fill_l(cur - 1, target, idx, '.');
    fill_l(cur - 1, target, idx + range, 'L');
}

void fill_d(int cur, int target, int idx, char c) {
    if (cur == target) {
        if (c == '.') return;
        for (int i = 0; i < 1 << target; ++i) {
            for (int j = 0; j < n; ++j) {
                paper[idx + i][j].emplace_back(c);
            }
        }
        return;
    }
    int range = 1 << (cur - 1);
    fill_d(cur - 1, target, idx, 'D');
    fill_d(cur - 1, target, idx + range, '.');
}

void fill_u(int cur, int target, int idx, char c) {
    if (cur == target) {
        if (c == '.') return;
        for (int i = 0; i < 1 << target; ++i) {
            for (int j = 0; j < n; ++j) {
                paper[idx + i][j].emplace_back(c);
            }
        }
        return;
    }
    int range = 1 << (cur - 1);
    fill_u(cur - 1, target, idx, '.');
    fill_u(cur - 1, target, idx + range, 'U');
}

int get_res(int r, int c, int hole) {
    int arr[] = {0, 1, 2, 3};
    for (auto ele : paper[r][c]) {
        switch (ele) {
            case 'R':
            case 'L':
                swap(arr[0], arr[1]);
                swap(arr[2], arr[3]);
                break;
            case 'D':
            case 'U':
                swap(arr[0], arr[2]);
                swap(arr[1], arr[3]);
                break;
        }
    }
    return arr[hole];
}

int main() {
    FASTIO;
    cin >> k; n = 1 << k;

    char c;
    unordered_map<char, int> cnt;
    for (int i = 0; i < n; ++i) {
        cin >> c;
        cnt[c] += 1;
    }

    for (auto e : cnt) {
        switch (e.first) {
            case 'R':
                fill_r(k, k - e.second, 0, 'R');
                break;
            case 'L':
                fill_l(k, k - e.second, 0, 'L');
                break;
            case 'D':
                fill_d(k, k - e.second, 0, 'D');
                break;
            case 'U':
                fill_u(k, k - e.second, 0, 'U');
                break;
        }
    }

    int hole; cin >> hole;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cout << get_res(i, j, hole) << ' ';
        }
        cout << '\n';
    }

    return 0;
}