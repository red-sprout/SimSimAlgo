#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    int n; cin >> n;
    vector<int> res(n);
    int left = 0, right = n - 1;
    for(int i = 1; i <= n; ++i) (i % 2 ? res[left++] : res[right--]) = i;
    for(auto ele : res) cout << ele << ' ';
    cout << '\n';
    return 0;
}
