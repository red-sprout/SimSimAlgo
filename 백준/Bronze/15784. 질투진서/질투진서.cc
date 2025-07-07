#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, a, b; cin >> n >> a >> b;
    --a; --b;
    int mp[1000][1000];
    for(int i = 0; i < n; ++i) {
        for(int j = 0; j < n; ++j) {
            cin >> mp[i][j];
        }
    }
    int jin = mp[a][b];
    bool angry = false;
    for(int i = 0; i < n; ++i) {
        if(mp[i][b] > jin || mp[a][i] > jin) {
            angry = true;
            break;
        }
    }
    cout << (angry ? "ANGRY" : "HAPPY") << '\n';
    return 0;
}