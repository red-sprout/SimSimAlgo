#include <iostream>
#include <vector>
#include <cmath>

using namespace std;
typedef long long ll;

int n;
ll arr[100'000];
vector<int> selling;
vector<int> buying;

ll solution() {
	int s, b;
	ll res = 0;
	for (s = 0, b = 0; b < buying.size(); ++b) {
		int ss = selling[s], bb = buying[b];
		ll vs = abs(arr[ss]), vb = abs(arr[bb]);
		if (vs > vb) {
			res += vb * abs(ss - bb);
			arr[bb] = 0;
			arr[ss] -= vb;
		}
		else if(vs < vb) {
			res += vs * abs(ss - bb);
			arr[bb] += vs;
			arr[ss] = 0;
			++s; --b;
		}
		else {
			res += vb * abs(ss - bb);
			arr[bb] = 0;
			arr[ss] = 0;
			++s;
		}
	}
	return res;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n;
	for (int i = 0; i < n; ++i) {
		cin >> arr[i];
		if (arr[i] > 0) {
			selling.emplace_back(i);
		}
		else if (arr[i] < 0) {
			buying.emplace_back(i);
		}
	}
	cout << solution() << '\n';
	return 0;
}
