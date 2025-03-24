#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;
typedef long long ll;
typedef pair<ll, ll> pll;

int n;
vector<ll> a, b;

bool comp(pll p1, pll p2) {
	return p1.second == p2.second ? p1.first > p2.first : p1.second < p2.second;
}

ll solution() {
	vector<pll> pairs(n);
	for (int i = 0; i < n; ++i) {
		pairs[i] = { a[i], b[i] };
	}
	sort(pairs.begin(), pairs.end(), comp);

	ll res = 0;
	ll now = 0;
	ll threshold = 0;
	for (int i = 0; i < n; ++i) {

		if (pairs[i].first < pairs[i].second) {
			ll cnt = ll(ceil(double(pairs[i].second - pairs[i].first) / 30));
			res += cnt;
			pairs[i].first += cnt * 30;
		}

		if (i != 0 && now > pairs[i].first) {
			ll cnt = ll(ceil(double(now - pairs[i].first) / 30));
			res += cnt;
			pairs[i].first += cnt * 30;
		}

		threshold = max(pairs[i].first, threshold);
		if (i < n - 1 && pairs[i].second != pairs[i + 1].second) {
			now = threshold;
		}
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	
	a.resize(n);
	for (int i = 0; i < n; ++i) {
		cin >> a[i];
	}

	b.resize(n);
	for (int i = 0; i < n; ++i) {
		cin >> b[i];
	}

	cout << solution() << '\n';
	return 0;
}