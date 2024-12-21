#include <iostream>

using namespace std;

int n, ans;
string num;

int main() {
  cin >> n;
  cin >> num;
  ans = 0;
  for(int i = 0; i < n; i++) {
    ans += num[i] - '0';
  }
  cout << ans << '\n';
  return 0;
}