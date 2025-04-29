#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
typedef long long ll;

int t, n, m;
int a[1001], b[1001];

int lb(vector<int> bb, int ele) {
    int l = -1, r = bb.size(), mm;
    while (l + 1 < r) {
        mm = (l + r) >> 1;
        if (bb[mm] < ele) {
            l = mm;
        }
        else {
            r = mm;
        }
    }
    return l;
}

int ub(vector<int> bb, int ele) {
    int l = -1, r = bb.size(), mm;
    while (l + 1 < r) {
        mm = (l + r) >> 1;
        if (bb[mm] <= ele) {
            l = mm;
        }
        else {
            r = mm;
        }
    }
    return r;
}

ll solution() {
    vector<int> aa;
    vector<int> bb;

    for (int i = 0; i < n; ++i) {
        for (int j = i + 1; j <= n; ++j) {
            aa.emplace_back(a[j] - a[i]);
        }
    }
    for (int i = 0; i < m; ++i) {
        for (int j = i + 1; j <= m; ++j) {
            bb.emplace_back(b[j] - b[i]);
        }
    }

    sort(aa.begin(), aa.end());
    sort(bb.begin(), bb.end());

    ll cnt = 0;
    int i = 0, j = bb.size() - 1;
    while (i < aa.size() && j >= 0) {
        int sum = aa[i] + bb[j];
        if (sum == t) {
            ll aa_cnt = 1, bb_cnt = 1;
            while (i + 1 < aa.size() && aa[i] == aa[i + 1]) {
                ++i;
                ++aa_cnt;
            }
            while (j - 1 >= 0 && bb[j] == bb[j - 1]) {
                --j;
                ++bb_cnt;
            }
            cnt += aa_cnt * bb_cnt;
            ++i;
            --j;
        }
        else if (sum < t) {
            ++i;
        }
        else {
            --j;
        }
    }
    return cnt;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> t;
    cin >> n;
    for (int i = 1; i <= n; ++i) {
        cin >> a[i];
        a[i] += a[i - 1];
    }

    cin >> m;
    for (int i = 1; i <= m; ++i) {
        cin >> b[i];
        b[i] += b[i - 1];
    }

    cout << solution() << '\n';
    return 0;
}
