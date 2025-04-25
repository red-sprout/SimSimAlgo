#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct comp1 {
    bool operator()(int a, int b) {
        return a < b;
    }
};

struct comp2 {
    bool operator()(int a, int b) {
        return a > b;
    }
};

priority_queue<int, vector<int>, comp1> ll;
priority_queue<int, vector<int>, comp2> rr;

void simulation(int m) {
    int sz = (m + 1) / 2;
    int limit, num;
    cout << sz << '\n';
    bool flag = false;
    while (m > 0) {
        limit = (m >= 10 ? 10 : m % 10);
        for (int i = 1; i <= limit; ++i) {
            cin >> num;
            while(rr.top() < num) {
                ll.emplace(rr.top());
                rr.pop();
            }
            ll.emplace(num);
            while(ll.size() > rr.size() + 1) {
                rr.emplace(ll.top());
                ll.pop();
            }
            // cout << ll.top() << ' ' << rr.top() << '\n';
            if(i % 2) {
                cout << ll.top();
            } else {
                cout << ' ';
            }
        }
        if(flag) {
            cout << '\n';
            flag = false;
        } else {
            flag = true;
        }
        m -= 10;
    }
    if(flag) cout << '\n';
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int t; cin >> t;
    while (t--) {
        int m; cin >> m;
        while(!ll.empty()) ll.pop();
        while(!rr.empty()) rr.pop();
        ll.emplace(INT32_MIN);
        rr.emplace(INT32_MAX);
        simulation(m);
    }
    return 0;
}