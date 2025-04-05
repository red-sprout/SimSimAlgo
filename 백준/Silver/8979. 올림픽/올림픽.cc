#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct country {
	int id, g, s, b;
};

bool comp(country c1, country c2) {
	if (c1.g == c2.g) {
		if (c1.s == c2.s) {
			return c1.b > c2.b;
		}
		return c1.s > c2.s;
	}
	return c1.g > c2.g;
}

bool is_same_country(country c1, country c2) {
	return (c1.g == c2.g) && (c1.s == c2.s) && (c1.b == c2.b);
}

int n, k;
vector<country> list;

int main() {
	ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
	cin >> n >> k;
	list.resize(n);
	int id, g, s, b;
	for (int i = 0; i < n; ++i) {
		cin >> id >> g >> s >> b;
		list[i] = { id, g, s, b };
	}
	sort(list.begin(), list.end(), comp);

	int rank = 0, cnt = 1;
	for (int i = 0; i < n; ++i) {
		if (i == 0 || !is_same_country(list[i - 1], list[i])) {
			rank += cnt;
			cnt = 1;
		}
		else {
			++cnt;
		}
		if (list[i].id == k) {
			cout << rank << '\n';
			break;
		}
	}
	return 0;
}