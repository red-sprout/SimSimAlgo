#include <iostream>

using namespace std;

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  int n; cin >> n;
  string s;
  for(int i = 0; i < n; ++i) {
    cin >> s;
    cout << (s.length() >= 6 && s.length() <= 9 ? "yes\n" : "no\n");
  }
  return 0;
}