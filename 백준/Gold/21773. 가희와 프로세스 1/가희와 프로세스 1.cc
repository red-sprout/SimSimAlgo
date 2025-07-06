#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Process {
    int a, b, c;
    Process() {}
    Process(int _a, int _b, int _c) : a(_a), b(_b), c(_c) {}
};

struct comp {
    bool operator()(Process p1, Process p2) {
        return (p1.c == p2.c) ? p1.a > p2.a : p1.c < p2.c;
    }
};

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    priority_queue<Process, vector<Process>, comp> pq;
    int t, n; cin >> t >> n;
    int a, b, c;
    for(int i = 0; i < n; ++i) {
        cin >> a >> b >> c;
        pq.emplace(a, b, c);
    }
    while(t--) {
        Process p = pq.top(); pq.pop();
        --p.c;
        if(--p.b > 0) pq.emplace(p);
        cout << p.a << '\n';
    }
    return 0;
}