#include <iostream>
#include <algorithm>
#define MAX 1000000001

using namespace std;
typedef long long ll;

int n, m;
ll sum;
ll v[101010];

void init() {
    cin >> n >> m;
    for(int i = 0; i < n; i++) {
        cin >> v[i];
    }
}

bool compare(int i, int j) {
    return j < i;
}

void solution() {
    sum = 0;
    for(int i = 0; i < n; i++) sum += v[i];
    sort(v, v + n, compare);
    int idx = 0;
    ll tmpSum = 0;
    while(tmpSum + v[idx] < (sum + 1) / 2) {
        tmpSum += v[idx++];
    }
    cout << m / (idx + 2) << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    init();
    solution();
    return 0;
}