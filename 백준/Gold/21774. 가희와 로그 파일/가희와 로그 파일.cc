#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n, q; cin >> n >> q; cin.ignore();

    int lv, idx;
    string l, t; 
    vector<string> logs[7];
    for(int i = 0; i < n; ++i) {
        getline(cin, l);

        idx = l.find('#');
        t = l.substr(0, idx);
        lv = stoi(l.substr(idx + 1));

        logs[lv].emplace_back(t);
    }

    for(int i = 1; i < 7; ++i) {
        if (logs[i].empty()) continue;
        sort(logs[i].begin(), logs[i].end());
    }

    string query, s, e;
    while (q--) {
        getline(cin, query);
        int p1 = query.find('#');
        int p2 = query.find('#', p1 + 1);

        s = query.substr(0, p1);
        e = query.substr(p1 + 1, p2 - p1 - 1);
        lv = stoi(query.substr(p2 + 1));
        
        int res = 0;
        for(int i = lv; i < 7; ++i) {
            if (logs[i].empty()) continue;
            res += upper_bound(logs[i].begin(), logs[i].end(), e) - lower_bound(logs[i].begin(), logs[i].end(), s);
        }
        cout << res << '\n';
    }

    return 0;
}