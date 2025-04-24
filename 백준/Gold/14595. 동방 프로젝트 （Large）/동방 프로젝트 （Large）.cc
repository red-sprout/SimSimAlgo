#include <iostream>
#include <cmath>

using namespace std;

int n, m;
int parent[1'000'001];

int get(int x) {
    if(parent[x] == x) return x;
    return parent[x] = get(parent[x]);
}

void operation(int x, int y) {
    x = get(x);
    y = get(y);
    if(x == y) return;
    parent[x] = y;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> n >> m;
    int x, y;
    for(int i = 1; i <= n; ++i) {
        parent[i] = i;
    }
    int cnt = n;
    for(int i = 0; i < m; ++i) {
        cin >> x >> y;
        while(get(x) != get(y)) {
            --cnt;
            int nxt = get(x) + 1;
            operation(x, nxt);
            x = nxt;
        }
    }
    cout << cnt << '\n';
    return 0;
}