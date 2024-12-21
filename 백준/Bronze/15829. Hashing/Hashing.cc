#include <iostream>
using namespace std;

int r = 31, M = 1234567891;

int main() {
  ios::sync_with_stdio(0); cin.tie(0);
  int n;
  long long ans = 0;
  string str;
  cin >> n;
  cin >> str;
  for(int i = n - 1; i >= 0; i--) {
    ans *= r;
    ans += str[i] - 'a' + 1;
    ans %= M;
  }
  cout << ans << '\n';
  return 0;
}