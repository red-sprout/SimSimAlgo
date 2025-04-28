#include <iostream>
#include <vector>
#define MAX 1'000'001

using namespace std;

int n;
int v[MAX];
vector<int> arr;
vector<int> score;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n;
    
    arr.resize(n);
    score.resize(n);
    fill(v, v + MAX, -1);
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
        v[arr[i]] = i;
    }

    for (int i = 0; i < n; ++i) {
        for (int j = arr[i] * 2; j < MAX; j += arr[i]) {
            if (v[j] != -1) {
                ++score[i];
                --score[v[j]];
            }
        }
    }

    for (int i = 0; i < n; ++i) {
        cout << score[i] << ' ';
    }
    cout << '\n';
    return 0;
}
