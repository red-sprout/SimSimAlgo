#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

string s, t;
set<string> st;

bool bfs() {
    queue<string> q;
    q.emplace(t);
    st.emplace(t);
    while (!q.empty()) {
        string cur = q.front(); q.pop();
        if (cur == s) return true;
        if (cur.length() <= s.length()) continue;
        if (cur.back() == 'A') {
            string nxt = cur.substr(0, cur.length() - 1);
            if (st.find(nxt) == st.end()) {
                st.emplace(nxt);
                q.emplace(nxt);
            }
        }
        if (cur.front() == 'B') {
            string rs = cur.substr(1);
            reverse(rs.begin(), rs.end());
            if (st.find(rs) == st.end()) {
                st.emplace(rs);
                q.emplace(rs);
            }
        }
    }
    return false;
}

int main() {
    FASTIO;
    cin >> s >> t;
    cout << bfs() << '\n';
    return 0;
}