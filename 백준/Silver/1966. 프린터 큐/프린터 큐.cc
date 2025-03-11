#include <iostream>
#include <queue>

using namespace std;

int t, n, m;
queue<int> q;
priority_queue<int> pq;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> t;
    int i, val;
    int cnt = 1, cur, maxVal;
    while (t-- > 0) {
        cin >> n >> m;
        for (i = 0; i < n; i++) {
            cin >> val;
            q.emplace(val);
            pq.emplace(val);
        }
        cnt = 1;
        while (!pq.empty()) {
            cur = q.front(); q.pop();
            maxVal = pq.top();
            if (cur == maxVal) {
                if (m == 0) break;
                pq.pop();
                cnt++;
                n--;
            }
            else {
                q.emplace(cur);
            }
            m = (m + n - 1) % n;
        }
        cout << cnt << '\n';
        while (!q.empty()) q.pop();
        while (!pq.empty()) pq.pop();
    }
    return 0;
}