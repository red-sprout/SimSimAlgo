#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int r, c; cin >> r >> c;
    int rg, cg, rp, cp; cin >> rg >> cg >> rp >> cp;
    char mp[100][100];
    pair<int, int> p = { -1, -1 };
    for(int i = 0; i < r; ++i) {
        for(int j = 0; j < c; ++j) {
            cin >> mp[i][j];
            if(mp[i][j] == 'P' && p.first == -1) {
                p.first = i; p.second = j;
            }
        }
    }
    bool flag = false;
    for(int i = p.first; i < p.first + rp; ++i) {
        for(int j = p.second; j < p.second + cp; ++j) {
            if(mp[i][j] != 'P') {
                flag = true;
                break;
            }
        }
    }
    cout << flag << '\n';
    return 0;
}