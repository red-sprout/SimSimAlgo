#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int n;
vector<int> men_taller;
vector<int> men_shorter;
vector<int> women_taller;
vector<int> women_shorter;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);

    cin >> n;
    int l;
    for(int i = 0; i < n; ++i) {
        cin >> l;
        if(l > 0) {
            men_taller.emplace_back(l);
        } else {
            men_shorter.emplace_back(abs(l));
        }
    }

    for(int i = 0; i < n; ++i) {
        cin >> l;
        if(l > 0) {
            women_taller.emplace_back(l);
        } else {
            women_shorter.emplace_back(abs(l));
        }
    }

    sort(men_taller.begin(), men_taller.end(), greater<>());
    sort(men_shorter.begin(), men_shorter.end(), greater<>());
    sort(women_taller.begin(), women_taller.end(), greater<>());
    sort(women_shorter.begin(), women_shorter.end(), greater<>());

    int res = 0;
    int m = 0, w = 0;
    while(m < men_taller.size() && w < women_shorter.size()) {
        if(men_taller[m] < women_shorter[w]) {
            ++res; ++m; ++w;
        } else {
            ++m;
        }
    }

    m = 0; w = 0;
    while(m < men_shorter.size() && w < women_taller.size()) {
        if(men_shorter[m] > women_taller[w]) {
            ++res; ++m; ++w;
        } else {
            ++w;
        }
    }

    cout << res << '\n';
    return 0;
}