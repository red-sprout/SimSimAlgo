#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef pair<int, int> pii;

int n, m, cnt;
bool v[501];
vector<int> g[501];
int parent[501];

int find_parent(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find_parent(parent[x]);
}

void union_parent(int x, int y) {
    x = find_parent(x);
    y = find_parent(y);
    if (x < y) {
        parent[y] = x;
    }
    else if (x > y) {
        parent[x] = y;
    }
    else {
        parent[x] = 0;
        parent[y] = 0;
    }
}

void print(int cases) {
    cout << "Case " << cases << ": ";
    if (cnt > 1) {
        cout << "A forest of " << cnt << " trees." << '\n';
    }
    else if (cnt == 1) {
        cout << "There is one tree." << '\n';
    }
    else {
        cout << "No trees." << '\n';
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int cases = 0;
    while (1) {
        cin >> n >> m;
        if (n == 0 && m == 0) break;

        int i, a, b;
        cnt = 0;
        fill(v + 1, v + n + 1, false);
        for (i = 1; i <= n; ++i) {
            g[i].clear();
            parent[i] = i;
        }

        for (i = 0; i < m; ++i) {
            cin >> a >> b;
            union_parent(a, b);
        }
        
        for (i = 1; i <= n; i++) {
            if (parent[i] == i) ++cnt;
        }

        print(++cases);
    }
    return 0;
}