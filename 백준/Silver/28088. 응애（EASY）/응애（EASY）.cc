#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n, m, k; cin >> n >> m >> k;
    vector<int> start(m);
    bool v[201], tmp_v[201];
    fill(v, v + 201, false);
    for(int i = 0; i < m; ++i) {
        cin >> start[i];
        v[start[i]] = true;
    }
    
    for(int i = 0; i < k; ++i) {
        fill(tmp_v, tmp_v + 201, false);
        for(int j = 0; j < n; ++j) {
            tmp_v[j] = v[(j + 1) % n] ^ v[(j + n - 1) % n];
        }
        for(int j = 0; j < n; ++j) {
            v[j] = tmp_v[j];
        }
    }

    int res = 0;
    for(int i = 0; i < n; ++i) {
        if(v[i]) ++res;
    }

    cout << res << '\n';
    return 0;
}
