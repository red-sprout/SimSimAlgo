#include <iostream>
#include <string>

using namespace std;

int main() {
  ios::sync_with_stdio(0); cin.tie(0);
  string s;
  getline(cin, s);
  int cnt = 1;
  int i = 0;
  while(i < s.length()) {
    if(s[i++] == ' ') cnt++;
  }
  if(s[0] == ' ') cnt--;
  if(s[s.length() - 1] == ' ') cnt--;
  cout << cnt << '\n';
  return 0;
}