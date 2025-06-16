#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge {
    char c;
    int f, t;
    Edge() {}
    Edge(int _c, int _f, int _t) : c(_c), f(_f), t(_t) {}
};

bool compR(Edge e1, Edge e2) {
    return e1.c > e2.c;
}

bool compB(Edge e1, Edge e2) {
    return e1.c < e2.c;
}

int p[1001];
vector<Edge> rv, bv;

int find_p(int x) { return x == p[x] ? x : p[x] = find_p(p[x]); }
void union_p(int x, int y) { ((x > y) ? p[x] : p[y]) = ((x > y) ? y : x); }

int rst(int n) {
    for(int i = 1; i <= n; ++i) p[i] = i;
    sort(rv.begin(), rv.end(), compR);
    int cnt = 0;
    for(auto e : rv) {
        int pf = find_p(e.f);
        int pt = find_p(e.t);
        if(pf != pt) {
            union_p(pf, pt);
            if(e.c == 'B') ++cnt;
        }
    }
    return cnt;
}

int bst(int n) {
    for(int i = 1; i <= n; ++i) p[i] = i;
    sort(bv.begin(), bv.end(), compB);
    int cnt = 0;
    for(auto e : bv) {
        int pf = find_p(e.f);
        int pt = find_p(e.t);
        if(pf != pt) {
            union_p(pf, pt);
            if(e.c == 'B') ++cnt;
        }
    }
    return cnt;
}

bool st(int n, int m, int k) {
    char c; int f, t;
    rv.clear(); bv.clear();
    rv.resize(m); bv.resize(m);
    for(int i = 0; i < m; ++i) {
        cin >> c >> f >> t;
        Edge e(c, f, t);
        rv[i] = e;
        bv[i] = e;
    }
    int minb = rst(n);
    int maxb = bst(n);
    return minb <= k && k <= maxb;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, m, k;
    while(1) {
        cin >> n >> m >> k;
        if(n == 0 && m == 0 && k == 0) break;
        cout << st(n, m, k) << '\n';
    }
    return 0;
}