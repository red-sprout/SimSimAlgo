#include <string>
#include <vector>
#include <iostream>
#include <unordered_set>

using namespace std;

int n;
vector<int> tree[300'001];
int sale[300'001];
int depth[300'001];
unordered_set<int> team[300'001];
bool t[300'001];
bool v[300'001];

void dfs1(int cur, int d) {
    depth[cur] = d;
    if(tree[cur].empty()) return;
    team[cur].insert(cur);
    for(int nxt : tree[cur]) {
        dfs1(nxt, d + 1);
        team[nxt].insert(cur);
    }
}

// int dfs2(int cur) {
    
// }

void init(vector<int> sales, vector<vector<int>> links) {
    n = sales.size();
    for(int i = 0; i < n; ++i) {
        sale[i + 1] = sales[i];
    }
    for(vector<int> l : links) {
        tree[l[0]].emplace_back(l[1]);
    }
}

void print() {
    for(int i = 1; i <= n; ++i) {
        for(auto ele = team[i].begin(); ele != team[i].end(); ++ele) {
            cout << *ele << ' ';
        }
        cout << '\n';
    }
}

int solution(vector<int> sales, vector<vector<int>> links) {
    init(sales, links);
    dfs1(1, 1);
    print();
    // dfs2();
    return 0;
}