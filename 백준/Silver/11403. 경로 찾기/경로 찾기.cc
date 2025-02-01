#include <iostream>

using namespace std;

const int MAX = 1'000'000'000;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    int n, dist[100][100];
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> dist[i][j];
            if (dist[i][j] == 0) dist[i][j] = MAX;
        }
    }

    for (int k = 0; k < n; ++k) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cout << ((dist[i][j] != MAX) ? 1 : 0) << ' ';
        }
        cout << '\n';
    }

    return 0;
}
