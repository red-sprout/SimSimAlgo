#include <iostream>
#include <vector>

using namespace std;
typedef pair<int, int> pii;

int n;
int depth[100'001];
int energy[100'001];
int parent[17][100'001];
int dist[17][100'001];
vector<pii> g[100'001];

void dfs(int cur, int d) {
    depth[cur] = d;
    for(auto ele : g[cur]) {
        if(depth[ele.first] == 0) {
            parent[0][ele.first] = cur;
            dist[0][ele.first] = ele.second;
            dfs(ele.first, d + 1);
        }
    }
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n;
    for(int i = 1; i <= n; ++i) {
        cin >> energy[i];
    }

    int a, b, c;
    for(int i = 0; i < n - 1; ++i) {
        cin >> a >> b >> c;
        g[a].emplace_back(make_pair(b, c));
        g[b].emplace_back(make_pair(a, c));
    }

    dfs(1, 1);
    for(int i = 1; i < 17; ++i) {
        for(int j = 1; j < 100'001; ++j) {
            parent[i][j] = parent[i - 1][parent[i - 1][j]];
            dist[i][j] = dist[i - 1][parent[i - 1][j]] + dist[i - 1][j];
        }
    }

    int end;
    for(int i = 1; i <= n; ++i) {
        end = i;
        for(int j = 16; j >= 0; --j) {
            if (parent[j][end] != 0 && dist[j][end] <= energy[i]) {
                energy[i] -= dist[j][end];
                end = parent[j][end];
            }            
        }
        cout << end << '\n';
    }

    return 0;
}