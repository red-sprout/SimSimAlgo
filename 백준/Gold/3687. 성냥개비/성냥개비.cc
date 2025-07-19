#include <bits/stdc++.h>
#define FASTIO ios::sync_with_stdio(0), cin.tie(0), cout.tie(0)
using namespace std;

int num[] = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };
deque<int> match[8];

string dp_min[101][2], dp_max[101][2];

bool comp(string a, string b) {
    if (a.length() == b.length()) return a < b;
    return a.length() < b.length();
}

string get_min(int n, bool flag) {
    if (n == 0) return "";
    if (dp_min[n][flag] != "") return dp_min[n][flag];

    string s = "NONE";
    for (int i = 2; i < 8; ++i) {
        if (match[i].empty() || n < i) continue;
        string gm = get_min(n - i, 0), t;
        if (gm == "NONE") continue;
        t = (i == 6 && flag) ? "6" + gm : to_string(match[i].front()) + gm;
        if (s == "NONE" || !comp(s, t)) s = t;
    }
    return dp_min[n][flag] = s;
}

string get_max(int n, bool flag) {
    if (n == 0) return "";
    if (dp_max[n][flag] != "") return dp_max[n][flag];

    string s = "NONE";
    for (int i = 2; i < 8; ++i) {
        if (match[i].empty() || n < i) continue;
        string gm = get_max(n - i, 0), t;
        if (gm == "NONE") continue;
        t = (i == 6 && flag) ? "9" + gm : to_string(match[i].back()) + gm;
        if (s == "NONE" || comp(s, t)) s = t;
    }
    return dp_max[n][flag] = s;
}

int main() {
    FASTIO;
    for (int i = 0; i < 10; ++i) match[num[i]].emplace_back(i);
    for (int i = 2; i < 8; ++i) sort(match[i].begin(), match[i].end());
    int t; cin >> t;
    while (t--) {
        int n; cin >> n;
        cout << get_min(n, 1) << ' ' << get_max(n, 1) << '\n';
    }
    return 0;
}
