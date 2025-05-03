#include <iostream>
#include <vector>
#include <queue>

using namespace std;
typedef long long ll;

int n, m;
priority_queue<ll, vector<ll>, greater<>> pq;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n >> m;
    int card;
    while(n--) {
        cin >> card;
        pq.emplace(card);
    }

    while(m--) {
        ll x = pq.top(); pq.pop();
        ll y = pq.top(); pq.pop();
        pq.emplace(x + y);
        pq.emplace(x + y);
    }

    ll res = 0;
    while(!pq.empty()) {
        res += pq.top(); pq.pop();
    }

    cout << res << '\n';
    return 0;
}